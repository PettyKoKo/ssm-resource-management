package com.kzl.rm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kzl.rm.bean.User;
import com.kzl.rm.service.UserService;

/**
 * 
 * @ClassName: UserController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author kezeli
 * @date 2018年5月1日 上午11:57:04
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 主页面
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	// 登录页面
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// 注册页面
	@RequestMapping(value = "/register")
	public String register() {
		return "register";
	}

	/**
	 * 
	 * @Title: checkUser_Account
	 * @Description: 检验用户账号是否可用
	 * @return String 返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUser_Account")
	public String checkUser_Account(@RequestParam("user_account") String user_account) {
		System.out.println(user_account);
		boolean b = userService.checkUser_Account(user_account);
		System.out.println(b);
		if (b) {
			return "success";
		}
		return "fail";
	}

	/**
	 * 
	 * @Title: user_register
	 * @Description: 用来实现用户注册功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/user_register")
	public String user_register(@RequestParam("account") String account, @RequestParam("password") String password,
			@RequestParam("username") String username, @RequestParam("phone") String phone,
			@RequestParam("email") String email, @RequestParam("position") String position) {
		boolean b = userService.user_register(account, password, username, phone, email, position);
		if (b) {
			return "login";
		}
		return "register";
	}

	/**
	 * 
	 * @Title: checkPassword
	 * @Description: 校验密码是否正确
	 * @return String 返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPassword")
	public String checkPassword(@RequestParam("account") String account, @RequestParam("password") String password) {
		String result = userService.user_login(account, password);
		if (result.equals("AccountIsNotExist"))
			return "AccountIsNotExist";
		if (result.equals("PasswordError"))
			return "PasswordError";

		return "index";
	}

	/**
	 * 
	 * @Title: user_login
	 * @Description: 用来实现用户登录功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/user_login")
	public String user_login(HttpServletRequest request, @RequestParam("account") String account,
			@RequestParam("password") String password) {
		String result = userService.user_login(account, password);
		if (result.equals("AccountIsNotExist"))
			return "AccountIsNotExist";
		if (result.equals("PasswordError"))
			return "PasswordError";
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		return "index";
	}
	
	@RequestMapping(value="/logout")
	public String user_Logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		session.invalidate();
		return "index";
	}
}
