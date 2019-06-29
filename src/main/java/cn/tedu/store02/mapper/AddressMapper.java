package cn.tedu.store02.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store02.entity.Address;

/**
 * 处理收货地址持久层
 * @author glii0
 */
public interface AddressMapper {
	/**
	 * 添加收货地址
	 * @param address 用户地址
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	
	/**
	 * 根据uid获取收货地址数量
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * 根据用户id查询收货地址列表
	 * @param uid 用户id
	 * @return 收货地址列表
	 */
	List<Address> findListByUid(Integer uid);
	
	/**
	 * 根据地址id查询地址详情
	 * @param aid 地址id
	 * @return 地址详情
	 */
	Address findByAid(Integer aid);
	
	/**
	 * 根据uid将所有地址设置为非默认状态
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	Integer updateNonDefault(@Param("uid")Integer uid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据地址id设置默认状态
	 * @param aid 地址id
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updateDefault(@Param("aid")Integer aid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据aid删除地址信息
	 * @param aid 地址id
	 * @return 受影响的行数
	 */
	Integer deleteByAid(Integer aid);
	
	/**
	 * 根据uid查询最后一次修改的地址
	 * @param uid 用户id
	 * @return 用户地址
	 */
	Address findLastModifiedByUid(Integer uid);
	
}
