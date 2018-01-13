package com.store.other;

import org.springframework.stereotype.Component;

/**
 * convert rating from number value to UTF-8 star symbols
 */
@Component
public class RatingConvert {
    private static final int COUNT_OF_STARS = 5;

    public static String convert(double rating) {
        long r = rating < 1 ? 1 : Math.round(rating);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < COUNT_OF_STARS; i++)
            if (r > i)
                s.append("&#9733;");
            else
                s.append("&#9734;");
        return s.toString();
    }

}
