package com.store.database.dao;

import com.store.database.model.Category;
import com.store.database.repository.CategoryRepository;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryService extends WebStoreService<Category, String> {

}
