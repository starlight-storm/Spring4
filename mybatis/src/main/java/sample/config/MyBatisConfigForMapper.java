package sample.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sample.mybatis.business.service.PetDao;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="sample.mybatis.business.service")
public class MyBatisConfigForMapper {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sf = new SqlSessionFactoryBean();
      sf.setDataSource(dataSource);
      sf.setConfigLocation(new ClassPathResource("sample/config/mybatis-config.xml"));
      sf.setMapperLocations(
              new Resource[]{new ClassPathResource("sample/mybatis/dataaccess/pet.xml")}
      );
      return sf;
    }

    @Bean
    public PetDao petDao(SqlSessionFactory sf) {
      SqlSessionTemplate st = new SqlSessionTemplate(sf);
      return st.getMapper(PetDao.class);
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    
}
