import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
Готовое домашнее задание к пятому уроку
У Вас оно может отличаться, в зависимости от стиля программирования
*/

public class HomeWork5 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Задаем параметры подключения
        String url = "jdbc:h2:./test";
        String userName = "valerii";
        String password = "";
//        Class.forName("org.h2.Driver");

        // Создаем список абонентов
        List<String> newAbonents = new ArrayList<>();

        // Создаем подключение
        try(Connection connection = DriverManager.getConnection(url, userName, password)) {
            // Создаем команды вставки новых записей в таблицу
            String newAbonenet1 = "INSERT INTO Phonebook (number,lastName) VALUES (111115555, 'Bradly')";
            String newAbonenet2 = "INSERT INTO Phonebook (number,lastName) VALUES (111116666, 'Bob')";
            String newAbonenet3 = "INSERT INTO Phonebook (number,lastName) VALUES (111117777, 'Kate')";
            String newAbonenet4 = "INSERT INTO Phonebook (number,lastName) VALUES (111118888, 'Scarlett')";

            // Заполняем список абонентов
            newAbonents.add(newAbonenet1);
            newAbonents.add(newAbonenet2);
            newAbonents.add(newAbonenet3);
            newAbonents.add(newAbonenet4);

            // Вызываем метод вставки новой записи
            insertNewAbon(connection, newAbonents);
        }
    }

    // Метод для вставки новых записей в таблицу
    public static void insertNewAbon(Connection conn, List<String> abonents) throws SQLException {
        Statement statement = null;
        try {
            // Выполняем вставку группой, в контексте отдельной транзакции
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            for (String a : abonents) {
                statement.addBatch(a);
            }
            statement.executeBatch();
            conn.commit();
        } catch (SQLException exc) {
            // Отменяем транзакцию в случае возникновения ошибок
            conn.rollback();
            exc.printStackTrace();
        } finally {
            statement.close();
        }
    }
}
