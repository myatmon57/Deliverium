package com.four_bro.deliverium.controller;

import com.four_bro.deliverium.model.ProductModel;
import com.four_bro.deliverium.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @Controller
@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/productlist")
  public List<ProductModel> allProducts(
      HttpServletRequest request,
      Model model) {
    List<ProductModel> data = productService.getAllProducts();
    return data;
  }

  @PostMapping(value = "/create_product")
  public String createProductData(@RequestBody ProductModel request) {
    String msg = productService.saveProduct(request);
    return msg;
  }

  @PostMapping(value = "/get_product")
  public Optional<ProductModel> editProductData(
      @RequestBody Map<String, Object> param) {
    String productIdStr = (String) param.get("productId");
    if (productIdStr == null) {
      return Optional.empty();
    }
    try {
      Integer productId = Integer.valueOf(productIdStr);
      Optional<ProductModel> data = productService.getAllProductsById(
          productId);
      return data;
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

  @PostMapping(value = "/edit_product")
  public String editProductData(@RequestBody ProductModel request) {
    String msg = productService.editProduct(request);
    return msg;
  }

  @PostMapping(value = "/delete_product")
  public String deleteProductData(@RequestBody ProductModel request) {
    String msg = productService.deleteProduct(request);
    return msg;
  }

  @GetMapping(value = "/get_one_product")
  public ProductModel getOneProduct(@RequestParam ProductModel id) {
    log.info("Id" + id);
    ProductModel product = productService.getOneProduct(id);
    return product;
  }

}
