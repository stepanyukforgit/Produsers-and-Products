package stepanyuk.productsandproducers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.services.ProducerService;
import stepanyuk.productsandproducers.services.ProductService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/producers")
public class ProducersController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/producers_list", method = GET)
    public String showProducersList(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "producers/producers_list";
    }

    @RequestMapping(value = "/producers_list", params = "producerId", method = GET)
    public String deleteProducer(String producerId, Model model) {
        producerService.delete(producerId);
        model.addAttribute("producers", producerService.findAll());
        return "producers/producers_list";
    }

    @RequestMapping(value = "/producers_list", method = POST)
    public @ResponseBody Map<String, String> addNewProducer(@RequestBody String params) {
        long id = 0;
        
        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, String> reqBody = om.readValue(params, Map.class);
            id = producerService.saveProducer(reqBody.get("name"), reqBody.get("address"), reqBody.get("description"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Map<String, String> resBody = new HashMap();
        Producer prod = producerService.findById(id);
        resBody.put("name", prod.getName());
        resBody.put("address", prod.getAddress());
        resBody.put("description", prod.getDescription());
        resBody.put("id", prod.getId().toString());
        
        return resBody;
    }

//pass Producer
    @RequestMapping(value = "/producer_info/{producerId}", method = GET)
    public String showProducerInfo(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findById(producerId));
        return "producers/producer_info";
    }

    @RequestMapping(value = "/producer_edit/{producerId}", method = GET)
    public String showProducerEdit(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findById(producerId));
        return "producers/producer_edit";
    }

    @RequestMapping(value = "/producer_info", method = POST)
    public String editProducer(String producerName, String producerAddress,
            String producerDescription, String producerId, Model model) {
        producerService.updateProducer(producerName, producerAddress, producerDescription, producerId);
        model.addAttribute("producer", producerService.findById(Long.valueOf(producerId)));
        return "producers/producer_info";
    }

    @RequestMapping("/producer_products/{producerId}")
    public String showProducerProducts(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findByIdWithProducts(producerId));
        return "producers/producer_products";
    }

//replace String with Producer
    @RequestMapping(value = "/producer_products", method = POST)
    public String addProducerProduct(String productName, String productPrice,
            String productDescription, String producerId, Model model) {
        productService.saveProduct(productName, productDescription,
                productPrice, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("producer", producerService.findByIdWithProducts(Long.valueOf(producerId)));
        return "producers/producer_products";
    }
}
