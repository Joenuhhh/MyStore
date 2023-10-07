package com.gcu.Modelss;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//class dedicated to an item created after a user purchases something. It pulls properties from the user, 
//and properties from the products in his cart, and adds them to a new table with a time stamp to log the order. - Jonah

@EntityScan
@Table(name = "order_logs")
public class OrderLog {

    @Id
    private Integer id;

    @Column(value = "Email")
    private String email;

    @Column(value = "FirstName")
    private String firstName;

    @Column(value = "LastName")
    private String lastName;

    @Column(value = "Address")
    private String address;

    @Column(value = "ItemsBought")
    private String itemsBought;

    @Column(value = "Total")
    private Integer total;

    @Column(value = "timestamp")
    private LocalDateTime timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(String itemsBought) {
        this.itemsBought = itemsBought;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
