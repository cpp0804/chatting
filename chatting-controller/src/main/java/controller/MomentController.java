package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MomentService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/moment")
public class MomentController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "login";
    }

}
