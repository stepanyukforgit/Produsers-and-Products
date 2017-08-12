package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProductService {
    
    void saveProduct(String productName, String productPrice, String productDescription, Producer producer);
    List<Product> findAll();
    Product findById(Long id);
    void updateProduct(String productName, String productPrice, String productDescription, String id, Producer producer);
    void delete(Product product);
}