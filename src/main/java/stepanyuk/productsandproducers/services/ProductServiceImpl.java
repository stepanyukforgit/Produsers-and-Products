package stepanyuk.productsandproducers.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProductDao;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;
/**
 *
 * @author stepanyuk
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;
    
    @Override
    public Product saveProduct(String productName, String productDescription, String productPrice, Producer producer) {
        
        return productDao.saveOrUpdate(
            new Product(productName, productDescription, new BigDecimal(productPrice),producer));
    }
 
    @Override
    public List<Product> findAll() {
        
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        
        return productDao.findById(id);
    }
    
    
    @Override
    public Product updateProduct(String productName, String productPrice, 
            String productDescription, String id, Producer producer) {
        Product product = findById(Long.valueOf(id));
        product.setName(productName);
        product.setPrice(new BigDecimal(productPrice));
        product.setDescription(productDescription);
        product.setProducer(producer);

        return productDao.saveOrUpdate(product);
    }
    
    @Override
    public void delete(Long id) {
        
        productDao.delete(id);
    }
    
    @Override
    public List<Product> searchProducts(String searchProducts){
        
        return productDao.findByName(searchProducts);
    }
}
