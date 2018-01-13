package com.store.database.dao;

import com.store.database.model.Booking;
import com.store.database.repository.BookingRepository;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public interface BookingService extends WebStoreService<Booking,Long> {

}
