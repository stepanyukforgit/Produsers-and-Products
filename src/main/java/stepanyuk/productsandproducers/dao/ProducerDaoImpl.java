package stepanyuk.productsandproducers.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Producer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Producer p").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Producer findById(Long id) {
        return sessionFactory.getCurrentSession().get(Producer.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
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
    
    @Override
    public List<Producer> findByName(String searchProducers){
        String hql = "from Producer p where lower(p.name) like lower('%" + searchProducers + "%')";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}