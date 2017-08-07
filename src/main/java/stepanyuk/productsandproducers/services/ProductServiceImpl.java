package stepanyuk.productsandproducers.services;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProductDao;
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
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
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
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
    
    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
