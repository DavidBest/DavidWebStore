package com.store.database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.store.other.Views;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static com.store.other.Views.Product.*;


@Entity
public class Product implements Serializable {

    private long id;

    private String name;

    private List<String> photos;

    private BigDecimal cost;

    private int count;

    private String description;

    private List<Booking> sold;

    private List<Overview> overviews;

    private Category category;

    private List<User> users;

    private double rating;

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @NotNull
    @Pattern(regexp = "[A-Za-z0-9-\\s_]{3,20}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ElementCollection
    @CollectionTable(name = "Photo", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo")
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

//    public void addPhotos(Photo photo) {
//        this.photos.add(photo);
//    }


//    @NotNull
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Column(columnDefinition = "int(4) default 0")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(columnDefinition = "TEXT", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    @JsonView({ListOfBooking.class, Views.Product.List.class})
    public List<Booking> getSold() {
        return sold;
    }

    public void setSold(List<Booking> sold) {
        this.sold = sold;
    }

    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    @JsonView({ListOfOverview.class, Views.Product.List.class})
    public List<Overview> getOverviews() {
        return overviews;
    }

    public void setOverviews(List<Overview> overviews) {
        this.overviews = overviews;
    }

    @ManyToOne
//    @NotNull
    @JoinColumn(name = "category_name")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany
    @JoinTable(name = "user_product", joinColumns =
    @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @JsonView({ListOfUser.class, Views.Product.List.class})
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
