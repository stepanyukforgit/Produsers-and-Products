package stepanyuk.productsandproducers.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
@Transactional
@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao{
    
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product p").list();
    }

    @Override
    public Product findById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void saveProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }
    
    @Override
    public void updateProduct(Product product){
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
