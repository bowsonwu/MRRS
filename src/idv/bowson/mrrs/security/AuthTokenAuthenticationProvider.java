package idv.bowson.mrrs.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * AuthTokenAuthentication之AuthenticationProvider
 *
 */
@Component
public class AuthTokenAuthenticationProvider implements AuthenticationProvider {
    /**
     * 此系統之log
     */
    private static Log log = LogFactory.getLog(AuthTokenAuthenticationProvider.class);

    /**
     * AuthTokenSerivce
     */
    @Autowired
    private AuthTokenService authTokenService;

    /**
     * 實做驗證
     */
    @Override
    public Authentication authenticate(Authentication authentication) {

        if (authentication.getCredentials() != null) {
            // AuthToken authToken =
            // this.authTokenService.validate(authentication.getCredentials().toString());
            // if (authToken != null) {
            // AuthTokenAuthentication authenticatedAuthentication = new
            // AuthTokenAuthentication(authToken);
            // AuthTokenAuthentication authenticatedAuthentication =
            // (AuthTokenAuthentication) authentication;
            // AuthTokenAuthenticationProvider.log.info("username = " +
            // authToken.getUsername() + ", token = " +
            // authToken.getTokenString());

            // return authentication;
            AuthTokenAuthentication authenticatedAuthentication = (AuthTokenAuthentication) authentication;
            AuthTokenAuthenticationProvider.log.info(
                    "username = " + authenticatedAuthentication.getPrincipal() + ", roles = "
                            + authenticatedAuthentication.getAuthorities());
            return authenticatedAuthentication;
            // }
        }

        AuthTokenAuthenticationProvider.log.info("token = "
                + (authentication.getCredentials() == null ? "(NULL)" : authentication.getCredentials().toString()));
        throw new BadCredentialsException("BAD_TOKEN");
    }

    /**
     * 判斷Authentication是否能為此AuthenticateProvider所接受
     */
    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(AuthTokenAuthentication.class);
    }

}
