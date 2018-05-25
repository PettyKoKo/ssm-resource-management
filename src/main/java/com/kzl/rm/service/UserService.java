package com.kzl.rm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kzl.rm.bean.User;
import com.kzl.rm.bean.UserExample;
import com.kzl.rm.bean.UserExample.Criteria;
import com.kzl.rm.dao.UserMapper;

/**
 * 
 * @ClassName: UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author kezeli
 * @date 2018年5月1日 上午11:57:48
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 
	 * @Title: checkUser_Account
	 * @Description: 检验用户账号是否可用
	 * @return boolean true:代表可用 false:代表不可用
	 */
	public boolean checkUser_Account(String user_account) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserAccountEqualTo(user_account);
		long count = userMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 
	 * @Title: user_register
	 * @Description: 用户注册功能，把用户数据保存到 数据库中
	 * @return boolean 返回类型
	 */
	public boolean user_register(String account, String password, String username, String phone, String email,
			String position) {
		User user = new User();
		user.setUserAccount(account);
		user.setPassword(password);
		user.setUserName(username);
		user.setTelephone(phone);
		user.setEmail(email);
		user.setPosition(position);

		// 对传入的数据进行校验,如果用户名已存在，返回false
		if (!checkUser_Account(account))
			return false;

		int count = userMapper.insertSelective(user);
		return count == 1;
	}

	/**
	 * 
	 * @Title: user_login
	 * @Description: 用户登录功能实现.根据查询结果，返回相应的值
	 * @return String 返回类型
	 */
	public String user_login(String account, String password) {
		// 判断该用户账号是否存在
		if (checkUser_Account(account)) {
			return "AccountIsNotExist";
		}
		long count = userMapper.findUserByAccountAndPassword(account, password);
		if (count == 0)
			return "PasswordError";

		return "Success";
	}

	/**
	 * 
	 * @Title: findIdByAccount
	 * @Description: 通过用户账号查找用户信息
	 * @return User 返回类型
	 */
	public User findIdByAccount(String account) {
		User user = userMapper.findUserByAccount(account);
		return user;
	}

	public int updateImageId(String user_account, Long imageId) {
		int count = userMapper.updateImageId(user_account,imageId);
		return count;
	}

	public boolean update_userInfo(String username, String position, String email, String telephone,String account) {
		int count = userMapper.update_userInfo(username,position,email,telephone,account);
		return count==1;
	}

	public boolean update_password(String account, String password) {
		int count = userMapper.update_password(account,password);
		return count==1;
	}

}
