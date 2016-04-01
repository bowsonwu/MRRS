package idv.bowson.mrrs.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.BadCredentialsException;

import idv.bowson.mrrs.config.AppConstants;

public class UserService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRowMapper userRowMapper;

    private static final String USERS_TABLE = AppConstants.APP_DB_NAME + ".Users";

    public UserService(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public AuthToken authenticate(String username, String password) {

        // 1. 資料庫驗證(查詢有無此使用者、是否有效)
        User user = this.get(new User(username));
        if (!this.userService.authenticate(user)) {
            throw new BadCredentialsException("AUTHENTICATION_FAILED");
        }

        // 2. 驗證通過，發給AuthToken
        return this.authTokenService.issue(user);

    }

    public User get(User user) {

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + USERS_TABLE + " where username = :username";
        SqlParameterSource namedParameters = new MapSqlParameterSource("username", user.getUsername());
        List<User> list = jdbcTemplate.query(sql, namedParameters, this.userRowMapper);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
