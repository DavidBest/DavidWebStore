package com.store.controller.web;

import com.store.database.dao.UserService;
import com.store.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * registration page
     *
     * @return modelAndView
     */
    @GetMapping
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("account/registration");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    /**
     * registration on the website
     *
     * @param user          data
     * @param bindingResult need to send information about exceptions
     * @return registration page if it has exceptions
     * or index page if registration complete correctly
     */
    @PostMapping
    public ModelAndView registrationAction(@Valid User user, BindingResult bindingResult) {

        if (!Pattern.matches("[a-zA-Z0-9]{7,20}", user.getPassword()))
            bindingResult.rejectValue("password", "error.user", "have to be between 7 and 20");

        if (!Pattern.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,20})", user.getPassword()))
            bindingResult.rejectValue("password", "error.user", "have one upper case,lower case, numeric");

        if (userService.create(user) == null)
            bindingResult.rejectValue("username", "error.username", "This name is already exist");

        if (bindingResult.hasErrors())
            return new ModelAndView("account/registration");
        else
            return new ModelAndView("redirect:/");
    }
}
