package sample;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import sample.config.JndiConfig;
import sample.config.TemplateConfig;


public class JndiMain {
    
    public static void main(String[] args) throws Exception {
    	// データソースのオブジェクトを生成
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        ds.setUrl("jdbc:hsqldb:mem:sample");
        ds.setUsername("sa");
        ds.setPassword("");
        datainitialize(ds);

        // Springが提供するJNDI実装(テスト用)を使用し、ネーミングコンテキストを生成
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("jdbc/MyDataSource", ds);
        builder.activate();
        
        // Bean定義ファイルを使用し、JNDI経由でデータソースを取得
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("sample/config/spring-jndi.xml");
        // JavaConfigを使用し、JNDI経由でデータソースを取得
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TemplateConfig.class, JndiConfig.class);
        
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
                
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM PET", Integer.class);
        System.out.println(count);
        
        
    }
    
    private static void datainitialize(DataSource ds) {
        ResourceDatabasePopulator p = new ResourceDatabasePopulator();
        p.addScripts(
                new ClassPathResource("/script/table.sql"),
                new ClassPathResource("/script/data.sql")
                );
        p.execute(ds);
        p = new ResourceDatabasePopulator();
        p.addScript(
                new ClassPathResource("/script/proc.sql")
                );
        p.setSeparator("/");
        p.execute(ds);
        
    }
    
}
