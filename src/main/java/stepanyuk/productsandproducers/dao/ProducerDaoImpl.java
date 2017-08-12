package stepanyuk.productsandproducers.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stepanyuk.productsandproducers.model.Producer;
/**
 *
 * @author stepanyuk
 */
@Transactional
@Repository(value = "producerDao")
public class ProducerDaoImpl implements ProducerDao{
    
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Producer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Producer p").list();
    }

    @Override
    public Producer findById(Long id) {
        return sessionFactory.getCurrentSession().get(Producer.class, id);
    }
    
    @Override
    public Producer findByIdWithProducts(Long id){
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        Hibernate.initialize(producer.getProducts());
        return producer;
    }

    @Override
    public void saveProducer(Producer producer) {
        sessionFactory.getCurrentSession().save(producer);
    }
    
    @Override
    public void updateProducer(Producer producer) {
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void delete(Producer producer) {
        sessionFactory.getCurrentSession().delete(producer);
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
