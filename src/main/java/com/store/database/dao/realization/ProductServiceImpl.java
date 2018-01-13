package com.store.database.dao.realization;

import com.store.database.dao.ProductService;
import com.store.database.model.Category;
import com.store.database.model.Product;
import com.store.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToOne;
import java.util.List;

@Repository
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;


    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product){
        return repository.save(product);
    }

    @Override
    public Product update(Product product){
        return repository.save(product);
    }

    @Override
    public void delete(Long aLong) {
        repository.delete(aLong);
    }

    @Override
    public Product get(Long aLong) {
        return repository.findOne(aLong);
    }

    public List<Product> getAll(){
        return (List<Product>)repository.findAll();
    }

    public Product get(long id){
        return repository.findOne(id);
    }

    public List<Product> getByCategory(Category category, int first, int count){
        return repository.findByCategory(category,new PageRequest(first,count));
    }

    @Override
    public List<Product> getByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public List<Product> getByCategoryName(String name) {
        Category c = new Category();
        c.setName(name);
        return repository.findByCategory(c);
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return repository.findByCategory(category);
    }
}
