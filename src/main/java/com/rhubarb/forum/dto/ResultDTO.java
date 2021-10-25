package com.rhubarb.forum.dto;

import com.rhubarb.forum.exception.CustomizedErrorResult;
import com.rhubarb.forum.exception.CustomizedException;
import lombok.Data;

/**
 * @author: sunxun
 * @date: 10/22/21 9:36 PM
 * @description: 返回给前端的状态码和信息。
 */

@Data
public class ResultDTO {

    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizedErrorResult errorResult) {
        return errorOf(errorResult.getErrorCode(), errorResult.getErrorMessage());
    }

    public static ResultDTO errorOf(CustomizedException ex) {
        return ResultDTO.errorOf(ex.getCode(), ex.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("操作成功！");
        return resultDTO;
    }
}
