package com.kzl.rm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kzl.rm.bean.Article;
import com.kzl.rm.service.ArticleService;

/**
 * 
 * @ClassName: ArticleController
 * @Description:
 * @author kezeli
 * @date 2018年5月5日 下午4:08:11
 *
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 
	 * @Title: write_article
	 * @Description: 写文章的页面
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/write_article")
	public String write_article() {
		return "write_article";
	}

	/**
	 * 
	 * @Title: user_publish_article
	 * @Description: 发布文章
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/user_publish_article")
	public String user_publish_article(HttpServletRequest request, @RequestParam("publishType") String publishType,
			@RequestParam("article_title") String article_title, @RequestParam("article_type") String article_type,
			@RequestParam("article_content") String article_content) {
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("account");
		System.out.println(article_type);
		boolean result = articleService.user_publish_article(publishType, article_title, article_type, article_content,
				user_account);
		if (result) {
			return "index";
		}

		return "write_article";
	}

	/**
	 * 
	 * @Title: user_articles
	 * @Description: 查询文章数据（分页查询）
	 * @return String 返回类型
	 * @throws ParseException
	 */
	@RequestMapping(value = "/user_articles")
	public String user_articles(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model)
			throws ParseException {
		// 引入PageHelper分页插件

		PageHelper.startPage(pn, 1);
		List<Article> articles = articleService.getAll();

		// 使用PageInfo包装查询后的结果
		PageInfo page = new PageInfo(articles);
		model.addAttribute("pageInfo", page);
		return "user_articles";
	}

	/**
	 * 
	 * @Title: article_management
	 * @Description: 实现文件管理功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/article_management")
	public String article_management() {
		return null;

	}
}
