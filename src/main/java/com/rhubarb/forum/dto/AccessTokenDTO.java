package com.rhubarb.forum.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author: sunxun
 * @date: 10/7/21 1:23 AM
 * @description:
 */

@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}
