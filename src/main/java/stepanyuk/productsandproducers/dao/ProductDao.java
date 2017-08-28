package stepanyuk.productsandproducers.dao;

import java.util.List;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProductDao {
    
    List<Product> findAll();
    List<Product> findByName(String searchProducts);
    Product findById(Long id);
    void updateProduct(Product product);
    long saveProduct(Product product);
    void delete(Product product);
}
