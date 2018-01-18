package com.store;

import com.store.configuration.JpaStoreConfiguration;
import com.store.configuration.StoreConfiguration;
import com.store.database.model.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

//import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {StoreConfiguration.class},loader = AnnotationConfigContextLoader.class)
//@ActiveProfiles("update")
public class RestTests {

    public RestTemplate template;

    @Test
    public void categoryRest(){
        template = new RestTemplate();

        Category fruit = new Category();
        fruit.setName("fruit");
        fruit.setInfo("fruit is eat");

        template.delete("http://localhost:8080/management/category/delete?name="+fruit.getName());

         HttpStatus status = template.postForObject("http://localhost:8080/management/category/create",fruit,HttpStatus.class);


        System.out.println(status.value());
    }

    @Test
    public void initialize() throws NoSuchMethodException, MalformedURLException {


        Map<String,Object> map = new HashMap<>();
        map.put("name","phone");

        template = new RestTemplate();

        ResponseEntity<Category> categoryResponseEntity = template.getForEntity("http://localhost:8080/management/catalog/get",Category.class,map);

        System.out.println(categoryResponseEntity.getBody().getName());
    }

//    @Test
//    public void jsonviewTest(){
//        template = new RestTemplate();
//
//        ResponseEntity<Category> categoryResponseEntity = template.getForEntity("http://localhost:8080/get2",Category.class,map);
//
//
//
//    }


}

