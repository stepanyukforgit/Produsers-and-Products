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
    
    @Override
    @Transactional(readOnly = true)
    public List<Producer> findAll() {
        //Hibernate's sessions demo
        Session session = entityManager.unwrap(Session.class);
        
        return session.createQuery("from Producer p").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Producer findById(Long id) {
        
        return entityManager.find(Producer.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producer findByIdWithProducts(Long id){
        Producer producer = entityManager.unwrap(Session.class).get(Producer.class, id);
        Hibernate.initialize(producer.getProducts());

        return producer;
    }

    @Override
    @Transactional(readOnly = true)
    public Producer findByIdWithLogo(Long id){
        Producer p = entityManager.find(Producer.class, id);
        if(p.getLogo() != null)
            p.getLogo().getPathToLogo();
        
        return p;
    }

    @Override
    public long saveProducer(Producer producer) {
        entityManager.persist(producer);
        
        return producer.getId();
    }
    
    @Override
    public void updateProducer(Producer producer) {
        
//        orphanRemoval DOESN'T WORK with unwruped session!!!
//        entityManager.unwrap(Session.class).update(producer);
        entityManager.merge(producer);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
    
    @Override
    public List<Producer> findByName(String searchProducers){
        String hql = "from Producer p where lower(p.name) like lower('%" + searchProducers + "%')";
        return entityManager.unwrap(Session.class).createQuery(hql).list();
    }
}