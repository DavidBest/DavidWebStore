package com.store.database.repository;

import com.store.database.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,String> {
//    public Iterable<Category> findAllByRoot(boolean isRoot);
}
