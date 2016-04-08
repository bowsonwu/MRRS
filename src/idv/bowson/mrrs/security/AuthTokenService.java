package idv.bowson.mrrs.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import idv.bowson.mrrs.model.User;

/**
 * AuthToken之相關Service
 */
@Component
public final class AuthTokenService {

    private static Log log = LogFactory.getLog(AuthTokenService.class);

    @Autowired
    private TokenStringService tokenStringService;

    /**
     * 產生一個AuthToken
     *
     * @param user 欲產生AuthToken之User
     * @return 產生之AuthToken
     */
    private AuthToken generate(User user) {
        // 產生token string
        String tokenString = this.tokenStringService.next();

        // 生成AuthToken
        AuthToken authToken = new AuthToken();
        authToken.setUsername(user.getUsername());
        authToken.setTokenString(tokenString);
        authToken.setRoles(user.getRoles());
        authToken.setName(user.getName());

        return authToken;
    }

    /**
     * 產生一個AuthToken並使之生效
     *
     * @param user 所要發給的User
     * @return 所產生的AuthToken
     */
    public AuthToken issue(User user) {

        // 產生AuthToken
        AuthToken authToken = this.generate(user);

        return authToken;
    }

}
