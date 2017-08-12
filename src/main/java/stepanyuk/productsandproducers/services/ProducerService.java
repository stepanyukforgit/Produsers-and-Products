package stepanyuk.productsandproducers.services;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;

/**
 *
 * @author stepanyuk
 */
public interface ProducerService {
    
    void saveProducer(Producer producer);
    List<Producer> findAll();
    Producer findById(Long id);
    Producer findByIdWithProducts(Long id);
    void updateProducer(Producer producer);
    void delete(Producer producer);
}
