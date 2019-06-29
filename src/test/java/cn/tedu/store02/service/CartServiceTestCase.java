package cn.tedu.store02.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.service.ex.ServiceException;
import cn.tedu.store02.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {
	@Autowired
	ICartService service;
	
	@Test
	public void addToCart() {
		Cart cart=new Cart();
		cart.setGid(10000002L);
		cart.setCid(2);
		cart.setUid(16);
		cart.setNum(2);
		String username="ligen";
		try {
			service.addToCart(cart, username);
			System.err.println("添加购物车成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getListByUid() {
		Integer uid=16;
		List<CartVO> list=service.getListByUid(uid);
		for (CartVO cartVO : list) {
			System.err.println(cartVO);
		}
	}
	
	@Test
	public void deleteByCid() {
		Integer uid=16;
		Integer cid=2;
		try {
			service.deleteByCid(cid, uid);
			System.err.println("删除成功");
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
