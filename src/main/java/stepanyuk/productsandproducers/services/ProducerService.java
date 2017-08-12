package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerService {
    
    void saveProducer(String producerName, String producerAddress, String producerDescription);
    List<Producer> findAll();
    Producer findById(Long id);
    Producer findByIdWithProducts(Long id);
    void updateProducer(String producerName, String producerAddress, String producerDescription, String id);
    void delete(String id);
}
