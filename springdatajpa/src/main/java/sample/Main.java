package sample;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import sample.config.AppConfig;
import sample.config.DataSourceConfig;
import sample.config.JpaConfig;
import sample.springdatajpa.business.domain.Pet;
import sample.springdatajpa.business.service.PetDao;

public class Main {

    public static void main(String[] args) {
    	
    	//Springのコンテナを生成        
    	//JavaConfigでBean定義した場合
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(
//        		DataSourceConfig.class, AppConfig.class, JpaConfig.class);

    	// Springのコンテナを生成        
    	//XMLでBean定義した場合
        ApplicationContext ctx = new ClassPathXmlApplicationContext("sample/config/spring-jpa.xml");
                        
        PetDao dao = ctx.getBean(PetDao.class);

        //Spring Data JPAが自動生成したメソッドを呼び出します
        Pet pet = dao.findOne(1);
        System.out.println(pet.getPetName());
        
        //命名規則に沿ったメソッドを呼び出します
        List<Pet> list = dao.findByPetNameAndPriceLessThanEqual("ポチ", 5000);
        System.out.println(list.size());

        //JPAQLを指定したメソッドを呼び出します
        list = dao.findByOwnerName("東京太郎");
        System.out.println(list.size());

        //トランザクションを開始します
        PlatformTransactionManager t = ctx.getBean(PlatformTransactionManager.class);
        TransactionStatus s = t.getTransaction(null);

        //JPAQLを指定したメソッド(更新系)を呼び出します
        int count = dao.updatePetPrice(10000000, "ポチ5");
        System.out.println("count="+count);

        //トランザクションをコミットします
        t.commit(s);

        list = dao.findAll();
        for (Pet p : list) {
            System.out.println(p.getPrice());
        }

        //手動で実装を行ったメソッドを呼び出します
        dao.foo();

        
    }

}
