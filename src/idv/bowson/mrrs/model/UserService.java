package idv.bowson.mrrs.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import idv.bowson.mrrs.config.AppConstants;
import idv.bowson.mrrs.security.AuthToken;
import idv.bowson.mrrs.security.AuthTokenService;

@Component
public class UserService {

    private static Log log = LogFactory.getLog(UserService.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthTokenService authTokenService;

    private static final String USERS_TABLE = AppConstants.APP_DB_NAME + ".Users";
    private static final String USER_ROLES_TABLE = AppConstants.APP_DB_NAME + ".UserRoles";
    private static final String ROLES_TABLE = AppConstants.APP_DB_NAME + ".Roles";

    /**
     * Inject SQL之DataSource
     */
    @Autowired
    public UserService(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    /**
     * 檢查使用者名稱、密碼、是否有效
     * 
     * @param username 使用者名稱
     * @param password 使用者密碼
     * @return AuthToken
     * @throws BadCredentialsException
     * @throws NoSuchAlgorithmException
     */
    public AuthToken authenticate(String username, String password)
            throws BadCredentialsException, NoSuchAlgorithmException {

        // 1. 資料庫驗證
        // 1.1. 查詢有無此使用者
        User user = this.get(username);
        if (user == null) {

            throw new BadCredentialsException("User not found!");
        }

        // 1.2. 檢查密碼是否正確
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hashedPassword = Hex.encodeHexString(digest.digest(password.getBytes()));

        if (!user.getPassword().equals(hashedPassword)) {

            throw new BadCredentialsException("Wrong Password!");
        }

        // 1.3. 檢查是否為有效使用者
        if (!user.isEnabled()) {
            throw new BadCredentialsException("User is not enabled!");
        }

        // 2. 驗證通過，發給AuthToken
        return this.authTokenService.issue(user);

    }

    /**
     * 透過username 進入資料庫取得User information
     * 
     * @param username 使用者名稱
     * @return User
     */
    public User get(String username) {

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + USERS_TABLE + " where username = :username";
        SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
        List<User> users = jdbcTemplate.query(sql, namedParameters, new UserRowMapper());

        if (!users.isEmpty()) {

            User user = users.get(0);
            sql = "SELECT Roles.id,Roles.name FROM " + USER_ROLES_TABLE + " AS UserRoles JOIN " + ROLES_TABLE
                    + " AS Roles ON UserRoles.roleId = Roles.id WHERE UserRoles.username = :username;";
            namedParameters = new MapSqlParameterSource("username", user.getUsername());
            List<Role> roles = jdbcTemplate.query(sql, namedParameters, new RoleRowMapper());

            for (Role role : roles) {
                user.getRoles().add(role);
            }

            return user;
        } else {

            return null;
        }
    }
}
