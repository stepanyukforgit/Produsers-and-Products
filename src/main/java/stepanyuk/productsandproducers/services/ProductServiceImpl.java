package stepanyuk.productsandproducers.services;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProductDao;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;
/**
 *
 * @author stepanyuk
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService{

    @Resource(name = "productDao")
    private ProductDao productDao;
    
    @Override
    public void saveProduct(String productName, String productPrice, String productDescription, Producer producer) {
        productDao.saveProduct(
            new Product(productName, productDescription, Integer.valueOf(productPrice),producer));
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
    public void updateProduct(String productName, String productPrice, 
            String productDescription, String id, Producer producer) {
        Product product = findById(Long.valueOf(id));
        product.setName(productName);
        product.setPrice(Integer.parseInt(productPrice));
        product.setDescription(productDescription);
        product.setProducer(producer);
        productDao.updateProduct(product);
    }
    
    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
