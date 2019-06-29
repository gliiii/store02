package cn.tedu.store02.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store02.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestCase {
	@Autowired
	IDistrictService service;
	
	@Test
	public void getListByParent() {
		System.err.println("BEGIN");
		String parent="86";
		List<District> list=service.getListByParent(parent);
		for (District district : list) {
			System.err.println(district);
		}
		System.err.println("END");
	}
	
	@Test
	public void getByCode() {
		System.err.println("BEGIN");
		String code="120102";
		District district=service.getByCode(code);
		System.err.println(district);
		System.err.println("END");
	}
}
