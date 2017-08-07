package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProductService {
    
    void saveProduct(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void updateProduct(Product product);
    void delete(Product product);
}
