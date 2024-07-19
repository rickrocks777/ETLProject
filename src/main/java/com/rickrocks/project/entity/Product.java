package com.rickrocks.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_Table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //The strategy attribute is set to GenerationType.IDENTITY, which means that the database will automatically assign an ID value upon insertion.
    private int id;
    private String name;
    private int quantity;
    private double price;
}
