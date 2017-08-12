package stepanyuk.productsandproducers.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;
import stepanyuk.productsandproducers.services.ProducerService;
import stepanyuk.productsandproducers.services.ProductService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/producers")
public class ProducersController {
        
    @Resource(name = "productService")
    private ProductService productService;
    
    @Resource(name = "producerService")
    private ProducerService producerService;  
    
    @RequestMapping(value="/ProducersList", method=GET)
    public String showProducersList(Model model){
        model.addAttribute("producers", producerService.findAll());
        return "producers/ProducersList";
    }
    
    @RequestMapping(value="/ProducersList", params="producerId", method=GET)
    public String showProducersList(@RequestParam String producerId, Model model){
        producerService.delete(producerId);
        model.addAttribute("producers", producerService.findAll());
        return "producers/ProducersList";
    }

    @RequestMapping(value="/ProducersList", method=POST)
    public String showNewProducersList(
            @RequestParam("producerName")String producerName,
            @RequestParam("producerAddress")String producerAddress,
            @RequestParam("producerDescription")String producerDescription,
            Model model){
        producerService.saveProducer(producerName, producerAddress, producerDescription);
        model.addAttribute("producers", producerService.findAll());
        return "producers/ProducersList";
    }
    
    @RequestMapping(value="/ProducerInfo", method=GET)
    public String showProducerInfo(String producerId, Model model){
        model.addAttribute("producerId", producerService.findById(Long.valueOf(producerId)));
        return "producers/ProducerInfo";
    }
    
    @RequestMapping(value="/ProducerInfo", method=POST)
    public String showEditedProducerInfo(
            @RequestParam("producerName")String producerName,
            @RequestParam("producerAddress")String producerAddress,
            @RequestParam("producerDescription")String producerDescription,
            @RequestParam("producerId")String producerId,
            Model model){
        producerService.updateProducer(producerName, producerAddress, producerDescription, producerId);
        model.addAttribute("producerId", producerId);
        return "producers/ProducerInfo";
    }
    
    @RequestMapping(value="/ProducerEdit", method=GET)
    public String showProducerEdit(String producerId, Model model){
        model.addAttribute("producer", producerService.findById(Long.valueOf(producerId)));
        return "producers/ProducerEdit";
    }
    
    @RequestMapping("/ProducerProducts")
    public String showProducerProducts(String producerId, Model model){
        model.addAttribute("producer", producerService.findByIdWithProducts(Long.valueOf(producerId)));
        return "producers/ProducerProducts";
    }
    
    @RequestMapping(value="/ProducerProducts", method=POST)
    public String showNewProducerProducts(
            @RequestParam("productName")String productName,
            @RequestParam("productPrice")String productPrice,
            @RequestParam("productDescription")String productDescription,
            @RequestParam("producerId")String producerId,
            Model model){
        productService.saveProduct(productName, productDescription, 
                productPrice, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("producer", producerService.findByIdWithProducts(Long.valueOf(producerId)));
        return "producers/ProducerProducts";
    }
}
