package stepanyuk.productsandproducers.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProducerDao;
import stepanyuk.productsandproducers.model.Producer;
/**
 *
 * @author stepanyuk
 */
@Service
public class ProducerServiceImpl implements ProducerService{
    
    @Autowired
    private ProducerDao producerDao;
    
    @Override
    public long saveProducer(String producerName, String producerAddress, String producerDescription) {
        long id =  (Long) producerDao.saveProducer(new Producer(producerName, producerAddress, producerDescription));
        return id;
    }

    @Override
    public List<Producer> findAll() {
        return producerDao.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return producerDao.findById(id);
    }
    
    @Override
    public Producer findByIdWithProducts(Long id){
        return producerDao.findByIdWithProducts(id);
    }
    
    @Override
    public void updateProducer(String producerName, String producerAddress, 
            String producerDescription, String id) {
        Producer producer = findById(Long.valueOf(id));
        producer.setName(producerName);
        producer.setAddress(producerAddress);
        producer.setDescription(producerDescription);
        producerDao.updateProducer(producer);
    }
    
    @Override
    public void delete(String id) {
        producerDao.delete(Long.valueOf(id));
    }
    
    @Override
    public List<Producer> searchProducers(String searchProducers){
        return producerDao.findByName(searchProducers);
    }
}
