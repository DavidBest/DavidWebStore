package com.store.controller;

import com.store.database.dao.*;
import com.store.database.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class FullController {

    private static final Logger logger = LogManager.getLogger(FullController.class);

    private final ProductService productService;

    private final UserService userService;

    private final OverviewService overviewService;

    private final BookingService bookingServiceImpl;

    private final CategoryService categoryService;

    @Autowired
    public FullController(ProductService productService, UserService userService, OverviewService overviewService, BookingService bookingServiceImpl, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.overviewService = overviewService;
        this.bookingServiceImpl = bookingServiceImpl;
        this.categoryService = categoryService;
    }


    @GetMapping("/catalog")
    public ModelAndView catalog() {
        ModelAndView modelAndView = new ModelAndView("/catalog/catalog");

        modelAndView.addObject("categories", categoryService.getAll());

        return modelAndView;
    }

    @GetMapping("/openCategory")
    public ModelAndView openCategoryFragment(@RequestParam String category) {
        ModelAndView modelAndView = new ModelAndView("/catalog/openCategory :: openCategoryFragment");

        modelAndView.addObject("category", categoryService.get(category));

        return modelAndView;
    }

    @GetMapping("/item/{id}")
    public ModelAndView itemPage(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("itemFull");
        modelAndView.addObject("good", productService.get(id));

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("q") String query) {
        ModelAndView modelAndView = new ModelAndView("searchPage");

        modelAndView.addObject("goods", productService.getByNameContaining(query));

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("account/login");
    }


    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("account/registration");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registrationAction(@Valid User user, BindingResult bindingResult) {

//        if (!Pattern.matches("1234", user.getPassword()))
//            bindingResult.rejectValue("password", "error.user", "wrong password");

        if (bindingResult.hasErrors())
            return new ModelAndView("account/registration");
        else if (userService.create(user) == null) {
            bindingResult.rejectValue("username", "error.username", "This name is already exist");
            return new ModelAndView("account/registration");
        }

        return new ModelAndView("redirect:/catalog");
    }
}
