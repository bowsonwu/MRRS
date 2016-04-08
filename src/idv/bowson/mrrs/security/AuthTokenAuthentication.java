package idv.bowson.mrrs.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 採用AuthToken認證之Authentication(供Spring Security用)
 *
 */
@SuppressWarnings("serial")
public class AuthTokenAuthentication implements Authentication {

    private static Log log = LogFactory.getLog(AuthTokenAuthentication.class);

    /**
     * 表示此Authentication是否已經驗證身分
     */
    protected boolean isAuthenticated;

    /**
     * 此Authentication之權限
     */
    protected Collection<? extends GrantedAuthority> authorities;

    /**
     * 此Authentication之AuthToken
     */
    protected AuthToken authToken;

    /**
     * 建構子
     *
     * @param authToken AuthToken物件
     */
    public AuthTokenAuthentication(AuthToken authToken) {
        this.authToken = authToken;
        ArrayList<SimpleGrantedAuthority> newAuthorities = new ArrayList<SimpleGrantedAuthority>();

        // roles
        if (this.authToken.getUsername() != null) {
            this.isAuthenticated = true;

            String roles = authToken.getRoles();
            if (roles != null && !roles.equals("")) {
                String[] roleArray = (roles.split(","));
                for (String role : roleArray) {
                    newAuthorities.add(new SimpleGrantedAuthority(role));
                }
            }
        }

        this.authorities = newAuthorities;
    }

    /**
     * 取得Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 取得Credentials(Token String)
     */
    @Override
    public Object getCredentials() {
        return this.authToken.getTokenString();
    }

    /**
     * 取得Details
     */
    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * 取得Name
     */
    @Override
    public String getName() {
        return this.authToken.getName();
    }

    /**
     * 取得Principal(Username)
     */
    @Override
    public Object getPrincipal() {
        return this.authToken.getUsername();
    }

    /**
     * 取得此Authentication是否已經驗證
     */
    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    /**
     * 設定此Authentication是否已驗證。只能將其設為false。
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (!isAuthenticated) {
            this.isAuthenticated = false;
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
