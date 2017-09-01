package stepanyuk.productsandproducers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stepanyuk.productsandproducers.model.Producer;
/**
 *
 * @author stepanyuk
 */
@Transactional
@Repository
public class ProducerDaoImpl implements ProducerDao{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    //Hibernate's sessions demo
    //see JPA approach in ProductDaoImpl
    
    @Override
    @Transactional(readOnly = true)
    public List<Producer> findAll() {
        Session session = entityManager.unwrap(Session.class);
        
        return session.createQuery("from Producer p").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Producer findById(Long id) {
        return entityManager.unwrap(Session.class).get(Producer.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producer findByIdWithProducts(Long id){
        Producer producer = entityManager.unwrap(Session.class).get(Producer.class, id);
        Hibernate.initialize(producer.getProducts());
        return producer;
    }

    @Override
    public long saveProducer(Producer producer) {
        long id = (Long) entityManager.unwrap(Session.class).save(producer);
        return id;
    }
    
    @Override
    public void updateProducer(Producer producer) {
        entityManager.unwrap(Session.class).update(producer);
    }

    @Override
    public void delete(Long id) {
        entityManager.unwrap(Session.class).delete(findById(id));
    }
    
    @Override
    public List<Producer> findByName(String searchProducers){
        String hql = "from Producer p where lower(p.name) like lower('%" + searchProducers + "%')";
        return entityManager.unwrap(Session.class).createQuery(hql).list();
    }
}