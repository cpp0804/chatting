package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import service.LikeService;
import utils.ResultBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/likeOrCancel.do", method = RequestMethod.POST)
    @ResponseBody
    public RequestResultVO likeOrCancel (HttpServletRequest request, HttpServletResponse reponse) {
        try {
            return likeService.likeOrCancel(Long.parseLong(request.getParameter("momentId")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBuilder.buildErrorResult(e.getMessage(), "");
        }
    }

    //我的喜欢
    @RequestMapping(value = "/myLikes.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> myLikes (HttpServletRequest request, HttpServletResponse reponse) {
        return likeService.myLikes();
    }
}
