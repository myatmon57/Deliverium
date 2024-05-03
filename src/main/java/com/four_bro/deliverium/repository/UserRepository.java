package com.four_bro.deliverium.repository;

import com.four_bro.deliverium.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
  UserModel findByUsernameAndPassword(String username, String password);

  List<UserModel> findByRole(Integer role);
}
