package cn.tedu.store02.mapper;
/**
 * 处理省市区数据的持久层接口
 * @author glii0
 *
 */

import java.util.List;

import cn.tedu.store02.entity.District;

public interface DistrictMapper {
	
	/**
	 * 根据父级代号查询所有省/某省的所有市/某市的所有区
	 * @param parent 父级代号
	 * @return 数据集合
	 */
	List<District> findListByParent(String parent);
	
	/**
	 * 根据某省市区的代号，查询数据
	 * @param code 省/市/区代号
	 * @return 省/市/区名
	 */
	District findByCode(String code);
	
}
