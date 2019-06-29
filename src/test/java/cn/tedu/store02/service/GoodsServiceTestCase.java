package cn.tedu.store02.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
	@Autowired
	IGoodsService service;
	
	@Test
	public void getHotGoods() {
		Integer count=10;
		List<Goods> goods=service.getHotGoods(count);
		for (Goods goods2 : goods) {
			System.err.println(goods);
		}
	}
	
	@Test
	public void getById() {
		Long id=10000002L;
		Goods goods=service.getById(id);
		System.err.println(goods);
	}
}
