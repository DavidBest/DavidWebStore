package com.store;

import com.store.configuration.StoreConfiguration;
import com.store.configuration.WebStoreMvcConfiguration;
import com.store.controller.web.IndexController;
import com.store.database.dao.*;
import com.store.database.dao.realization.UserServiceImpl;
import com.store.database.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
//@ContextConfiguration(classes = StoreApplication.class)
//@SpringBootTest(classes = WebStoreMvcConfiguration.class)
@ContextConfiguration(classes = {WebStoreMvcConfiguration.class}, loader = AnnotationConfigContextLoader.class)
//@ActiveProfiles("update")
public class WebTest {

//    @Autowired
//    private WebApplicationContext context;
//
    @Autowired
    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//
//        mock()
//        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//    }
//
//    @Test
//    public void some() throws Exception {
//        mvc.perform(post("/").with(csrf()));
//    }

//    @MockBean
//    private ProductService productService;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private OverviewService overviewService;
//
//    @MockBean
//    private BookingService bookingServiceImpl;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void fullControllerTest() throws Exception {
        given(categoryService.getAll()).willReturn(Collections.emptyList());
        mvc.perform(get("/catalog").accept(MediaType.ALL)).andExpect(status().isOk());
    }

    @Test
    public void mockControllerTest() {
        ModelAndView catalog = new ModelAndView("/catalog/catalog");
        catalog.addObject("categories", Collections.emptyList());

//        IndexController controller = mock(IndexController.class);
//        when(controller.catalog()).thenReturn(catalog);
//
//        catalog = controller.catalog();
//        System.out.println(catalog.getViewName());
    }

    @Test
    public void test() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.get("name")).thenReturn(new User());

        User user = userService.get("asd");
        System.out.println(user.getUsername());
    }

    @Test
    public void userControllerTest() {
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(StoreConfiguration.class);
//        UserController controller = annotation.getBean("userController",UserController.class);
    }

}
