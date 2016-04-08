package idv.bowson.mrrs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import idv.bowson.mrrs.model.UserService;

/**
 * 驗證AuthToken之filter
 *
 */
@Component
public class AuthTokenAuthenticationFilter extends GenericFilterBean {
    private static Log log = LogFactory.getLog(AuthTokenAuthenticationFilter.class);

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        AuthToken authToken = (AuthToken) request.getSession().getAttribute("AUTH_TOKEN");

        if (authToken == null) {

            SecurityContextHolder.getContext().setAuthentication(null);
        } else {

            AuthTokenAuthentication authTokenAuthentication = new AuthTokenAuthentication(authToken);

            AuthTokenAuthenticationFilter.log.info("Username:" + authTokenAuthentication.getPrincipal());
            try {
                Authentication authentication = this.authenticationManager.authenticate(authTokenAuthentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException e) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        chain.doFilter(req, res);
    }
}
