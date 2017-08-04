package stepanyuk.productsandproducers.dao;

import java.util.List;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProductDao {
    
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Product product);
}
