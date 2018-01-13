package com.store.database.dao.realization;

import com.store.database.dao.OverviewService;
import com.store.database.dao.WebStoreService;
import com.store.database.model.Overview;
import com.store.database.repository.OverviewRepository;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OverviewServiceImpl implements OverviewService {

    private final OverviewRepository repository;

    @Autowired
    public OverviewServiceImpl(OverviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Overview create(Overview elem) {
        return repository.save(elem);
    }

    @Override
    public Overview update(Overview elem) throws DoesNotExistException {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        repository.delete(aLong);
    }

    @Override
    public Overview get(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Iterable<Overview> getAll() {
        return null;
    }
}
