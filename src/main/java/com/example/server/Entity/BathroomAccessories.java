package com.example.server.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "bathroomaccessories")
public class BathroomAccessories {
    @Id
    private Integer id;
    private String name;
    private Integer newprice;
    private Integer oldprice;
    private String status;
    private String origin;
    private String productcode;
    private String imagelink;
    private String brand;
    private String insurance;
}
