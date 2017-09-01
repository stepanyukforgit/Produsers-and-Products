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

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/products_list", method = GET)
    public String showProductsList(Model model) {
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/products_list";
    }

    @RequestMapping(value = "/products_list", params = "productId", method = GET)
    public String deleteProduct(String productId, Model model) {
        productService.delete(Long.valueOf(productId));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/products_list";
    }

    @RequestMapping(value = "/products_list", method = POST)
    @ResponseBody
    public  Map<String, String> addNewProduct(@RequestBody String params) {
        Product product = null;
        
        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, String> reqBody = om.readValue(params, Map.class);
            product = productService.saveProduct(reqBody.get("name"), reqBody.get("description"),
                    reqBody.get("price"), producerService.findById(Long.valueOf(reqBody.get("producerId"))));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        Map<String, String> resBody = new HashMap();
        resBody.put("name", product.getName());
        resBody.put("price", product.getPrice().toString());
        resBody.put("description", product.getDescription());
        resBody.put("producerName", product.getProducer().getName());
        resBody.put("id", product.getId().toString());
        return resBody;
    }

    @RequestMapping(value = "/product_info/{productId}", method = GET)
    public String showProductInfo(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productService.findById(productId));
        return "products/product_info";
    }

    @RequestMapping(value = "/product_edit/{productId}", method = GET)
    public String showProductEdit(@PathVariable Long productId, Model model) {
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("product", productService.findById(productId));
        return "products/product_edit";
    }

    @RequestMapping(value = "/product_info", method = POST)
    public String editProduct(String productName, String productPrice, String productDescription,
            String producerId, String productId, Model model) {
        productService.updateProduct(productName, productPrice,
                productDescription, productId, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("product", productService.findById(Long.valueOf(productId)));
        return "products/product_info";
    }
}
