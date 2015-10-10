package sample.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("sample")
@EnableTransactionManagement
public class TransactionConfig {
    
    @Bean
    public DataSource dataSource(){
        return
            new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("script/table.sql")
            .addScript("script/data.sql")
            .build();
    }   
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate t = new JdbcTemplate();
        t.setDataSource(dataSource());
        return t;   
    }    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
		
}
