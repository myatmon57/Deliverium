package com.four_bro.deliverium.controller;

import com.four_bro.deliverium.model.UserModel;
import com.four_bro.deliverium.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public UserModel login(
      @RequestBody Map<String, Object> param,
      HttpServletRequest request,
      Model model) {
    String username = (String) param.get("username");
    String password = (String) param.get("password");

    UserModel loginUser = userService.login(username, password);
    if (loginUser != null) {
      String uuid = UUID.randomUUID().toString();
      HttpSession session = request.getSession();
      session.setAttribute("AUTH_CHECK", uuid);
      session.setAttribute("USER_NAME", username);
      session.setAttribute("USER_ID", loginUser.getId());
      if (loginUser.getRole() == 0) {
        session.setAttribute("USER_ROLE", "admin");
      } else if (loginUser.getRole() == 1 && loginUser.getStatus() != 0) {
        session.setAttribute("USER_ROLE", "user");
      }
      return loginUser;
    }
    return null;
  }

  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {
    log.info("Work");
    HttpSession session = request.getSession();
    session.invalidate();
  }
}
