package com.four_bro.deliverium.service;

import com.four_bro.deliverium.model.OrderModel;
import com.four_bro.deliverium.model.Order_Model_1;
import com.four_bro.deliverium.repository.OrderRepository;
import com.four_bro.deliverium.repository.OrderRepository_1;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

  @Autowired
  private OrderRepository_1 orderRepository_1;

  private OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<OrderModel> getAllOrders() {
    return orderRepository.findAllOrders();
  }

  public String changeStatusOfOrder(Integer id, Integer status) {
    Optional<Order_Model_1> optionalOrder = orderRepository_1.findById(id);
    if (optionalOrder.isPresent()) {
      Order_Model_1 orderModel_1 = optionalOrder.get();
      orderModel_1.setStatus(status);
      orderRepository_1.save(orderModel_1);
      return status == 0 ? "Decline Order" : "Confirm Order";
    } else {
      return "Order not found";
    }
  }

  public String orderCreate(Integer productId, Integer userId, Integer count) {
    orderRepository.orderCreate(productId, userId, count);
    return "Order Created Successfully";
  }
}
