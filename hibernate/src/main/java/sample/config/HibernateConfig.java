package sample.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("sample.hibernate.dataaccess")
public class HibernateConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean fb = new LocalSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setNamingStrategy(new org.hibernate.cfg.ImprovedNamingStrategy());
        fb.setMappingResources("sample/hibernate/dataaccess/Pet.hbm.xml");
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        prop.setProperty("hibernate.show_sql", "true");
        fb.setHibernateProperties(prop);
        return fb;
    }    
    
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }

    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
}
