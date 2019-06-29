package cn.tedu.store02.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {
	@Autowired
	OrderMapper mapper;
	
	@Test
	public void insertOrder() {
		Order order=new Order();
		order.setUid(16);
		Integer rows=mapper.insertOrder(order);
		System.err.println(rows);
		System.err.println(order);
	}
}
