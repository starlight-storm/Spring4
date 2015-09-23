package sample.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("jndi.properties")
public class JndiConfig {

    
    @Value("${jndi.datasource}")
    private String jndiName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }    
    
    @Bean
    public DataSource dataSource() throws NamingException {
        Context ctx = new InitialContext();
        return (DataSource)ctx.lookup(jndiName);
    }
        
}
