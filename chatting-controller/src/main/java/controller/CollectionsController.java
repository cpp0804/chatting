package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.RequestResultVO;
import relationship.Collections;
import service.CollectionsService;
import utils.ResultBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/collections")
public class CollectionsController {

    @Autowired
    CollectionsService collectionsService;

    @RequestMapping(value = "/collectOrCancel.do", method = RequestMethod.POST)
    @ResponseBody
    public RequestResultVO collect (HttpServletRequest request, HttpServletResponse reponse) {
        try {
            return collectionsService.collectOrCancel(Long.parseLong(request.getParameter("momentId")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBuilder.buildErrorResult(e.getMessage(), "");
        }
    }

    //我的收藏
    @RequestMapping(value = "/myCollections.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> myCollections (HttpServletRequest request, HttpServletResponse reponse) {
        return collectionsService.myCollections();
    }
}
