package com.ecatom.commons.model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;


    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)//Specifies the time format (DATE, TIME or TIMESTAMP for both)
    private Date createdAt;

    @Transient //Indicates that this attribute  is not persisted in the DB
    //Configuration line to visualize the used port by Ribbon load balancer
    @Value("${server.port}")
    private Integer port;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    //Creates a Date before persisting the info
    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
