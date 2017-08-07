package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerService {
    
    void saveProducer(Producer producer);
    List<Producer> findAll();
    Producer findById(Long id);
    void updateProducer(Producer producer);
    void delete(Producer producer);
    
}
