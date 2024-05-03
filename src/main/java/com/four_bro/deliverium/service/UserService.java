package com.four_bro.deliverium.service;

import com.four_bro.deliverium.model.ProductModel;
import com.four_bro.deliverium.model.UserModel;
import com.four_bro.deliverium.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepo;

  public UserModel login(String username, String password) {
    UserModel user = userRepo.findByUsernameAndPassword(username, password);
    if (user == null) {
      return null;
    }
    return user;
  }

  public List<UserModel> getAllUsersbyStatus() {
    return userRepo.findByRole(1);
  }

  public String changeStatusOfUser(Integer id, Integer status) {
    Optional<UserModel> optionalUser = userRepo.findById(id);
    if (optionalUser.isPresent()) {
      UserModel userModel = optionalUser.get();
      userModel.setStatus(status);
      userRepo.save(userModel);
      return status == 0
        ? "Ban user successfully"
        : "Activate user successfully";
    } else {
      return "User not found";
    }
  }

  public String saveUser(@RequestBody UserModel request) {
    userRepo.save(request);
    return "User created successfully";
  }
}
