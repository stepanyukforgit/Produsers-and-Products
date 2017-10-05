package stepanyuk.productsandproducers.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfig {
    
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/prod?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("mysql");
        return dataSource;
    }

//JPA example    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("stepanyuk.productsandproducers.model");
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(new Properties(){
                {
                    setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
                    setProperty("hibernate.hbm2ddl.auto", "update");
                }
        });
        return em;
    }
    
    @Bean
    public PlatformTransactionManager trancactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
//HIBERNATE example
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("stepanyuk.productsandproducers.model");
//        sessionFactory.setHibernateProperties(new Properties(){
//                {
//                    setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
//                    setProperty("hibernate.hbm2ddl.auto", "update");
//                }
//        });
//        return sessionFactory;
//    }
//
//    //can not wire by setter
//    @Bean
//    @Autowired
//    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }
}
