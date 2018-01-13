//package com.store.controller;
//
//import com.store.database.dao.realization.UserServiceImpl;
//import com.store.database.model.User;
////import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class UserController {
//
////    private final static Logger logger = Logger.getLogger(UserController.class);
//
//    private final UserServiceImpl userService;
//
//
//    @Autowired
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("page/home");
//
//        return modelAndView;
//    }
//
//
//    @GetMapping("/registration")
//    public ModelAndView registration() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("page/registration");
//
//        modelAndView.addObject("user", new User());
//
//        return modelAndView;
//    }
//
//
//    @GetMapping("/complete")
//    public ModelAndView complete() {
//        return new ModelAndView("page/completedRegistration");
//    }
//
//    @GetMapping("/account")
//    public ModelAndView account() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("page/accountPage");
////        modelAndView.addObject()
//
//        return modelAndView;
//    }
//
//    @GetMapping("/account/change")
//    public ModelAndView accountChange() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("detail/accountDetail");
//
//        return modelAndView;
//    }
//
//    @GetMapping("/account/orders")
//    public ModelAndView accountOrders() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("detail/orders");
//
//        return modelAndView;
//    }
//
//
//
//
//}
