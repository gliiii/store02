package cn.tedu.store02.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store02.entity.District;
import cn.tedu.store02.mapper.DistrictMapper;
import cn.tedu.store02.service.IDistrictService;
/**
 * 处理省市区数据的实现类
 * @author glii0
 *
 */
@Service
public class DistrictServiceImpl implements IDistrictService{
	@Autowired
	private DistrictMapper mapper;
	
	@Override
	public List<District> getListByParent(String parent) {
		return findListByParent(parent);
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}
	
	/**
	 * 根据父级代号查询所有省/谋省所有市/某市所有区
	 * @param parent
	 * @return
	 */
	private List<District> findListByParent(String parent){
		return mapper.findListByParent(parent);
	};
	
	/**
	 * 根据省市区编码查询省市区名称
	 * @param code 省市区编码
	 * @return 省市区名称
	 */
	private District findByCode(String code) {
		return mapper.findByCode(code);
	};

}
