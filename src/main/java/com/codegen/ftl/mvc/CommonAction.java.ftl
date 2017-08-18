package ${extensions['ACTION_PACKAGE']};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共视图控制器
 * @author cxj
 **/
@Controller
public class CommonAction {
    /**
     * 首页
     * 
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    /**
     * 登录页
     */
    @RequestMapping("/page/login")
    public String login() {
        return "login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/page/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 404页
     */
    @RequestMapping("/page/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/page/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/page/500")
    public String error500() {
        return "500";
    }
}