package com.store;

import com.store.configuration.StoreConfiguration;
import com.store.database.dao.realization.UserServiceImpl;
import com.store.database.model.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.mockito.Mockito.*;

public class WebTest {

    @Test
    public void test(){
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.get("name")).thenReturn(new User());

        User user = userService.get("asd");
        System.out.println(user.getUsername());
    }

    @Test
    public void userControllerTest(){
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(StoreConfiguration.class);
//        UserController controller = annotation.getBean("userController",UserController.class);
    }

}
