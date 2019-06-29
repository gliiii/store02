package cn.tedu.store02.mapper;
/**
 * 处理商品数据的持久层接口
 * @author glii0
 *
 */

import java.util.List;

import cn.tedu.store02.entity.Goods;

public interface GoodsMapper {
	/**
	 * 查询热销商品
	 * @param count 查询数量
	 * @return 热销商品详情
	 */
	List<Goods> findHotGoods(Integer count);
	
	/**
	 * 根据id查询商品详情
	 * @param id
	 * @return
	 */
	Goods findById(Long id);
}
