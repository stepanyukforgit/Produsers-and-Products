package stepanyuk.productsandproducers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        
        return entityManager.createQuery("from Product p").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if(product.getId() == null)
            entityManager.persist(product);
        else
            entityManager.merge(product);
        
        return product;
    }

    @Override
    public void delete(Long id) {
        
        entityManager.remove(findById(id));
    }
    
    @Override
    public List<Product> findByName(String searchProducts){
        String JPQL= "FROM Product p WHERE LOWER(p.name) LIKE LOWER('%" + searchProducts + "%')";
        
        return entityManager.createQuery(JPQL).getResultList();
    }
}
