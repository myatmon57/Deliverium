package com.four_bro.deliverium.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class WebController {

  @GetMapping("/")
  public String auth(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      return "dashboard/dashboard";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      return "client_side/home";
    }
    return "auth/login";
  }

  @GetMapping("/user/create")
  public String registerUser(HttpServletRequest request, Model model) {
      return "users/registeration";
  }


  @GetMapping("/dashboard")
  public String home(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "dashboard/dashboard";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/product")
  public String allProducts(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("activeTab", "products");
      model.addAttribute("adminName", userName);
      return "product/list";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/product/create")
  public String createProduct(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("activeTab", "products");
      model.addAttribute("adminName", userName);
      model.addAttribute("mode", "create");
      return "product/create"; // Return the logical view name
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/product/edit")
  public String editProduct(
    HttpServletRequest request,
    Model model,
    @RequestParam Integer id
  ) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("activeTab", "products");
      model.addAttribute("adminName", userName);
      model.addAttribute("productId", id);
      model.addAttribute("mode", "edit");
      return "product/create"; // Return the logical view name
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/orders")
  public String order(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("activeTab", "orders");
      model.addAttribute("adminName", userName);
      return "orders/orders";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/users")
  public String users(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("activeTab", "users");
      model.addAttribute("adminName", userName);
      return "users/user";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      String userName = (String) session.getAttribute("USER_NAME");
      model.addAttribute("adminName", userName);
      model.addAttribute("activeTab", "dashboard");
      return "error/404";
    }
    return "auth/login";
  }

  // User Route
  // Client Side Route
  @GetMapping("/user_home")
  public String user_home(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      return "client_side/home";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      return "error/404";
    }
    return "auth/login";
  }

  @GetMapping("/product_page")
  public String product_page(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "user"
    ) {
      session.getAttribute("AUTH_CHECK");
      return "client_side/product";
    }
    if (
      session != null &&
      session.getAttribute("AUTH_CHECK") != null &&
      session.getAttribute("USER_ROLE") == "admin"
    ) {
      session.getAttribute("AUTH_CHECK");
      return "error/404";
    }
    return "auth/login";
  }
}
