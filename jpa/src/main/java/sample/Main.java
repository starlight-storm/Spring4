package sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import sample.config.DataSourceConfig;
import sample.config.JpaConfig;
import sample.jpa.business.service.PetDao;

public class Main {

    public static void main(String[] args) {
    	//Springのコンテナを生成        
    	//JavaConfigでBean定義した場合
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                DataSourceConfig.class, JpaConfig.class);

    	//Springのコンテナを生成        
    	//XMLでBean定義した場合
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("sample/config/spring-jpa.xml");
        
        //トランザクションを開始
        PlatformTransactionManager t = ctx.getBean(PlatformTransactionManager.class);
        TransactionStatus s = t.getTransaction(null);
        
        PetDao dao = ctx.getBean(PetDao.class);
        
        System.out.println(dao.findById(12).getPetName());
        
        //トランザクションをコミット
        t.commit(s);
        
    }

}
