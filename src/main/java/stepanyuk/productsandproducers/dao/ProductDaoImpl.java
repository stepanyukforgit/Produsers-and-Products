package stepanyuk.productsandproducers.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
@Transactional
@Repository
public class ProductDaoImpl implements ProductDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product p").list();
    }

    @Override
    @Transactional(readOnly = true)
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
    
    @Override
    public List<Product> findByName(String searchProducts){
        String hql = "from Product p where lower(p.name) like lower('%" + searchProducts + "%')";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
