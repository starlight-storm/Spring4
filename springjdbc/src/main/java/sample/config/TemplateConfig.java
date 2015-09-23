package sample.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class TemplateConfig {

    @Autowired
    private DataSource dataSource;    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate t = new JdbcTemplate();
        t.setDataSource(dataSource);
        return t;
        
    }
    @Bean
    public NamedParameterJdbcTemplate npJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
    
}
