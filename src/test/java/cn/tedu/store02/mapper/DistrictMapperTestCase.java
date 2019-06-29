package cn.tedu.store02.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
	@Autowired
	DistrictMapper mapper;
	
	@Test
	public void findListByParent() {
		System.err.println("BEGIN");
		String parent="86";
		List<District> data=mapper.findListByParent(parent);
		for (District district : data) {
			System.err.println(district);
		}
		System.err.println("END");
	}
	
	@Test
	public void findByCode() {
		System.err.println("BEGIN");
		String code="110111";
		District district=mapper.findByCode(code);
		System.err.println(district);
		System.err.println("END");
	}
}
