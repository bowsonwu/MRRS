package idv.bowson.mrrs.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import idv.bowson.mrrs.config.AppConstants;

public class MRRSAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static Log log = LogFactory.getLog(MRRSAuthenticationEntryPoint.class);

    @Override
    public final void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        MRRSAuthenticationEntryPoint.log.info("Pre-authenticated entry point called. Rejecting access");
        response.sendRedirect(String.format("/%s", AppConstants.APP_NAME));
    }
}