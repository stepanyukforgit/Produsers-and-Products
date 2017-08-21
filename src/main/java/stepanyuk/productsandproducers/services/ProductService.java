package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProductService {
    
    List<Product> findAll();
    List<Product> searchProducts(String searchProducts);
    Product findById(Long id);
    void saveProduct(String productName, String productPrice, String productDescription, Producer producer);
    void updateProduct(String productName, String productPrice, String productDescription, String id, Producer producer);
    void delete(Product product);
}