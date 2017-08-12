package stepanyuk.productsandproducers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import stepanyuk.productsandproducers.dao.ProducerDao;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;
/**
 *
 * @author stepanyuk
 */
@Service(value = "producerService")
public class ProducerServiceImpl implements ProducerService{
    
    @Resource(name = "producerDao")
    private ProducerDao producerDao;
    
    @Override
    public void saveProducer(Producer producer) {
        producerDao.saveProducer(producer);
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
        System.out.println("-----in findByIdWithProducts(service)");
        return producerDao.findByIdWithProducts(id);
    }
    
    @Override
    public void updateProducer(Producer producer) {
        producerDao.updateProducer(producer);
    }
    
    @Override
    public void delete(Producer producer) {
        producerDao.delete(producer);
    }
}
