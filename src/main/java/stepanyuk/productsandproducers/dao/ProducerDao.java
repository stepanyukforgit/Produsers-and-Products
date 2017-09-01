package stepanyuk.productsandproducers.dao;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerDao {

    List<Producer> findAll();
    List<Producer> findByName(String searchProducers);
    Producer findById(Long id);
    Producer findByIdWithProducts(Long id);
    void updateProducer(Producer producer);
    long saveProducer(Producer producer);
    void delete(Long id);
}
