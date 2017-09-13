package stepanyuk.productsandproducers.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stepanyuk.productsandproducers.dao.ProducerDao;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.ProducerLogo;
/**
 *
 * @author stepanyuk
 */
@Service
public class ProducerServiceImpl implements ProducerService{
    
    @Autowired
    private ProducerDao producerDao;
        
    @Override
    public long saveProducer(String producerName, String producerAddress, String producerDescription) {
        long id =  (Long) producerDao.saveProducer(new Producer(producerName, producerAddress, producerDescription));
        return id;
    }

    @Override
    public List<Producer> findAll() {
        return producerDao.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return producerDao.findById(id);
    }
    
    @Override
    public Producer findByIdWithProducts(Long id){
        return producerDao.findByIdWithProducts(id);
    }
    
    @Override
    public Producer findByIdWithLogo(Long id) {
        String RELPATH = "/resources/upload/";
        String DEFAULT_RELPATH = "/resources/upload/noimg.jpg";
        Producer producer = producerDao.findByIdWithLogo(id);
        ProducerLogo logo = producer.getLogo();
        
        if(logo != null){
            int cutInd = logo.getPathToLogo().lastIndexOf(File.separator)+1;
            String logoName = logo.getPathToLogo().substring(cutInd);
            logo.setPathToLogo(RELPATH+logoName);
        }else{
            logo = new ProducerLogo(DEFAULT_RELPATH);
        }
        producer.setLogo(logo);
        
        return producer;
    }
    
    @Override
    public void updateProducer(String producerName, String producerAddress, 
            String producerDescription, String relLogoPath, String pathToDir, String id) {
        Producer producer = findByIdWithLogo(Long.valueOf(id));
        producer.setName(producerName);
        producer.setAddress(producerAddress);
        producer.setDescription(producerDescription);
        producer.setLogo(updateLogo(relLogoPath, pathToDir, id));
        
        producerDao.updateProducer(producer);
    }
    
    @Override
    public String uploadLogo(MultipartFile multipartLogo, String contextPath) {
        String logoName = multipartLogo.getOriginalFilename();
        try{
//            replace with some framework
            byte[] bytes = multipartLogo.getBytes();
            File dir = new File(contextPath + File.separator + "upload");
            String pathToLogo = dir.getAbsolutePath() + File.separator + logoName;
            File uploadedLogo = new File (pathToLogo);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedLogo));
            stream.write(bytes);
            stream.flush();
            stream.close();
            
        }catch(IOException e){
            System.err.println(e);
        }
        
        return logoName;
    }
    
    @Override
    public void delete(String id) {
        producerDao.delete(Long.valueOf(id));
    }
    
    @Override
    public List<Producer> searchProducers(String searchProducers){
        return producerDao.findByName(searchProducers);
    }
    
    private ProducerLogo updateLogo(String relLogoPath, String pathToDir, String id){
        String DEFAULT_RELPATH = "/resources/upload/noimg.jpg"; 
        ProducerLogo newLogo =null;
                
        if(!relLogoPath.equals(DEFAULT_RELPATH)){
            ProducerLogo oldLogo = producerDao.findByIdWithLogo(Long.valueOf(id)).getLogo();
            if(oldLogo != null){
                File oldLogoFile = new File(oldLogo.getPathToLogo());
                oldLogoFile.delete();
            }
            String convRelLogoPath = relLogoPath.replace("/", File.separator).substring(1);
            newLogo = new ProducerLogo(pathToDir+convRelLogoPath);
        }
        
        return newLogo;
    }
}