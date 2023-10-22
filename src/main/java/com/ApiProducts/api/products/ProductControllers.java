package com.ApiProducts.api.products;

import com.ApiProducts.api.products.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/products")
public class ProductControllers {
    @Autowired
    private ProductServices productServices;

    @GetMapping
    public List<ProductEntity> FindAllProductController() {
        return productServices.FindAllProductService();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> FindByIdProductController(@PathVariable Long id) {
        return productServices.FindByIdProductService(id);
    }

    @PostMapping()
    public ResponseEntity<?> CreateProductController(@RequestBody ProductEntity product) {
        return productServices.CreateProductService(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateProductController(@PathVariable Long id,@RequestBody ProductEntity product) {
        return productServices.UpdateProductService(id,product);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteProductController(@PathVariable Long id) {
        return productServices.DeleteProductService(id);
    }
}
