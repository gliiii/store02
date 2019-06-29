package cn.tedu.store02.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.User;
import cn.tedu.store02.service.ex.FileUploadException;
import cn.tedu.store02.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	IUserService service;
	
	@Test
	public void reg() {
		User user=new User();
		user.setUsername("HelloWorld");
		user.setPassword("123456");
		try {
			service.reg(user);
			System.err.println("注册成功！");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void login() {
		String username="zbj";
		String password="123456";
		try {
			User user=service.login(username, password);
			System.err.println("登陆成功");
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changePassword() {
		Integer uid=16;
		String oldPassword="1234";
		String newPassword="123456";
		try {
			service.changePassword(uid, oldPassword, newPassword);
			System.err.println("修改密码成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeUserInfo() {
		User user=new User();
		user.setUid(17);
		user.setGender(0);
		user.setPhone("15872520020");
		try {
			service.changeUserInfo(user);
			System.err.println("修改用户信息成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void listUserInfo() {
		Integer uid=17;
		try {
			User data=service.listUserInfo(uid);
			System.err.println(data);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeAvater() {
		String avater="http://qq.com";
		Integer uid=17;
		try {
			service.changeAvater(uid, avater);
			System.err.println("修改用户头像成功");
		} catch (FileUploadException e) {
			System.err.println(e.getMessage());
		}
	}
}
