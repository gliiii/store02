package cn.tedu.store02.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store02.entity.Address;
import cn.tedu.store02.entity.District;
import cn.tedu.store02.mapper.AddressMapper;
import cn.tedu.store02.service.IAddressService;
import cn.tedu.store02.service.IDistrictService;
import cn.tedu.store02.service.ex.AccessDeniedException;
import cn.tedu.store02.service.ex.AddressNotFoundException;
import cn.tedu.store02.service.ex.DeleteException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UpdateException;
/**
 * 处理收货地址的实现类
 * @author glii0
 */
@Service
public class AddressServiceImpl implements IAddressService{
	@Autowired
	private AddressMapper mapper;
	
	@Autowired
	private IDistrictService districtService;
	
	@Override
	public void addNew(Address address, String username) throws InsertException {
		//TODO 获取district
		String district=getDistrictByCodes(address.getProvince(),address.getCity(),address.getArea());
		address.setDistrict(district);
		//获取当前用户的收货地址数量
		Integer uid=3;
		Integer count=getCountByUid(uid);
		//如果数量为0，则isDefault为1，否则，isDefault为0
		address.setIsDefault(count==0?1:0);
		//四项日志
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		//执行插入数据：insert(address);
		insert(address);
	}
	
	@Override
	public List<Address> getListByUid(Integer uid) {
		return findListByUid(uid);
	}

	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username)
			throws UpdateException, AddressNotFoundException, AccessDeniedException {
		//根据aid查询地址信息
		Address data=findByAid(aid);
		//判断地址信息是否存在
		if(data==null) {
			//--不存在，为null，抛出异常AddressNotFoundException
			throw new AddressNotFoundException("设置默认地址失败，地址不存在");
		}
		//判断地址中的uid是否与session中的uid一致
		if(!uid.equals(data.getUid())) {
			//--不一致，抛出异常AccessDeniedException 
			throw new AccessDeniedException("设置默认地址失败，用户不一致");
		}
		//执行设置所有地址为非默认状态
		Date now=new Date();
		updateNonDefault(uid, username, now);
		//执行某个地址设置为默认状态
		updateDefault(aid, username, now);
	}

	@Override
	@Transactional
	public void deleteByAid(Integer aid, Integer uid, String username)
			throws DeleteException, AddressNotFoundException, AccessDeniedException {
		//根据aid查询地址数据
		Address data=findByAid(aid);
		//判断地址数据是否存在
		if(data==null) {
			//--不存在。为null，抛出异常AddressNotFoundException
			throw new AddressNotFoundException("删除地址失败，地址不存在！");
		}
		//判断地址中的uid是否跟session中的uid一致，
		if(!uid.equals(data.getUid())) {
			//--不一致，抛出异常AccessDeniedException 
			throw new AccessDeniedException("删除地址失败，访问拒绝异常");
		}
		//执行删除
		deleteByAid(aid);
		//判断刚刚删除的数据isDefault是否为为默认状态
		if(data.getIsDefault()==1) {
			//--是为默认状态。为1，查询当前用户还有多少条收货地址
			Integer count=getCountByUid(uid);
			//判断是否》0
			if(count>0) {
				//--是大于0，找出最后一条修改的地址
				Address address=findLastModifiedByUid(uid);
				//将该条地址设置为默认状态
				updateDefault(address.getAid(), username, new Date());
			}
		}
	}

	@Override
	public Address getByAid(Integer aid) {
		return findByAid(aid);
	}

	/**
	 * 根据用户id查询收货地址数量
	 * @param uid 用户id
	 * @return 收货地址数量
	 */
	private Integer getCountByUid(Integer uid) {
		return mapper.getCountByUid(uid);
	};
	
	/**
	 * 添加用户收货地址
	 * @param address 收货地址
	 */
	private void insert(Address address) {
		Integer rows=mapper.insert(address);
		if(rows!=1) {
			throw new InsertException("添加收货地址失败，添加异常");
		}
	};
	
	/**
	 * 获得省市区名称
	 * @param province 省代号
	 * @param city 市代号
	 * @param area 区代号
	 * @return 省市区名称
	 */
	private String getDistrictByCodes(String province, String city, String area) {
		String provinceName="NULL";
		String cityName="NULL";
		String areaName="NULL";
		
		District p=districtService.getByCode(province);
		if(p!=null) {
			 provinceName=p.getName();
		}
		District c=districtService.getByCode(city);
		if(c!=null) {
			 cityName=c.getName();
		}
		District a=districtService.getByCode(area);
		if(a!=null) {
			 areaName=a.getName();
		}
		return provinceName+cityName+areaName;
	}
	
	/**
	 * 根据用户id查询收货地址集合
	 * @param uid 用户id
	 * @return 收货地址集合
	 */
	private List<Address> findListByUid(Integer uid){
		return mapper.findListByUid(uid);
	};
	
	/**
	 * 根据用户id查询地址详情
	 * @param aid
	 * @return
	 */
	private Address findByAid(Integer aid) {
		return mapper.findByAid(aid);
	};
	
	/**
	 * 根据用户id设置非默认状态
	 * @param uid
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	private void updateNonDefault(Integer uid,String modifiedUser,Date modifiedTime) {
		Integer rows=mapper.updateNonDefault(uid, modifiedUser, modifiedTime);
		if(rows<1) {
			throw new UpdateException("设置非默认状态异常");
		}
	};
	
	/**
	 * 根据地址id设置默认状态
	 * @param aid
	 * @param modifiedUser
	 * @param modifiedTime
	 */
	private void updateDefault(Integer aid,String modifiedUser,Date modifiedTime) {
		Integer rows=mapper.updateDefault(aid, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("设置默认状态异常");
		}
	};
	
	/**
	 * 根据aid删除用户地址
	 * @param aid 地址id
	 */
	private void deleteByAid(Integer aid) {
		Integer rows=mapper.deleteByAid(aid);
		if(rows!=1) {
			throw new DeleteException("删除地址失败，请重试");
		}
	};
	
	/**
	 * 根据用户id查询最后一次修改的地址数据
	 * @param uid 用户id
	 * @return 最后一次修改的地址数据
	 */
	private Address findLastModifiedByUid(Integer uid) {
		return mapper.findLastModifiedByUid(uid);
	};
}
