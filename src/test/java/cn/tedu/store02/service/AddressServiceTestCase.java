package cn.tedu.store02.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Address;
import cn.tedu.store02.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	IAddressService services;
	
	@Test
	public void addNew() {
		Address address=new Address();
		address.setUid(3);
		address.setReceiver("小李同学");
		String username="超级管理员";
		try {
			services.addNew(address, username);
			System.err.println("添加收货地址成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getListByUid() {
		System.err.println("BEGIN");
		Integer uid=16;
		List<Address> list=services.getListByUid(uid);
		for (Address address : list) {
			System.err.println(address);
		}
		System.err.println("END");
	}
	
	@Test
	public void setDefault() {
		Integer aid=5;
		Integer uid=16;
		String username="zbj";
		try {
			services.setDefault(aid, uid, username);
			System.err.println("设置默认地址成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void deletByAid() {
		Integer aid=7;
		Integer uid=16;
		String username="ligen";
		try {
			services.deleteByAid(aid, uid, username);
			System.err.println("删除地址成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
