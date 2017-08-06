package stepanyuk.productsandproducers.services;

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
    public Producer saveProducer(Producer producer) {
        producerDao.saveProducer(producer);
        return producer;
    }
}
