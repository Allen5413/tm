package test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;


import com.zs.domain.finance.Invoice;
import com.zs.service.bank.paylog.AddPayLogService;
//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath*:resource/spring/applicationContext.xml")
//@ActiveProfiles("development")
//public class JpaHibernateFindAllTest extends AbstractTransactionalJUnit4SpringContextTests{
//
//	@Resource
//	private AddPayLogService addPayLogService;
//
//	@Test
//	@Rollback(value =  false)
//	public void getStudentByName() throws UnsupportedEncodingException{
//        try {
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//	}
//
//}
