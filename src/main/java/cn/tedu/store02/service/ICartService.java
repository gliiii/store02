package cn.tedu.store02.service;

import java.util.List;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.service.ex.AccessDeniedException;
import cn.tedu.store02.service.ex.CartNotFoundException;
import cn.tedu.store02.service.ex.DeleteException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UpdateException;
import cn.tedu.store02.vo.CartVO;

/**
 * 处理购物车数据的持久层接口
 * @author glii0
 *
 */
public interface ICartService {
	/**
	 * 添加商品到购物车
	 * @param cart 购物车数据
	 * @param usernmae 用户名
	 * @throws InsertException 添加数据异常
	 * @throws UpdateException 更新数据异常
	 */
	void addToCart(Cart cart,String username)throws InsertException,UpdateException;
	
	/**
	 * 根据用户id查询购物车详情
	 * @param uid 用户id
	 * @return 购物车数据集合
	 */
	List<CartVO> getListByUid(Integer uid);
	
	/**
	 * 添加购物车商品数量
	 * @param cid 购物车id
	 * @param uid 用户id
	 * @param username 用户名
	 * @return 受影响的行数
	 * @throws UpdateException 更新异常
	 * @throws AccessDeniedException 访问被拒绝异常
	 * @throws CartNotFoundException 购物车数据未找到异常
	 */
	Integer addNum(Integer cid,Integer uid,String username)
			throws UpdateException,AccessDeniedException,CartNotFoundException;
	
	/**
	 *  购物车数据中购物数量-1
	 * @param cid
	 * @param uid
	 * @param username
	 * @return
	 * @throws UpdateException
	 * @throws AccessDeniedException
	 * @throws CartNotFoundException
	 */
	Integer reduceNum(Integer cid,Integer uid,String username)
			throws UpdateException,AccessDeniedException,CartNotFoundException;
	
	/**
	 * 根据cid删除购物车数据
	 * @param cid 购物车id
	 * @param uid 用户id
	 */
	void deleteByCid(Integer cid, Integer uid)throws DeleteException,CartNotFoundException,AccessDeniedException;
	
	/**
	 * 查询指定的某些id的购物车数据
	 * @param cids 多个购物车数据的id的数组
	 * @return 指定的某些id的购物车数据
	 */
	List<CartVO> getListByCids(Integer[] cids);
}
