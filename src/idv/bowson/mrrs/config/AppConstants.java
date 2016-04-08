package idv.bowson.mrrs.config;

import org.springframework.stereotype.Component;

/**
 * AuditCloud API Service設定值
 */
@Component
public class AppConstants {

    public static final String APP_NAME = "MRRS";
    public static final String APP_DB_NAME = "Mrrs";

    // MySQL
    public static final String MYSQL_HOST_NAME = "127.0.0.1/mrrs";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "root";
}