package com.four_bro.deliverium.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class OrderModel {

  @Id
  private Integer id;

  private String username;
  private String email;
  private String productName;
  private String price;
  private String productType;
  private Integer status;
  private Integer quantity;
}
