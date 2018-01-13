package com.store.component.service;

import com.store.database.dao.BookingService;
import com.store.database.dao.ProductService;
import com.store.database.dao.UserService;
import com.store.database.model.Booking;
import com.store.database.model.Product;
import com.store.database.model.Process;
import com.store.database.model.User;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuyingService {

    private final UserService userService;

    private final ProductService productService;

    private final BookingService bookingService;

    @Autowired
    public BuyingService(ProductService productService, UserService userService, BookingService bookingService) {
        this.productService = productService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    /**
     * @return {{@link Booking}} if transaction complete success
     **/

    //    @Transactional
    public Booking make(User user, Product product) throws DoesNotExistException {

        return make(user, product, 1);
    }
    //    @Transactional

    private Booking make(User user, Product product, int count) throws DoesNotExistException {

        if (product.getCount() == 0
                || user.getCredits().compareTo(product.getCost().multiply(new BigDecimal(count))) < 0)
            return null;

        Booking booking = new Booking();

        booking.setProduct(product);
        booking.setUser(user);
        booking.setDateOfOrder(new Date(System.currentTimeMillis()));
        booking.setProcess(new Process("in process"));

        user.setCredits(user.getCredits().subtract(product.getCost().multiply(new BigDecimal(count))));

        product.setCount(product.getCount() - 1);
        bookingService.create(booking);


        productService.update(product);
        userService.update(user);
        return booking;
    }

    public List<Booking> makeStorage(User user, Map<Product, Integer> products) {
        List<Booking> bookings = new ArrayList<>();

        products.forEach((product, integer) -> {
            try {
                bookings.add(make(user, product, integer));
            } catch (DoesNotExistException e) {
                e.printStackTrace();
                bookings.add(null);
            }
        });
        return bookings;
    }

}
