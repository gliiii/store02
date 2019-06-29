package cn.tedu.store02.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void findByUsername() {
		String username="web";
		User user=mapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void addNew() {
		User user=new User();
		user.setUsername("lixr");
		user.setPassword("123456");
		Integer rows=mapper.addNew(user);
		System.err.println(rows);
	}
	
	@Test
	public void findByUid() {
		Integer uid=16;
		User user=mapper.findByUid(uid);
		System.err.println(user);
	}
	
	@Test
	public void updatePassword() {
		String password="1234";
		Integer uid=14;
		String modifiedUser="ligen";
		Date modifiedTime=new Date();
		Integer rows=mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
	
	@Test
	public void updateUserInfo() {
		User user=new User();
		user.setUid(16);
		user.setGender(1);
		user.setPhone("15527589316");
		Integer rows=mapper.updateUserInfo(user);
		System.err.println(rows);
	}
	
	@Test
	public void updateAvater() {
		String avater="http://tedu.com.cn";
		Integer uid=16;
		String modifiedUser="ligen";
		Date modifiedTime=new Date();
		Integer rows=mapper.updateAvater(avater, uid, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
}
