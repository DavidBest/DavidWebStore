package com.store.component.service;

import com.store.database.dao.OverviewService;
import com.store.database.dao.ProductService;
import com.store.database.model.Booking;
import com.store.database.model.Overview;
import com.store.database.model.Product;
import com.store.exception.DoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final OverviewService overviewService;

    private final ProductService productService;

    @Autowired
    public RatingService(OverviewService overviewService, ProductService productService) {
        this.overviewService = overviewService;
        this.productService = productService;
    }

    public void rate(String comment, int rating, Booking booking) {
        Product p = booking.getProduct();

        Overview overview = new Overview();
        overview.setProduct(p);
        overview.setRating(rating);
        overview.setUser(booking.getUser());
        overview.setDescription(comment);

        overviewService.create(overview);

        double[] rate =new double[]{0};

        List<Overview> overviews = p.getOverviews();
        overviews.forEach(overview1 -> rate[0]+=overview1.getRating());

        rate[0]/=overviews.size();

        p.setRating(rate[0]);
        try {
            productService.update(p);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }


    }

}
