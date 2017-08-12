package stepanyuk.productsandproducers.dao;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerDao {

    List<Producer> findAll();
    Producer findById(Long id);
    Producer findByIdWithProducts(Long id);
    void updateProducer(Producer producer);
    void saveProducer(Producer producer);
    void delete(Producer producer);
}
