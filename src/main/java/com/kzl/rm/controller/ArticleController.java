package com.kzl.rm.controller;

import java.text.ParseException;
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
import com.kzl.rm.bean.Comment;
import com.kzl.rm.service.ArticleService;
import com.kzl.rm.service.CommentService;
import com.kzl.rm.utils.RemoveHTML;

/**
 * 
 * @ClassName: ArticleController
 * @Description:对文章进行增删改查操作
 * @author kezeli
 * @date 2018年5月5日 下午4:08:11
 *
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;

	/**
	 * 
	 * @Title: write_article
	 * @Description: 写文章的页面
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/write_article")
	public String write_article(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("account") == null)
			return "login";

		return "write_article";
	}

	/**
	 * 
	 * @Title: user_publish_article
	 * @Description: 发布文章
	 * @return String 返回类型
	 * @throws ParseException
	 */
	@RequestMapping(value = "/user_publish_article")
	public String user_publish_article(HttpServletRequest request, @RequestParam("publishType") String publishType,
			@RequestParam("article_title") String article_title, @RequestParam("article_type") String article_type,
			@RequestParam("article_content") String article_content) throws ParseException {
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("account");
		// System.out.println(article_type);
		boolean result = articleService.user_publish_article(publishType, article_title, article_type, article_content,
				user_account);
		if (result) {
			return "redirect:/user_articles";
		}

		return "redirect:/write_article";
	}

	/**
	 * 
	 * @Title: user_articles
	 * @Description: 查询文章数据（分页查询）
	 * @return String 返回类型
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user_articles")
	public String user_articles(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("account") == null)
			return "login";

		String account = (String) session.getAttribute("account");
		// 引入PageHelper分页插件
		List<Article> articles = articleService.getAllByAccount(pn, account);

		for (Article article : articles) {
			String content = article.getArticleContent();
			article.setPlantext(RemoveHTML.Html2Text(content));
			article.setAuthorAccount(account);
		}
		// 使用PageInfo包装查询后的结果
		@SuppressWarnings("unchecked")
		PageInfo page = new PageInfo(articles);
		model.addAttribute("pageInfo", page);
		return "user_articles";
	}

	/**
	 * 
	 * @Title: article_management
	 * @Description: 实现文件管理功能(只能操作自己的文章)
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/article_management")
	public String article_management(HttpServletRequest request,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) throws ParseException {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		List<Article> articles = articleService.getAllByAccount(pn, account);

		// 使用PageInfo包装查询后的结果
		PageInfo page = new PageInfo(articles);
		model.addAttribute("pageInfo", page);
		return "article_management";

	}

	/**
	 * 
	 * @Title: findArticleById
	 * @Description: 实现文章详细页跳转功能
	 * @return String 返回类型
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/article_details")
	public String findArticleById(@RequestParam(value = "articleId") long articleId, Model model) {
		Article article = articleService.findArticleById(articleId);
		String account = articleService.findAccountByUserId(article.getAuthorId());
		article.setAuthorAccount(account);
		articleService.updateArticleAddPraise(articleId);
		List<Comment> comments = articleService.findCommentByArticleId(articleId);
		if (article != null) {
			model.addAttribute("ArticleInfo", article);

			if (comments != null)
				model.addAttribute("comments", comments);

			return "article_details";
		}
		return "error";
	}

	/**
	 * 
	 * @Title: editArticle
	 * @Description: 实现文章编辑功能(只能操作自己的文章)
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/edit_article")
	public String editArticle(@RequestParam(value = "articleId") long articleId, Model model) {
		Article article = articleService.findArticleById(articleId);
		if (article != null) {
			model.addAttribute("EditArticleInfo", article);
			String[] list = new String[] { "人工智能", "移动开发", "后台开发", "架构", "运维", "游戏开发", "云计算/大数据", "数据库", "前端", "编程语言",
					"研发管理", "安全", "程序人生", "区块链" };
			model.addAttribute("List", list);
			return "edit_article";
		}
		return "error";
	}

	/**
	 * 
	 * @Title: updataArticle
	 * @Description: 实现文章修改功能(只能操作自己的文章)
	 * @return String 返回类型
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/update_article")
	public String updataArticle(HttpServletRequest request, @RequestParam("article_Id") String article_Id,
			@RequestParam("publishType") String publishType, @RequestParam("article_title") String article_title,
			@RequestParam("article_type") String article_type, @RequestParam("article_content") String article_content,
			Model model) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		boolean result = articleService.updataArticle(article_Id, publishType, article_title, article_type,
				article_content);

		if (result) {

			List<Article> articles = articleService.getAllByAccount(1, account);

			// 使用PageInfo包装查询后的结果
			@SuppressWarnings("unchecked")
			PageInfo page = new PageInfo(articles);
			model.addAttribute("pageInfo", page);
			return "redirect:/article_management";
		}

		return "error";

	}

	/**
	 * 
	 * @Title: deleteArticle
	 * @Description: 实现文章删除功能(只能操作自己的文章)
	 * @return String 返回类型
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete_article")
	public String deleteArticle(HttpServletRequest request, @RequestParam("article_Id") String article_Id,
			Model model) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		boolean result = articleService.deleteArticle(article_Id);
		commentService.deleteCommentByArticleId(article_Id);
		if (result) {
			List<Article> articles = articleService.getAllByAccount(1, account);

			// 使用PageInfo包装查询后的结果
			@SuppressWarnings("unchecked")
			PageInfo page = new PageInfo(articles);
			model.addAttribute("pageInfo", page);
			return "article_management";
		}
		return "error";
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 显示所有文章
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/all_articles")
	public String all_articles(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model)
			throws ParseException {
		// 引入PageHelper分页插件
		PageHelper.startPage(pn, 8);
		List<Article> articles = articleService.getAll();

		for (Article article : articles) {
			String content = article.getArticleContent();
			article.setPlantext(RemoveHTML.Html2Text(content));
			String account = articleService.findAccountByUserId(article.getAuthorId());
			article.setAuthorAccount(account);
		}
		// 使用PageInfo包装查询后的结果
		@SuppressWarnings({ "rawtypes", "unchecked" })
		PageInfo page = new PageInfo(articles);
		model.addAttribute("pageInfo", page);
		return "all_articles";
	}

	/**
	 * 
	 * @Title: search_article
	 * @Description: 搜索文章
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/search_article")
	public String search_article(@RequestParam(value="search_name")  String search_name, Model model)
			throws ParseException {
		if(search_name==null||search_name=="")
			return "search_article";
		List<Article> articles = articleService.getAllSearch(search_name);

		for (Article article : articles) {
			String content = article.getArticleContent();
			article.setPlantext(RemoveHTML.Html2Text(content));
			String account = articleService.findAccountByUserId(article.getAuthorId());
			article.setAuthorAccount(account);
		}
		model.addAttribute("articles",articles);
		return "search_article";
	}
}
