package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApplicationConstant.WEB_PATH)
public class WebPageController {

    @RequestMapping(value = ApplicationConstant.REGISTER_PAGE_URL)
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value = ApplicationConstant.HOME_PAGE_URL)
    public String homepage() {
        return "home";
    }

}
