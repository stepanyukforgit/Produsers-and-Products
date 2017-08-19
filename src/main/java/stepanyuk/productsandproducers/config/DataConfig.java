package stepanyuk.productsandproducers.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DataConfig {
    
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_76c45aceb49d3d0?reconnect=true;useSSL=false");
        dataSource.setUsername("bf95cc0874ff1a");
        dataSource.setPassword("e49375da");
        return dataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("stepanyuk.productsandproducers.model");
        sessionFactory.setHibernateProperties(new Properties(){
                {
                    setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
                    setProperty("hibernate.hbm2ddl.auto", "update");
                }
        });
        return sessionFactory;
    }

    //can not wire by setter
    @Bean
    @Autowired
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
