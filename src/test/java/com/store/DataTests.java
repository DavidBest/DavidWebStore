package com.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.configuration.JpaStoreConfiguration;
import com.store.database.dao.*;
import com.store.database.model.*;
import com.store.database.repository.RoleRepository;
import com.store.exception.DoesNotExistException;
import com.store.other.Views;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaStoreConfiguration.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("update")
public class DataTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OverviewService overviewService;

//    @Before
//    public void baseCreation(){
//        Role role = new Role("ROLE_USER");
//
//        roleRepository.save(role);
//    }

    @Test
    public void viewCategory() throws JsonProcessingException {

        Category c = categoryService.get("phone");

        String s = new ObjectMapper().writerWithView(Views.Category.ListOfProducts.class).writeValueAsString(c);

        System.out.println(s);

    }

    @Test
    public void cascadeCategoryProduct(){
        Category category = new Category();
        category.setName("robot");
        category.setInfo("move and dance");

        Product product = new Product();
        product.setName("icoljaa");
        product.setCost(new BigDecimal(1000));
        product.setCount(1223);
        product.setDescription("robot amazing");

        category.setProducts(Collections.singletonList(product));

//        categoryService.delete("robot");
        categoryService.create(category);
    }

    @Test
    public void categoryCreation() {
        Category phone = new Category();

        phone.setName("phone");

        phone.setInfo("This is phone");

        System.out.println(phone.getName());

        categoryService.create(phone);

//        System.out.println();
    }

    @Test
    public void goodCreation() {

        Product iPhone = new Product();
        iPhone.setName("iphone 5s");
        iPhone.setDescription("everyone know about iphone 5s, so i think it is not to be presented");
        iPhone.setCount(35);
        iPhone.setCost(new BigDecimal(200));
        iPhone.setPhotos(Arrays.asList("https://www.imore.com/sites/imore.com/files/styles/large/public/field/image/2014/03/topic_iphone_5s.png?itok=A24vBPpa",
                "https://www.retail-loyalty.org/upload/medialibrary/b11/b1196168cea7a323ae3174b344016129.jpg"));
        iPhone.setRating(5);

        iPhone.setCategory(categoryService.get("phone"));

        productService.create(iPhone);
    }

    @Test
    public void mergeCategory() throws DoesNotExistException {


        Category category = new Category();
        category.setName("phone");
        category.setInfo("phone category");

//        categoryService.delete("phone");

        Product product= new Product();
        product.setName("nokia 7-10");
        product.setCount(10);
        product.setCost(new BigDecimal(100));

        category.setProducts(Collections.singletonList(product));

        categoryService.create(category);

    }

    @Test
    public void userCreation() {
        User david = new User();

        david.setRoles(Collections.singletonList(new Role("ROLE_USER")));

        String password = "qwerQwer123";

        if (Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])){7,20}").matcher(password).matches())
            david.setPassword(new BCryptPasswordEncoder().encode(password));
        else
            david.setPassword("123");

        david.setUsername("david");

        david.setMail("madhawk007@yandex.ru");

        david.setCredits(new BigDecimal(1000));

        userService.create(david);
    }

    @Test
    public void bookingCreation(){
        Booking booking = new Booking();

        booking.setDateOfOrder(new Date(System.currentTimeMillis()));
        booking.setUser(userService.get("david"));
        booking.setProduct(productService.get(1L));

        bookingService.create(booking);
    }

    @Test
    public void overviewCreation(){
        Overview overview = new Overview();

        overview.setBooking(bookingService.get(1L));
        overview.setDescription("it is amazing!");
        overview.setUser(userService.get("david"));
        overview.setRating(3);
        overview.setProduct(productService.get(1L));

        overviewService.create(overview);
    }

    @Test
    public void gettingTest(){
        User user = userService.get("david");
        System.out.println(user.getBookings().get(0).getOverview().getProduct().getCategory().getName());
    }

    @Test
    public void removeTest(){
        categoryService.delete("phone");

    }

}
