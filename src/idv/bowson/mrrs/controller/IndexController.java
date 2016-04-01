package idv.bowson.mrrs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import idv.bowson.mrrs.model.UserService;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        return new ModelAndView("/portal");
        /*
         * AuthToken authToken = (AuthToken)
         * request.getSession().getAttribute("AUTH_TOKEN");
         * if (authToken == null) {
         * // 未登入
         * return new ModelAndView("/portal");
         * }
         * 
         * // 已登入
         * User user = this.userService.get(new User(authToken.getUsername()));
         * 
         * HashMap<String, Object> map = new HashMap<String, Object>();
         * map.put("authTokenString", authToken.getTokenString());
         * map.put("name", authToken.getName());
         * map.put("roles", authToken.getRoles());
         * map.put("group", user.getGroup());
         * map.put("isProduction", AppConstants.IS_PRODUCTION);
         * 
         * return new ModelAndView("/WebClient/workspace", map);
         */
    }

    /**
     * 登入AuditCloud WebClient
     * 角色權限: 公開
     *
     * @param username 使用者名稱
     * @param password 密碼
     * @param request HttpRequest物件
     * @return 如果密碼驗證成功，則導至"GET /"；若失敗，則回傳View:
     *         /WebClient/portal並顯示相關訊息
     * @throws IOException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(required = false) String username,
            @RequestParam(required = false) String password, HttpServletRequest request, HttpServletResponse response)
                    throws IOException {
        try {
            AuthToken authToken = this.userService.authenticate(username, password);
            request.getSession().setAttribute("AUTH_TOKEN", authToken);

            // 驗證成功，導至"GET /"
            return new ModelAndView("redirect:/");
        } catch (AuthenticationException e) {
            // 驗證失敗
            WebClientController.log.info("驗證失敗: " + username);
            HashMap<String, String> models = new HashMap<String, String>();
            models.put("message", "AUTHENTICATION_FAILED");
            return new ModelAndView("/WebClient/portal", models);
        }
    }

}