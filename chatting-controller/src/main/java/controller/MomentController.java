package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import service.MomentService;
import service.UserService;
import service.impl.MomentServiceImpl;
import utils.HttpResponseConstants;
import utils.ResultBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/moment")
public class MomentController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "login";
    }

}
