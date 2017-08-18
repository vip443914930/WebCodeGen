package ${extensions['ACTION_PACKAGE']};

import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName};
import ${extensions['SERVICE_PACKAGE']}.${tableConfig.domainObjectName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 **/
@Controller
@RequestMapping(value = "/${tableConfig.domainObjectName?lower_case}")
public class ${tableConfig.domainObjectName}Action {

    @Resource
    private ${tableConfig.domainObjectName}Service ${tableConfig.domainObjectName?uncap_first}Service;


    @RequestMapping(value = "/queryPageList")
    @ResponseBody
    public String queryPageList() {
        return null;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public String add() {
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public String del() {
        return null;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update() {
        return null;
    }

    @RequestMapping(value = "/queryById")
    @ResponseBody
    public String queryById() {
        return null;
    }
}
