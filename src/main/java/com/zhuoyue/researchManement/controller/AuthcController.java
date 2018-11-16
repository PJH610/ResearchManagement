package com.zhuoyue.researchManement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/authc")
public class AuthcController extends BaseApiController {

    @RequestMapping("/unauthc")
    public Map<String, Object> unauthc() {
        return onBadResp("您尚未登录或登录时间过长,请重新登录!");
    }

    @RequestMapping("/unperm")
    public Map<String, Object> unpermi() {
        return onBadResp("您没有足够的权限执行该操作!");
    }

}
