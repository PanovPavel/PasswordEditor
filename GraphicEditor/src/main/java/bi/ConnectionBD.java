package bi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String DB_DRIVER = "org.microsoft";
    private static final String DB_URL = "C:\\Program Files\\Microsoft SQL Server\\MSSQL15.SQLEXPRESS\\MSSQL\\DATA\\PasswordManager.mdf";
    private static final String DB_USERNAME = "DESKTOP-LH8OTUP\\Pavel";
    private static final String DB_PASSWORD = "";

    String connectionUrl =
            "jdbc:sqlserver://localhost;databaseName=PasswordManager;integratedSecurity=true;Trusted_Connection=yes";
    Connection connection;

    public Connection getConnection(){
        {
            try {
                connection = DriverManager.getConnection(connectionUrl);
                System.out.println("Connection OK");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("Connection ERROR");
            }
        }
        return connection;
    }


}