package sample.repository;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import sample.config.DataSourceConfig;
import sample.config.MyBatisConfig;
import sample.mybatis.business.domain.Pet;
import sample.mybatis.business.service.PetDao;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, MyBatisConfig.class})
@Transactional
public class PetDaoTest {
	@Autowired
	private PetDao dao;
	
	@Before
	public void setup() {
	    
	}
	
	@Test
	public void test2() {
	    assertEquals("コロ", dao.findById(12).getPetName());
	}
	
	@Test
	public void test3() {
	}
	
	
}
