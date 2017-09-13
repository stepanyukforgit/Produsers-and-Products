package stepanyuk.productsandproducers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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

    @RequestMapping(value = "/producers_list", method = POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String addNewProducer(@RequestBody String params) {
        String jsonProducer = "";
        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, String> reqBody = om.readValue(params, Map.class);
            long id = producerService.saveProducer(reqBody.get("name"), reqBody.get("address"), reqBody.get("description"));
            jsonProducer = om.writeValueAsString(producerService.findById(id));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return jsonProducer;
    }

    @RequestMapping(value = "/producer_info/{producerId}", method = GET)
    public String showProducerInfo(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findByIdWithLogo(producerId));
        
        return "producers/producer_info";
    }

    @RequestMapping(value = "/producer_edit/{producerId}", method = GET)
    public String showProducerEdit(@PathVariable Long producerId, Model model) {
        model.addAttribute("producer", producerService.findByIdWithLogo(producerId));
        
        return "producers/producer_edit";
    }
    
    @ResponseBody
    @RequestMapping(value = "/producer_edit/upload_logo", method = POST)
    public String uploadLogo(@RequestPart MultipartFile logo, 
                             HttpServletRequest request) {
        
        if(!logo.isEmpty()){
            String pathToDir = request.getServletContext().getRealPath("resources");
            
            return producerService.uploadLogo(logo, pathToDir);
        }
            
        return "";
    }

    @RequestMapping(value = "/producer_info", method = POST)
    public String editProducer(String producerName, String producerAddress, String producerDescription,
            String producerLogo, String producerId, Model model, HttpServletRequest request) {
        String pathToDir = request.getServletContext().getRealPath("");
        producerService.updateProducer(producerName, producerAddress, producerDescription, producerLogo, pathToDir, producerId);
        Producer p = producerService.findByIdWithLogo(Long.valueOf(producerId));
        model.addAttribute("producer", p);
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
