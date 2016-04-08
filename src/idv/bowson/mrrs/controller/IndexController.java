package idv.bowson.mrrs.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import idv.bowson.mrrs.model.Announcement;
import idv.bowson.mrrs.model.AnnouncementService;
import idv.bowson.mrrs.model.UserService;
import idv.bowson.mrrs.security.AuthToken;

@Controller
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AnnouncementService announcementService;

    // @PreAuthorize("hasRole('ADMIN')")
    // @RequestMapping(value = "/admin", method = RequestMethod.GET)
    // public ModelAndView showAdmin(HttpServletRequest request) {
    //
    // IndexController.log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    // AuthToken authToken = (AuthToken)
    // request.getSession().getAttribute("AUTH_TOKEN");
    // if (authToken == null) {
    // // 未登入
    // return new ModelAndView("/portal");
    // }
    //
    // // 已登入
    // User user = this.userService.get(authToken.getUsername());
    //
    // HashMap<String, Object> map = new HashMap<String, Object>();
    // map.put("username", authToken.getUsername());
    // map.put("name", authToken.getName());
    // map.put("roles", authToken.getRoles());
    //
    // return new ModelAndView("index", map);
    //
    // }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showIndex(HttpServletRequest request) {

        AuthToken authToken = (AuthToken) request.getSession().getAttribute("AUTH_TOKEN");
        if (authToken == null) {
            // 未登入
            return new ModelAndView("/login");
        }

        // 已登入
        // Get top 5 new Announcements
        List<Announcement> announcements = announcementService.getLastestPostList(5);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("announcements", announcements);
        return new ModelAndView("home", map);

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
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(required = false) String username,
            @RequestParam(required = false) String password, HttpServletRequest request, HttpServletResponse response)
                    throws IOException, NoSuchAlgorithmException {

        try {
            AuthToken authToken = this.userService.authenticate(username, password);
            request.getSession().setAttribute("AUTH_TOKEN", authToken);

            // 驗證成功，導至"GET /"
            return new ModelAndView("redirect:/");
        } catch (AuthenticationException e) {
            // 驗證失敗
            IndexController.log.info("驗證失敗: " + username + ":" + e.getMessage());
            HashMap<String, String> models = new HashMap<String, String>();
            models.put("message", "AUTHENTICATION_FAILED");
            return new ModelAndView("/login", models);
        } catch (NoSuchAlgorithmException e) {

            IndexController.log.info("NoSuchAlgorithmException: " + e.getMessage());
            throw e;
        }
    }

    /**
     * 登出AuditCloud。撤銷此使用者之AuthToken。
     * 角色權限: 公開
     *
     * @param request
     * @return 登入頁面
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        HashMap<String, String> models = new HashMap<String, String>();
        AuthToken authToken = (AuthToken) request.getSession().getAttribute("AUTH_TOKEN");

        if (authToken != null) {
            request.getSession().removeAttribute("AUTH_TOKEN");
            models.put("message", "LOGGED_OUT");
        }

        return new ModelAndView("/login", models);
    }

    /**
     * 權限不足顯示畫面
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView showAccessDenied(HttpServletRequest request) {

        return new ModelAndView("/accessDenied");

    }

}