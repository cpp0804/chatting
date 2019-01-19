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

    @RequestMapping(value = "/collect.do", method = RequestMethod.POST)
    @ResponseBody
    public RequestResultVO collect (HttpServletRequest request, HttpServletResponse reponse) {

        Long momentId = Long.parseLong(request.getParameter("momentId"));

        RequestResultVO requestResultVO = new RequestResultVO();

        try {
            momentService.collectMoment(userService.findUserByLogin(userService.getSessionId()), momentId);
            requestResultVO.setCode(0);
            requestResultVO.setMessage("收藏成功");
        } catch (Exception e) {
            requestResultVO.setCode(1);
            requestResultVO.setMessage("收藏失败");
            e.printStackTrace();
        }

        return requestResultVO;

    }

}
