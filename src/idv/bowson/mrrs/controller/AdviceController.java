package idv.bowson.mrrs.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import idv.bowson.mrrs.security.AuthTokenAuthentication;

@ControllerAdvice
public class AdviceController {

    private static Log log = LogFactory.getLog(AdviceController.class);

    @ModelAttribute
    public void addAttributes(Model model) {

        if (SecurityContextHolder.getContext().getAuthentication().getClass() != AnonymousAuthenticationToken.class) {

            AuthTokenAuthentication authTokenAuthentication = (AuthTokenAuthentication) SecurityContextHolder
                    .getContext()
                    .getAuthentication();

            model.addAttribute("authTokenAuthentication", authTokenAuthentication);
        }

    }

}
