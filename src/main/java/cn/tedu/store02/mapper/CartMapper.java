package cn.tedu.store02.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.vo.CartVO;

/**
 * 处理购物车数据的持久层
 * @author glii0
 *
 */
public interface CartMapper {
	/**
	 * 添加购物车数据
	 * @param cart 购物车数据
	 * @return 受影响的行数
	 */
	Integer insert(Cart cart);

	/**
	 * 更新购物车数量
	 * @param cid 购物车id
	 * @param num 数量
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updateNum(@Param("cid")Integer cid, 
			@Param("num")Integer num, 
			@Param("modifiedUser")String modifiedUser, 
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据uid和gid查询购物车数据
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 购物车
	 */
	Cart findByUidAndGid(@Param("uid")Integer uid,
			@Param("gid")Long gid);
	
	/**
	 * 根据uid查询购物车详情
	 * @param uid 用户id
	 * @return 购物车详情数据集合
	 */
	List<CartVO> findListByUid(Integer uid);
	
	/**
	 * 根据购物车id查询购物车数据
	 * @param cid 购物车id
	 * @return 购物车详情
	 */
	Cart findByCid(Integer cid);
	
	/**
	 * 根据cid删除购物车商品
	 * @param cid 购物车id
	 * @return 受影响的行数
	 */
	Integer deleteByCid(Integer cid);
	
	/**
	 * 根据购物车id查询购物车数据集合
	 * @param cids 购物车id数组
	 * @return 购物车详情
	 */
	List<CartVO> findListByCids(Integer[] cids);
}
