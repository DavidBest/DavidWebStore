package com.store.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.database.dao.CategoryService;
import com.store.database.model.Category;
import com.store.exception.DoesNotExistException;
import com.store.other.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import javax.annotation.security.RolesAllowed;
import javax.swing.text.View;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/management/category")
//@RolesAllowed("ROLE_EMPLOYEE")
public class ManagementCategoryController {

    private final CategoryService categoryService;

    @Autowired
    public ManagementCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public HttpStatus createCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return BAD_REQUEST;
        else if (categoryService.get(category.getName()) != null)
            return NOT_MODIFIED;
        else {
            categoryService.create(category);
            return OK;
        }
    }

    @PutMapping("/update")
    public void updateCategory(@Valid Category category, BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            try {
                categoryService.update(category);
            } catch (DoesNotExistException e) {
                e.printStackTrace();
            }
    }

    /**
     * @param name is {@link Category} id in database
     * @return {{@link Category}} without {@link List<com.store.database.model.Product>}
     **/

    @GetMapping("/get")
    @JsonView(Views.Public.class)
    public @ResponseBody
    ResponseEntity<Category> getCategory(@RequestParam String name) {
        return new ResponseEntity<>(categoryService.get(name), OK);
    }

    /**
     * @param name is {@link Category} id in database
     * @return {{@link Category}} with {@link List<com.store.database.model.Product>}
     **/

    @GetMapping("/get/product")
    @JsonView(Views.Category.ListOfProducts.class)
    public @ResponseBody
    ResponseEntity<Category> getCategoryView(@RequestParam String name) {
        return new ResponseEntity<>(categoryService.get(name), OK);

    }

    @GetMapping("/get/all")
    @JsonView(Views.Public.class)
    public @ResponseBody
    ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>((List<Category>) categoryService.getAll(), OK);
    }

    @DeleteMapping("/delete")
    public HttpStatus dropCategory(@RequestParam String name) {
        categoryService.delete(name);
        return OK;
    }

}
