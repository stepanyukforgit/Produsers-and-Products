package stepanyuk.productsandproducers.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import stepanyuk.productsandproducers.model.Product;
import stepanyuk.productsandproducers.services.ProducerService;
import stepanyuk.productsandproducers.services.ProductService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/products")
public class ProductsController {
    
    @Resource(name = "productService")
    private ProductService productService;
    
    @Resource(name = "producerService")
    private ProducerService producerService;  
    
    @RequestMapping(value="/ProductsList", method=GET)
    public String showProductsList(Model model){
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/ProductsList";
    }
    
    @RequestMapping(value="/ProductsList", params="productId", method=GET)
    public String showProductsList(@RequestParam String productId, Model model){
        productService.delete(productService.findById(Long.valueOf(productId)));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/ProductsList";
    }
    
    @RequestMapping(value="/ProductsList", method=POST)
    public String showNewProductsList(
            @RequestParam("productName")String productName,
            @RequestParam("productPrice")String productPrice,
            @RequestParam("productDescription")String productDescription,
            @RequestParam("producerId")String producerId,
            Model model){
          
        productService.saveProduct(
                new Product(productName, productDescription, 
                        Integer.valueOf(productPrice),
                        producerService.findById(Long.valueOf(producerId))));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/ProductsList";
    }
    
    @RequestMapping(value="/ProductInfo", method=GET)
    public String showProductInfo(String productId, Model model){
        model.addAttribute("productId", productService.findById(Long.valueOf(productId)));
        return "products/ProductInfo";
    }
    
    @RequestMapping(value="/ProductInfo", method=POST)
    public String showEditedProductInfo(
            @RequestParam("productName")String productName,
            @RequestParam("productPrice")String productPrice,
            @RequestParam("productDescription")String productDescription,
            @RequestParam("producerId")String producerId,
            @RequestParam("productId")String productId,
            Model model){
        Product product = productService.findById(Long.valueOf(productId));
        product.setName(productName);
        product.setPrice(Integer.parseInt(productPrice));
        product.setDescription(productDescription);
        product.setProducer(producerService.findById(Long.valueOf(producerId)));
        productService.updateProduct(product);
        model.addAttribute("productId", product);
        return "products/ProductInfo";
    }
    
    @RequestMapping(value="/ProductEdit", method=GET)
    public String showProductEdit(String productId, Model model){
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("product", productService.findById(Long.valueOf(productId)));
        return "products/ProductEdit";
    }
}
