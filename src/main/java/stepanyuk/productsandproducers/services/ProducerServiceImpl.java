package stepanyuk.productsandproducers.services;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProducerDao;
import stepanyuk.productsandproducers.model.Producer;
/**
 *
 * @author stepanyuk
 */
@Service(value = "producerService")
public class ProducerServiceImpl implements ProducerService{
    
    @Resource(name = "producerDao")
    private ProducerDao producerDao;
    
    @Override
    public void saveProducer(String producerName, String producerAddress, String producerDescription) {
        producerDao.saveProducer(new Producer(producerName, producerAddress, producerDescription));
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
        producerDao.delete(findById(Long.valueOf(id)));
    }
}
