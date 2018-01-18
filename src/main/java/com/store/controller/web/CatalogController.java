package com.store.controller.web;

import com.store.database.dao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CatalogController {

    private final CategoryService categoryService;

    @Autowired
    public CatalogController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Storage page
     *
     * @return modelAndView of catalog page
     */
    @GetMapping("/catalog")
    public ModelAndView catalog() {
        ModelAndView modelAndView = new ModelAndView("/catalog/catalog");
        modelAndView.addObject("categories", categoryService.getAll());

        return modelAndView;
    }

    /**
     * Detail of catalog page, open chosen categories, with help of ajax
     *
     * @param category - name of chosen category
     * @return modelAndView of open category
     */
    @GetMapping("/openCategory")
    public ModelAndView openCategoryFragment(@RequestParam String category) {
        ModelAndView modelAndView = new ModelAndView("/catalog/openCategory :: openCategoryFragment");
        modelAndView.addObject("category", categoryService.get(category));

        return modelAndView;
    }
}
