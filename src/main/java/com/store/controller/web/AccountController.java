package com.store.controller.web;

import com.store.component.service.RatingService;
import com.store.database.dao.BookingService;
import com.store.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final BookingService bookingService;
    private final RatingService ratingService;

    @Autowired
    public AccountController(BookingService bookingService, RatingService ratingService) {
        this.bookingService = bookingService;
        this.ratingService = ratingService;

    }

    /**
     * !!!not completed!!!
     * main account page
     *
     * @return modelAndView
     */
    @GetMapping
    public ModelAndView account() {
        ModelAndView modelAndView = new ModelAndView("/account/accountDetail");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    /**
     * !!!not completed!!!
     * page with booking list of user
     *
     * @return modelAndView
     */
    @GetMapping("/booking")
    public ModelAndView bookings() {
        ModelAndView modelAndView = new ModelAndView("/account/bookings");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("bookings", user.getBookings());

        return modelAndView;
    }

    /**
     * !!!not completed!!!
     *
     * @param id of booking
     * @return modelAndView
     */
    @GetMapping("/booking/{id}")
    public ModelAndView booking(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("/account/booking");
        modelAndView.addObject("booking", bookingService.get(id));

        return modelAndView;
    }


    /**
     * !!!not completed!!!
     *
     * @param rating  user rate
     * @param comment user comment
     * @param id      of product
     * @return modelAndView
     */
    @PostMapping("/rate")
    public ModelAndView rate(@RequestBody int rating, @RequestBody String comment, @RequestBody long id) {
        ratingService.rate(comment, rating, bookingService.get(id));

        return new ModelAndView("redirect:/account/booking/" + id);
    }

}
