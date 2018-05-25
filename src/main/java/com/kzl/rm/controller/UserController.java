package com.kzl.rm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kzl.rm.bean.Person_Image;
import com.kzl.rm.bean.User;
import com.kzl.rm.service.ArticleService;
import com.kzl.rm.service.Person_ImageService;
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

	@Autowired
	private ArticleService articleSerivce;

	@Autowired
	private Person_ImageService person_ImageService;

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

	// 关于我们页面
	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/error")
	public String error() {
		return "error";
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
		boolean b = userService.checkUser_Account(user_account);
		if (b) {
			return "success";
		}
		return "fail";
	}

	/**
	 * 
	 * @Title: checkUser_password
	 * @Description: 修改密码时，判断原密码是否正确
	 * @return String 返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUser_password")
	public String checkUser_password(HttpServletRequest request, @RequestParam("oldpassword") String oldpassword) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		String result = userService.user_login(account, oldpassword);
		if (result.equals("AccountIsNotExist"))
			return "AccountIsNotExist";
		if (result.equals("PasswordError"))
			return "fail";

		return "success";
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
			return "redirect:/login";
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
		return "redirect:/index";
	}

	/**
	 * 
	 * @Title: user_Logout
	 * @Description: 注销功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/logout")
	public String user_Logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		session.invalidate();
		return "index";
	}

	/**
	 * 
	 * @Title: edit_UserInfo
	 * @Description: 用户信息的管理（显示用户的信息）
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/userInfo_management")
	public String show_UserInfo(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		User user = userService.findIdByAccount(account);
		long original = articleSerivce.countOriginal(user.getUserId());
		user.setOriginal(original);
		long reprint = articleSerivce.countReprint(user.getUserId());
		user.setReprint(reprint);
		long translate = articleSerivce.countTranslate(user.getUserId());
		user.setTranslate(translate);
		long visit = articleSerivce.countVisit(user.getUserId());
		user.setVisit(visit);
		long discuss = articleSerivce.countDiscuss(user.getUserId());
		user.setDiscuss(discuss);
		Person_Image image = person_ImageService.findImageById(user.getPersonalImageId());
		user.setImageurl(image.getImageUrl());
		if (user != null) {
			model.addAttribute("userInfo", user);
			return "userInfo_management";
		}

		return "error";
	}

	/**
	 * 
	 * @Title: edit_UserInfo
	 * @Description: 修改个人信息
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/change_userInfo")
	public String edit_UserInfo(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		User user = userService.findIdByAccount(account);
		Person_Image image = person_ImageService.findImageById(user.getPersonalImageId());
		user.setImageurl(image.getImageUrl());
		if (user != null) {
			model.addAttribute("userInfo", user);
			return "change_userInfo";
		}

		return "error";
	}

	/**
	 * 
	 * @Title: change_password
	 * @Description: 更换密码
	 * @return String 返回类型
	 */
	@RequestMapping(value = "change_password")
	public String change_password(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		User user = userService.findIdByAccount(account);
		Person_Image image = person_ImageService.findImageById(user.getPersonalImageId());
		user.setImageurl(image.getImageUrl());
		if (user != null) {
			model.addAttribute("userInfo", user);
			return "change_password";
		}

		return "error";
	}

	/**
	 * 
	 * @Title: update_userInfo
	 * @Description: 更新个人信息
	 * @return String 返回类型
	 */
	@RequestMapping("/update_userInfo")
	public String update_userInfo(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("position") String position, @RequestParam("email") String email,
			@RequestParam("telephone") String telephone) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		boolean result = userService.update_userInfo(username, position, email, telephone, account);
		if (result)
			return "redirect:/userInfo_management";

		return "error";
	}

	/**
	 * 
	 * @Title: update_password
	 * @Description: 更新密码
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/update_password")
	public String update_password(HttpServletRequest request, @RequestParam("password") String password) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		boolean result = userService.update_password(account, password);
		if (result) {
			session.removeAttribute("account");
			session.invalidate();
			return "login";
		}
		return "error";
	}
}
