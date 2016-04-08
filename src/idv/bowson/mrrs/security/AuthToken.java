package idv.bowson.mrrs.security;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import idv.bowson.mrrs.model.Role;

/**
 * 驗證使用者身分之Token
 *
 */
@SuppressWarnings("serial")
public class AuthToken implements Serializable {

    private static Log log = LogFactory.getLog(AuthToken.class);

    /**
     * 使用者名稱
     */
    private String username;

    /**
     * 使用者姓名
     */
    private String name;

    /**
     * 角色字串
     */
    private String roles;

    /**
     * Token字串
     */
    private String tokenString;

    /**
     * 建構子
     */
    public AuthToken() {

    }

    /**
     * 取得使用者姓名
     *
     * @return 使用者姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 取得角色字串
     *
     * @return 角色字串
     */
    public String getRoles() {
        return this.roles;
    }

    /**
     * 取得Token字串
     *
     * @return Token字串
     */
    public String getTokenString() {
        return this.tokenString;
    }

    /**
     * 取得使用者名稱
     *
     * @return 使用者名稱
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 設定使用者姓名
     *
     * @param name 使用者姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 設定使用者角色字串
     *
     * @param roles 使用者角色字串
     */
    public void setRoles(List<Role> roles) {

        if (roles == null || roles.size() < 1) {
            this.roles = null;
            return;
        } else {

            this.roles = roles.get(0).getName();
            for (int index = 1; index < roles.size(); index++) {
                this.roles += "," + roles.get(index).getName();
            }
        }
    }

    /**
     * 設定Token字串
     *
     * @param tokenString Token字串
     */
    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    /**
     * 設定使用者名稱
     *
     * @param username 使用者名稱
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
