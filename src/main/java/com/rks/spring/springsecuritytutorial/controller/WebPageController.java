package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApplicationConstant.WEB_PATH)
public class WebPageController {

    /**
     * This method is http get web page url to register the user in database
     * @return
     */
    @RequestMapping(value = ApplicationConstant.REGISTER_PAGE_URL)
    public String registerPage() {
        return "register";
    }

    /**
     * This method is http get web page url to load home page.
     * @return
     */
    @RequestMapping(value = ApplicationConstant.HOME_PAGE_URL)
    public String homepage() {
        return "home";
    }

}
