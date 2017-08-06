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
    Producer saveProducer(Producer producer);
    void delete(Producer product);
}
