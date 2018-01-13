package com.store.database.dao.realization;

import com.store.database.dao.CategoryService;
import com.store.database.model.Category;
import com.store.database.repository.CategoryRepository;
import com.store.database.repository.ProductRepository;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public Category create(Category elem) {
        elem.getProducts().forEach(product -> product.setCategory(elem));
//        Category category = repository.save(elem);
        return repository.save(elem);
    }

    @Override
    public Category update(Category elem) throws DoesNotExistException {
        return repository.save(elem);
    }

    @Override
    public void delete(String s) {
        repository.delete(s);
    }

    @Override
    public Category get(String s) {
        return repository.findOne(s);
    }

    @Override
    public Iterable<Category> getAll() {
        return repository.findAll();
    }


}
