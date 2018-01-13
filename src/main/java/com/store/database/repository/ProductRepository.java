package com.store.database.repository;

import com.store.database.model.Category;
import com.store.database.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll(Pageable pageable);

    List<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findByCategory(Category category);

    List<Product> findByNameContaining(String name);

}
