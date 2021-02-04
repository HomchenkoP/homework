package ru.geekbrains.javaCore.lesson09;

import java.sql.*;

// Создать класс кота
// Создать в бд таблицу с котами
// Написать метод извлечения котов
// Метод добавления котов
// Метод удаления котов
// Метод изменения котов

public class Homework09 {

    private static Connection connection;
    private static Statement statement;
    private static final String createDDL = "CREATE TABLE IF NOT EXISTS cats (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL, age INTEGER, breed TEXT, colour TEXT);";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // открываем соединение с БД
        openConn();

        // создаем таблицу cats
        statement = connection.createStatement();
        statement.execute(createDDL);

        // вставляем в таблицу запись
        insertCat(new Cat("Васька", 3, "дворянин", "белый"), connection);
        insertCat(new Cat("Мурка", 1, null, "черный"), connection);
        insertCat(new Cat("Чубайс", 15, "мейн-кун", "рыжий"), connection);
        insertCat(new Cat("Прошка", 0, null, null), connection);

        // извлекаем все записи
        Cat[] cats = selectAll(connection);
        for (Cat c : cats)
            System.out.println(c.toString());

        // удаляем одну запись из таблицы
        deleteCat(cats[2].getId(), connection);

        // вносим изменения
        cats[1].setBreed("сибиряк");
        cats[3].setAge(1);
        cats[3].setBreed("метис");
        cats[3].setColour("серый");

        // сохраняем изменения в БД
        updateCat(cats[1], connection);
        updateCat(cats[3], connection);

        // извлекаем все записи
        cats = selectAll(connection);
        for (Cat c : cats)
            System.out.println(c.toString());

        // закрываем соединение с БД
        closeConn();
    }

    // соединение с БД SQLite
    private static void openConn() throws ClassNotFoundException, SQLException {
        // загрузка драйвера БД SQLite
        Class.forName("org.sqlite.JDBC");
        // подключение к БД с именем lesson09.db
        connection = DriverManager.getConnection("jdbc:sqlite:lesson09.db");
        // автокоммит
        connection.setAutoCommit(true);
    }

    // закрытие соединения с БД
    private static void closeConn() throws SQLException {
        connection.close();
    }

    // добавление одной записи в БД
    private static void insertCat(Cat cat, Connection connection) throws SQLException {
        final String insertDML = "INSERT INTO cats (name, age, breed, colour) VALUES (?, ?, ?, ?);";
        PreparedStatement ps;
        ps = connection.prepareStatement(insertDML);
        ps.setString(1, cat.getName());
        ps.setInt(2, cat.getAge());
        ps.setString(3, cat.getBreed());
        ps.setString(4, cat.getColour());
        if (ps.executeUpdate() != 0)
            System.out.println("Добавлена запись кота '" + cat.getName() + "'.");
    }

    // удаление одной записи в БД
    private static void deleteCat(int id, Connection connection) throws SQLException {
        final String deleteDML = "DELETE FROM cats WHERE id = ?;";
        PreparedStatement ps;
        ps = connection.prepareStatement(deleteDML);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0)
            System.out.println("Не удалось удалить запись с id=" + id + ".");
        else
            System.out.println("Удалена запись с id=" + id + ".");
    }

    // изменение одной записи в БД
    private static void updateCat(Cat cat, Connection connection) throws SQLException {
        final String updateDML = "UPDATE cats SET name = ?, age = ?, breed = ?, colour = ? WHERE id = ?;";
        PreparedStatement ps;
        ps = connection.prepareStatement(updateDML);
        ps.setString(1, cat.getName());
        ps.setInt(2, cat.getAge());
        ps.setString(3, cat.getBreed());
        ps.setString(4, cat.getColour());
        ps.setInt(5, cat.getId());
        if (ps.executeUpdate() != 0)
            System.out.println("Изменена запись кота '" + cat.getName() + "'.");
    }

    // извлечение одной записи и БД
    private static Cat selectCat(int id, Connection connection) throws SQLException {
        final String selectDML = "SELECT * FROM cats WHERE id = ?;";
        PreparedStatement ps;
        ps = connection.prepareStatement(selectDML);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Cat cat = new Cat(
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("breed"),
                rs.getString("colour")
        );
        cat.setId(rs.getInt("id"));
        return cat;
    }

    // извлечение всех записей из БД
    private static Cat[] selectAll(Connection connection) throws SQLException {
        Statement statement;
        statement = connection.createStatement();
        // количество записей в таблице
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM cats;");
        rs.next();
        int cnt = rs.getInt(1);
        // все записи таблицы
        Cat[] cats = new Cat[cnt];
        int i = 0;
        rs = statement.executeQuery("SELECT * FROM cats;");
        while (rs.next()) {
            Cat c = new Cat(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("breed"),
                    rs.getString("colour")
            );
            c.setId(rs.getInt("id"));
            cats[i++] = c;
        }
        return cats;
    }
}
