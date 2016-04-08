package idv.bowson.mrrs.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Bean相關設定
 *
 */
@Configuration
public class BeanConfiguration {

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://"
                + AppConstants.MYSQL_HOST_NAME);
        dataSource.setUsername(AppConstants.MYSQL_USERNAME);
        dataSource.setPassword(AppConstants.MYSQL_PASSWORD);
        return dataSource;
    }
}