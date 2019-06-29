package cn.tedu.store02.service;
/**
 * 处理省市区数据的业务层接口
 */
import java.util.List;

import cn.tedu.store02.entity.District;

/**
 * 处理省市区数据的业务层接口
 * @author glii0
 *
 */
public interface IDistrictService {
	/**
	 * 根据父级代号查询所有省/某省的所有市/某市的所有区集合
	 * @param 父级代号
	 * @return 省/市/区集合
	 */
	List<District> getListByParent(String parent);
	
	/**
	 * 根据省/市/区代码查询地址
	 * @param 省/市/区代号
	 * @return 省/市/区 名称
	 */
	District getByCode(String code);
}
