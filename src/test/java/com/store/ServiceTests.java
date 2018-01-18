package com.store;

import com.store.configuration.JpaStoreConfiguration;
import com.store.database.dao.CategoryService;
import com.store.database.dao.ProductService;
import com.store.database.dao.UserService;
import com.store.database.model.Booking;
import com.store.database.model.Category;
import com.store.database.model.Product;
import com.store.database.model.User;
import com.store.exception.DoesNotExistException;
import com.store.component.service.BuyingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaStoreConfiguration.class,loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("update")
public class ServiceTests {

    @Autowired
    private BuyingService buyingService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void buy() throws DoesNotExistException {

        User user = new User();
        user.setUsername("davidoss");
        user.setPassword("123123Aaskdj");
        user.setMail("madhawk0071@yandex.ru");


        userService.create(user);

        Product product = new Product();
        product.setCount(5);
        product.setName("asdfgq");
        product.setId(2);

        productService.create(product);

        Booking b = buyingService.buy(user, product);

        System.out.println(b.getId());

    }

    @Test
    public void transactionCategory(){
        categoryService.getAll().forEach(category -> System.out.println(category.getName()));
    }

    @Test
    public void transactionProduct(){
        productService.getByCategory(new Category()).forEach(product -> System.out.println(product.getName()));
    }

    @Test
    public void buyReal() throws DoesNotExistException {
        User user = userService.get("david");

        Product product = productService.get(1L);

        Booking b = buyingService.buy(user, product);

        System.out.println(b.getId());

    }

}
