package com.store.controller.web;

import com.store.component.service.BuyingService;
import com.store.database.dao.ProductService;
import com.store.database.model.Booking;
import com.store.database.model.Overview;
import com.store.database.model.Product;
import com.store.database.model.User;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;
    private final BuyingService buyingService;

    @Autowired
    public ProductController(ProductService productService, BuyingService buyingService) {
        this.productService = productService;
        this.buyingService = buyingService;
    }

    /**
     * !!!not completed!!!
     *
     * @param id - of item in storage
     * @return modelAndView
     */
    @GetMapping("/item/{id}")
    public ModelAndView item(@PathVariable long id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/item/openItem :: openItem");
        Product product = productService.get(id);

        modelAndView.addObject("product", product);
        session.setAttribute("product", product);

        return modelAndView;
    }

    @GetMapping("/item/{id}/overviews")
    public ModelAndView itemOverviews(@PathVariable long id, @RequestParam String rating, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/item/overviews");
        Product product = (Product) session.getAttribute("product");

        if (product.getId() != id) {
            product = productService.get(id);
            session.setAttribute("product", product);
        }

        List<Overview> overviewList = product.getOverviews();
        if (0 == Integer.valueOf(rating))
            overviewList = overviewList.stream().limit(10).collect(Collectors.toList());
        else
            overviewList = overviewList.stream()
                    .filter(overview -> overview.getRating() == Integer.valueOf(rating))
                    .limit(10)
                    .collect(Collectors.toList());

        modelAndView.addObject("overviews", overviewList);

        return modelAndView;
    }

    /**
     * !!!not completed!!!
     *
     * @param id item id
     * @return modelAndView
     * @throws DoesNotExistException buyingService exception
     */
    @PostMapping("/buy")
    public ModelAndView buying(@RequestBody long id, HttpSession session) throws DoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("/account/booking");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Product product = (Product) session.getAttribute("product");
        if (product.getId() != id)
            product = productService.get(id);

        Booking booking = buyingService.buy(user, product);

//        ModelAndView modelAndView = new ModelAndView("redirect:/account/booking/" + booking.getId());
        modelAndView.addObject("booking", booking);
        return modelAndView;
    }

    /**
     * !!!not completed!!!
     *
     * @param query user query
     * @return modelAndView
     */
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("q") String query) {
        ModelAndView modelAndView = new ModelAndView("searchPage");
        modelAndView.addObject("goods", productService.getByNameContaining(query));

        return modelAndView;
    }
}
