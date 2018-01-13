package com.store.database.dao.realization;

import com.store.database.dao.BookingService;
import com.store.database.dao.WebStoreService;
import com.store.database.model.Booking;
import com.store.database.repository.BookingRepository;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }


    @Override
    public Booking create(Booking elem) {
        return repository.save(elem);
    }

    @Override
    public Booking update(Booking elem) throws DoesNotExistException {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        repository.delete(aLong);
    }

    @Override
    public Booking get(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Iterable<Booking> getAll() {
        return null;
    }
}
