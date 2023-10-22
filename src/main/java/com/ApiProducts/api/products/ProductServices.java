package com.ApiProducts.api.products;

import com.ApiProducts.api.products.entity.ProductEntity;
import com.ApiProducts.api.products.repository.ProductRepository;
import com.ApiProducts.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

    //? Generate message Response
    private MessageResponse messageResponse(String message) {
        return new MessageResponse(message);
    }

    public List<ProductEntity> FindAllProductService() {
        return productRepository.findAll();
    }

    public ResponseEntity<?> FindByIdProductService(Long id) {
        Optional<ProductEntity> response = productRepository.findById(id);
        if (response.isEmpty()) {
            MessageResponse message = messageResponse("El producto con el id " + id + " no existe!");
            return ResponseEntity.status(404).body(message);
        }
        return ResponseEntity.ok(response.get());
    }

    public ResponseEntity<?> CreateProductService(ProductEntity product) {
        ProductEntity productCreated = productRepository.save(product);
        MessageResponse message = messageResponse("Producto " + productCreated.getName() + " creado " +
                "correctamente");
        return ResponseEntity.status(201).body(message);
    }

    public ResponseEntity<?> UpdateProductService(Long id, ProductEntity product) {
        Optional<ProductEntity> productFound = productRepository.findById(id);
        if (productFound.isEmpty()) {
            MessageResponse errorMessage = messageResponse("El producto con el id " + id + " no existe!");
            return ResponseEntity.status(404).body(errorMessage);
        }
        ProductEntity product1 = productRepository.save(product);
        MessageResponse message = messageResponse("Producto " + product1.getName() + " actualizado correctamente");
        return ResponseEntity.status(201).body(message);
    }

    public ResponseEntity<?> DeleteProductService(Long id) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product == null) {
            MessageResponse messageError = messageResponse("El producto con el id " + id + " no existe!");
            return ResponseEntity.status(404).body(messageError);
        }
        productRepository.deleteById(id);
        MessageResponse message = messageResponse("Producto " + product.getName() + " eliminado");
        return ResponseEntity.status(200).body(message);

    }
}
