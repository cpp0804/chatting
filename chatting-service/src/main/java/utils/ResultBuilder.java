package utils;

import pojo.RequestResultVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 标准的结果生成器
 * 
 */
public class ResultBuilder {

    /**
     * 生成成功的结果
     * 
     * @param obj 数据体
     * @return
     */
    public static RequestResultVO buildSuccessResult(String message, Object obj) {
        RequestResultVO response = new RequestResultVO();
        response.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        response.setMessage(message);
        response.setData(obj);
        return response;

    }

    /**
     * 生成失败的结果
     *
     * @param obj 数据体
     * @return
     */
    public static RequestResultVO buildErrorResult(String message, Object obj) {
        RequestResultVO response = new RequestResultVO();
        response.setCode(HttpResponseConstants.Public.ERROR_CODE);
        response.setMessage(message);
        response.setData(obj);
        return response;

    }
}
