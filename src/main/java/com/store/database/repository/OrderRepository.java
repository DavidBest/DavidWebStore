package com.store.database.repository;

import com.store.database.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Booking,Long>{
}
