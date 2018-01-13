package com.store.database.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.store.other.Views.User.*;

@Entity
public class User implements Serializable {

    private String username;

    private List<Role> roles;

    private String password;

    private String mail;

    private BigDecimal credits;

    private List<Booking> bookings;

    private List<Overview> overviews;

    private String name;

    private String lastName;

    private Date age;

    private String country;

    private String address;

    private String livingAddress;

    private String phone;

    private String photo;

    public User() {
    }

    @Id
    @Pattern.List({
            @Pattern(regexp = "^[a-zA-Z].+", message = "first symbol have to be a-z or A-Z"),
            @Pattern(regexp = "[a-zA-Z0-9]{5,20}", message = "have to be between 5 and 20")
    })
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToMany
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @NotNull(message = "not written")
//    @Pattern.List({
//            @Pattern(regexp = "[a-zA-Z0-9]{7,20}", message = "have to be between 7 and 20"),
//            @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]))", message = "one upper case,lower case, numeric")
//    })
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(unique = true)
    @Pattern(regexp = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonView({ListOfBooking.class, UserList.class})
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonView({ListOfOverviews.class, UserList.class})
    public List<Overview> getOverviews() {
        return overviews;
    }

    public void setOverviews(List<Overview> overviews) {
        this.overviews = overviews;
    }

    //    @Max(value = 30, message = "name cant be more then 30 symbols")
    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @Max(value = 30, message = "last name cant be more then 30 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    //    @Max(value = 60, message = "country cant be more then 60 symbols")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //    @Max(value = 100, message = "country cant be more then 100 symbols")
    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    @Pattern(regexp = "\\+?[0-9](3,20)", message = "wrong phone number")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
