package com.store.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.store.database.dao.ProductService;
import com.store.database.model.Product;
import com.store.exception.DoesNotExistException;
import com.store.other.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/management/product")
public class ManagementProductsController {

    private final ProductService productService;

    @Autowired
    public ManagementProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<Product> create(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>((Product) null, BAD_REQUEST);
        else return new ResponseEntity<>(productService.create(product), OK);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody Product product) {
        try {
            productService.update(product);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestParam long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/get/all")
    @JsonView(Views.Public.class)
    public @ResponseBody
    ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>((List<Product>) productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{category}")
    @JsonView(Views.Public.class)
    public @ResponseBody
    ResponseEntity<List<Product>> getByCategory(@PathVariable("category") String name) {
        return new ResponseEntity<>(productService.getByCategoryName(name), HttpStatus.OK);
    }

    @GetMapping("/get")
    @JsonView({Views.Product.ListOfOverview.class, Views.Product.ListOfBooking.class})
    public @ResponseBody
    ResponseEntity<Product> get(@RequestParam long id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }


}
