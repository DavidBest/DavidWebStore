package com.store.controller.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

//    private static final Logger logger = LogManager.getLogger(IndexController.class);

    /**
     * !!!not completed !!!
     * Main page of project
     *
     * @return modelAndView of index page
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * for login page
     *
     * @return modelAndView
     */
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("account/login");
    }


}
