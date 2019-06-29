package cn.tedu.store02.service;
/**
 * 处理商品数据的业务层接口
 * @author glii0
 *
 */

import java.util.List;

import cn.tedu.store02.entity.Goods;

public interface IGoodsService {
	/**
	 * 查询热销商品
	 * @param count 热销商品数量
	 * @return 热销商品详细信息
	 */
	List<Goods> getHotGoods(Integer count);
	
	/**
	 * 根据id查询商品详情
	 * @param id 商品id
	 * @return 商品详情
	 */
	Goods getById(Long id);
}
