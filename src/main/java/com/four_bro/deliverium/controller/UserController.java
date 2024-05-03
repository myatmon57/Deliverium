package com.four_bro.deliverium.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.four_bro.deliverium.model.UserModel;
import com.four_bro.deliverium.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RestController

public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register_user")
  public String UserRegister(@RequestBody  UserModel request) {
    log.info("status*****************");
    String msg = userService.saveUser(request);
    return msg;
  }
  

  @GetMapping(value = "/userlist")
  public List<UserModel> allUsers(HttpServletRequest request, Model model) {
    List<UserModel> data = userService.getAllUsersbyStatus();
    return data;
  }

  @PostMapping(value = "/change_status")
  public String changeStatusOfUser(@RequestBody Map<String, Object> request) {
    Integer id = (Integer) request.get("id");
    Integer status = (Integer) request.get("status");
    String data = userService.changeStatusOfUser(Integer.valueOf(id), Integer.valueOf(status));
    return data;
  }

}
