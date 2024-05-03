package com.four_bro.deliverium.repository;

import com.four_bro.deliverium.model.OrderModel;
import com.four_bro.deliverium.model.UserModel;

import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
  @Query(nativeQuery = true, value = "SELECT orders.id, orders.quantity, orders.status, user.username, user.email, product.product_name, product.price, product.product_type "
      +
      "FROM deliverium.orders " +
      "INNER JOIN user ON orders.userId = user.id " +
      "INNER JOIN product ON orders.productId = product.id " +
      "WHERE product.delete_flg = '0'")
  List<OrderModel> findAllOrders();

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO orders (`productId`, `userId`, `status`, `quantity`) VALUES (:productId, :userId, 1, :quantity)", nativeQuery = true)
  void orderCreate(Integer productId, Integer userId, Integer quantity);
}
