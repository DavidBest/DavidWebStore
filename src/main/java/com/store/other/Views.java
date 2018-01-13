package com.store.other;

/**
 * {@link Views} contain classes for {@link com.fasterxml.jackson.annotation.JsonView}
 */
public class Views {

    public static class Public{}

    public static class Category{

        public static class ListOfProducts extends Public{}

    }

    public static class Product{

        public static class List extends Public{}

        public static class ListOfBooking extends Public{}

        public static class ListOfOverview extends Public{}

        public static class ListOfUser extends Public{}

    }

    public static class User{

        public static class UserList extends Public{}

        public static class ListOfBooking extends Public{}

        public static class ListOfOverviews extends Public{}

    }
}
