package cn.tedu.store02.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store02.entity.Goods;
import cn.tedu.store02.mapper.GoodsMapper;
import cn.tedu.store02.service.IGoodsService;

/**
 * 处理商品数据的实现类
 * @author glii0
 *
 */
@Service
public class GoodsServiceImpl implements IGoodsService{
	@Autowired
	private GoodsMapper mapper;
	
	@Override
	public List<Goods> getHotGoods(Integer count) {
		return findHotGoods(count);
	}

	@Override
	public Goods getById(Long id) {
		return findById(id);
	}

	/**
	 * 查询热销商品
	 * @param count 热销商品数量
	 * @return 热销上坪详情
	 */
	private List<Goods> findHotGoods(Integer count){
		return mapper.findHotGoods(count);
	};
	
	/**
	 * 根据id查询商品详情
	 * @param id 商品id
 	 * @return 商品详情
	 */
	private Goods findById(Long id) {
		return mapper.findById(id);
	};
}
