package com.store.database.dao;

import com.store.database.model.Category;
import com.store.database.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService extends WebStoreService<Product,Long> {

    List<Product> getByNameContaining(String name);

    List<Product> getByCategoryName(String name);

    List<Product> getByCategory(Category category);
}
