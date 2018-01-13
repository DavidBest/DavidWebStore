package com.store.database.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.store.other.Views;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * {@link Category} is type of {@link Product}, that containing it.
 **/
@Entity
public class Category implements Serializable {

    private String name;

    private String info;


    private List<Product> products;

    public Category() {
    }

    @Id
    @Column(length = 30, name = "category_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(length = 500)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JsonView(Views.Category.ListOfProducts.class)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
