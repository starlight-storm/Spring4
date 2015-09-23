package sample.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource("jdbc.properties")
public class DataSourceConfig {

    @Value("${jdbc.driverClassName}")
    private String driverName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverName);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setMaxActive(maxPoolSize);
        
        datainitialize(ds);
        
        return ds;
    }
    private void datainitialize(DataSource ds) {
        ResourceDatabasePopulator p = new ResourceDatabasePopulator();
        p.addScripts(
                new ClassPathResource("script/table.sql"),
                new ClassPathResource("script/data.sql")
                );
        p.execute(ds);
        p = new ResourceDatabasePopulator();
        p.addScript(
                new ClassPathResource("script/proc.sql")
                );
        p.setSeparator("/");
        p.execute(ds);
    }
    
}
