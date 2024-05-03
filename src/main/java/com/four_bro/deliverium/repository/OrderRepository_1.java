package com.four_bro.deliverium.repository;

import com.four_bro.deliverium.model.Order_Model_1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepository_1
  extends JpaRepository<Order_Model_1, Integer> {}
