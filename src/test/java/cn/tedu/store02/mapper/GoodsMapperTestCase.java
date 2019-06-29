package cn.tedu.store02.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTestCase {
	@Autowired
	GoodsMapper mapper;
	
	@Test
	public void findHotGoods() {
		Integer count=10;
		List<Goods> goods=mapper.findHotGoods(count);
		for (Goods goods2 : goods) {
			System.err.println(goods2);
		}
	}
	
	@Test
	public void findById() {
		Long id=10000001L;
		Goods goods=mapper.findById(id);
		System.err.println(goods);
	}
	
	
}
