package com.four_bro.deliverium.service;

import com.four_bro.deliverium.model.ProductModel;
import com.four_bro.deliverium.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<ProductModel> getAllProducts() {
    return productRepository.findByDeleteFlg(0);
  }

  public Optional<ProductModel> getAllProductsById(Integer id) {
    return productRepository.findByIdAndDeleteFlg(id, 0);
  }

  public String saveProduct(@RequestBody ProductModel request) {
    productRepository.save(request);
    return "Product created successfully";
  }

  public String editProduct(@RequestBody ProductModel request) {
    Optional<ProductModel> optionalProduct = productRepository.findByIdAndDeleteFlg(
      request.getId(),
      0
    );
    if (optionalProduct.isPresent()) {
      ProductModel productModel = optionalProduct.get();
      productModel.setProductName(request.getProductName());
      productModel.setPrice(request.getPrice());
      productModel.setProductType(request.getProductType());
      productRepository.save(productModel);
      return "Product updated successfully";
    } else {
      return "Product not found";
    }
  }

  public ProductModel getOneProduct(@RequestBody ProductModel request) {
    ProductModel optionalProduct = productRepository.findOneProductByIdAndDeleteFlg(
      request.getId(),
      0
    );
    return optionalProduct;
  }

  public String deleteProduct(@RequestBody ProductModel request) {
    Optional<ProductModel> optionalProduct = productRepository.findByIdAndDeleteFlg(
      request.getId(),
      0
    );
    if (optionalProduct.isPresent()) {
      ProductModel productModel = optionalProduct.get();
      productModel.setDeleteFlg(1);
      productRepository.save(productModel);
      return "Product deleted successfully";
    } else {
      return "Product not found";
    }
  }
}
