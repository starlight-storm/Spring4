package sample.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("sample.mybatis.dataaccess")
public class MyBatisConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sf = new SqlSessionFactoryBean();
      sf.setDataSource(dataSource);
      sf.setConfigLocation(new ClassPathResource("sample/config/mybatis-config.xml"));
      sf.setMapperLocations(
              new PathMatchingResourcePatternResolver().getResources("classpath*:sample/mybatis/dataaccess/**/*.xml")
              //new Resource[]{new ClassPathResource("sample/mybatis/dataaccess/pet.xml")}
      );
      
      return sf;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sf) {
        return new SqlSessionTemplate(sf);
    }

}
