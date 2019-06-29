package cn.tedu.store02.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.mapper.CartMapper;
import cn.tedu.store02.service.ICartService;
import cn.tedu.store02.service.ex.AccessDeniedException;
import cn.tedu.store02.service.ex.CartNotFoundException;
import cn.tedu.store02.service.ex.DeleteException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UpdateException;
import cn.tedu.store02.vo.CartVO;

/**
 * 处理购物车数据的业务层实现类
 * @author glii0
 *
 */
@Service
public class CartServiceImpl implements ICartService{
	@Autowired
	private CartMapper mapper;

	@Override
	public void addToCart(Cart cart, String username) throws InsertException, UpdateException {
		//创建Date对象
		Date now=new Date();
		// 根据参数Cart中的uid和gid执行查询：Cart findByUidAndGid
		Integer uid=cart.getUid();
		Long gid=cart.getGid();
		Cart data=findByUidAndGid(uid, gid);
		//判断查询结果是否为null
		if(data==null) {
			//是：表示该用户尚未添加该商品到购物车，则向参数对象cart中封装4个日志属性
			cart.setCreatedUser(username);
			cart.setCreatedTime(now);
			cart.setModifiedUser(username);
			cart.setModifiedTime(now);
			//--则执行插入数据：void insert(Cart cart);
			insert(cart);
		}else {
			//否：表示该用户已经添加该商品到购物车，取出cid
			Integer cid=data.getCid();
			Integer oldNum=data.getNum();
			Integer newNum=cart.getNum();
			//--num值为参数cart中的num加上前序查询结果中的num
			Integer num=oldNum+newNum;
			//--则执行修改数量：void updateNum
			//(Integer cid,Integer num,String modifiedUser,Date modifiedTime)
			updateNum(cid, num, username, now);
		}
	}
	
	@Override
	public List<CartVO> getListByUid(Integer uid) {
		return findListByUid(uid);
	}

	@Override
	public Integer addNum(Integer cid, Integer uid, String username)
			throws UpdateException, AccessDeniedException, CartNotFoundException {
		//根据cid查询购物车数据
		Cart data=findByCid(cid);
		//判断查询结果是否为null
		if(data==null) {
			//是：抛出异常：CartNotFoundException 
			throw new CartNotFoundException("添加商品数量失败，购物车商品不存在");
		}
		//判断查询结果中的uid与参数uid是否不同
		if(!uid.equals(data.getUid())) {
			//是：抛出异常：AccessDeniedException
			throw new AccessDeniedException("添加商品数量失败，访问被拒绝异常");
		}
		//从查询结果中取出当前的num
		Integer num=data.getNum();
		//将num自增
		num++;
		//创建当前时间对象
		Date now=new Date();
		//更新updateNum(cid, num, modifiedUser, modifiedTime);
		updateNum(cid, num, username, now);
		//返回num
		return num;
	}

	@Override
	public Integer reduceNum(Integer cid, Integer uid, String username)
			throws UpdateException, AccessDeniedException, CartNotFoundException {
		//根据cid查询数据
		Cart good=findByCid(cid);
		//判断查询结果是否为null
		if(good==null) {
			//是：抛出异常：CartNotFoundException 
			throw new CartNotFoundException("购物车商品不存在！");
		}
		//判断查询结果中的uid与参数uid是否不同
		if(good.getUid()!=uid) {
			//是：抛出异常：AccessDeniedException
			throw new AccessDeniedException("访问被拒绝！");
		}
		//从查询结果中取出当前的num
		Integer num=good.getNum();
		//将num自减
		num--;
		//限制num最小值
		if(num<=0) {
			num=1;
		}
		//创建当前时间对象
		Date now=new Date();
		//更新updateNum(cid, num, modifiedUser, modifiedTime);
		updateNum(cid, num, username, now);
		//返回num
		return num;
}

	@Override
	public void deleteByCid(Integer cid, Integer uid)
			throws DeleteException, CartNotFoundException, AccessDeniedException {
		//根据cid查询购物车数据
		Cart data=findByCid(cid);
		//判断购物车数据是否不存在
		if(data==null) {
			//--是，不存在，抛出异常CartNotFoundException
			throw new CartNotFoundException("删除购物车数据失败，购物车数据不存在");
		}
		//判断uid是否一致
		if(!uid.equals(data.getUid())) {
			//--不一致，抛出异常AccessDeniedException 
			throw new AccessDeniedException("删除购物车数据失败，访问被拒绝");
		}
		//执行删除
		deleteByCid(cid);
	}

	@Override
	public List<CartVO> getListByCids(Integer[] cids) {
		//应该检查参数是否有效：非null，数组至少包含一个元素
		//应该检查数据归属
		return findListByCids(cids);
	}

	/**
	 * 添加购物车数据
	 * @param cart 购物车数据
	 */
	private void insert(Cart cart) {
		Integer rows=mapper.insert(cart);
		if(rows!=1) {
			throw new InsertException("添加购物车失败，添加数据异常");
		}
	};
	
	/**
	 * 更新购物车商品数量
	 * @param cid 购物车id
	 * @param num 商品数量
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 */
	private void updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime) {
		Integer rows= mapper.updateNum(cid, num, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("添加购物车失败，更新数据异常");
		}
	};
	
	/**
	 * 根据uid和gid查询购物车详情
	 * @param uid 用户id
	 * @param gid 购物车id
	 * @return 购物车详情
	 */
 	private Cart findByUidAndGid(Integer uid, Long gid) {
 		return mapper.findByUidAndGid(uid, gid);
 	};
 	
 	/**
 	 * 根据用户id查询购物车详情
 	 * @param uid 用户id
 	 * @return 购物车详情数据集合
 	 */
 	private List<CartVO> findListByUid(Integer uid){
 		return mapper.findListByUid(uid);
 	};
 	
 	/**
 	 * 根据购物车id查询购物车数据
 	 * @param cid 购物车id
 	 * @return 购物车数据
 	 */
 	private Cart findByCid(Integer cid) {
 		return mapper.findByCid(cid);
 	};
 	
 	/**
 	 * 根据购物车id删除购物车数据
 	 * @param cid 购物车id
 	 */
 	private void deleteByCid(Integer cid) {
 		Integer rows= mapper.deleteByCid(cid);
 		if(rows!=1) {
 			throw new DeleteException("删除购物车数据失败，删除数据异常");
 		}
 	};
 	
 	/**
 	 * 根据购物车id查询购物车数据详情
 	 * @param cids 购物车id
 	 * @return 购物车数据详情
 	 */
 	private List<CartVO> findListByCids(Integer[] cids){
 		return mapper.findListByCids(cids);
 	};
}
