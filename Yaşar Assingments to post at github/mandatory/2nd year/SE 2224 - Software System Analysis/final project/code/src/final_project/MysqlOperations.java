package final_project;

import java.sql.*;
import java.util.Properties;

public class MysqlOperations {

    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    // hocam, kendi database bilgilerinizi bu değişkenlere verin
    public static final String USERNAME = "";
    public static final String PASSWORD = "";
    public static final String schemaName = "se2224_proje";
    public static final String MAX_POOL = "250";

    public static final String databaseName = "tasks";

    public static final String DATABASE_URL = "jdbc:mysql://localhost/" + schemaName + "?user=" + USERNAME + "&password=" + PASSWORD + "&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";

    
    public Connection connection;

    public Properties properties;

    // create properties
    public Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editAndUpdateRowAtSQLTable(int ID, String Name, String Description, Timestamp Deadline, int Priority, boolean ReminderImage) throws SQLException {
        String sql = "UPDATE " +  databaseName + " SET name=?, description=?, Deadline=?, Priority=?, Reminder_Image=? WHERE id=?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, Name);
            pst.setString(2, Description);
            pst.setTimestamp(3, Deadline);
            pst.setInt(4, Priority);
            pst.setBoolean(5, ReminderImage);
            pst.setInt(6, ID);

            pst.executeUpdate();
            System.out.println("Database update successful.");

    }

    public void insertRowAtSQLTable(int ID, String Name, String Description, Timestamp Deadline, int Priority, boolean ReminderImage, Timestamp entryDate) throws SQLException {
        String sql = " insert into " +  databaseName + " (ID, name, description, deadline, priority, Reminder_Image, entryDate  )"
                + " values (?, ?, ?, ?, ?, ? , ?)";


        PreparedStatement preparedStmt = null;

        preparedStmt = connection.prepareStatement(sql);

        preparedStmt.setInt(1, ID);
        preparedStmt.setString(2, Name);
        preparedStmt.setString(3, Description);
        preparedStmt.setTimestamp(4, Deadline);
        preparedStmt.setInt(5, Priority);
        preparedStmt.setBoolean(6, ReminderImage);

        preparedStmt.setTimestamp(7, entryDate);
        preparedStmt.execute();

        System.out.println("Insertion to database successful.");





    }

    public void deleteRowAtSQLTable(int ID) throws SQLException {
        String sql = "delete from " +  databaseName + " where id= " + ID;
        PreparedStatement preparedStmt = null;
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.executeUpdate(sql);

        System.out.println("Deletion from database successful.");

        }
}
