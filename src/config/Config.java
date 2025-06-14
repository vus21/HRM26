package config;

public class Config {
    
     private static final String DB_URL = "jdbc:mysql://localhost:3306/javadb1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Thay bằng mật khẩu thật

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}