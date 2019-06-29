package cn.tedu.store02.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {
	@Autowired
	CartMapper mapper;
	
	@Test
	public void insert() {
		Cart cart=new Cart();
		cart.setGid(10000001L);
		cart.setUid(16);
		cart.setNum(1);
		Integer rows=mapper.insert(cart);
		System.err.println(rows);
	}
	
	@Test
	public void updateNum() {
		Integer cid=1;
		Integer num=2;
		String modifiedUser="ligen";
		Date modifiedTime=new Date();
		Integer rows=mapper.updateNum(cid, num, modifiedUser, modifiedTime);
		System.err.println(rows);
	}
	
	@Test
	public void findByUidAndGid() {
		Long gid=10000001L;
		Integer uid=16;
		Cart cart=mapper.findByUidAndGid(uid, gid);
		System.err.println(cart);
	}
	
	@Test
	public void findListByUid() {
		Integer uid=16;
		List<CartVO> list=mapper.findListByUid(uid);
		for (CartVO cartVO : list) {
			System.err.println(cartVO);
		}
	}
	
	@Test
	public void findListByCid() {
		Integer cid=4;
		Cart cart=mapper.findByCid(cid);
		System.err.println(cart);
	}
	
	@Test
	public void deleteByCid() {
		Integer cid=1;
		Integer rows= mapper.deleteByCid(cid);
		System.err.println(rows);
	}
	
	@Test
	public void findListByCids() {
		Integer[] cids= {11,12,13,14};
		List<CartVO> list=mapper.findListByCids(cids);
		for (CartVO cartVO : list) {
			System.err.println(cartVO);
		}
	}
}
