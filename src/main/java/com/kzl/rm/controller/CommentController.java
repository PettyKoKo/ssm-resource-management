package com.kzl.rm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kzl.rm.service.CommentService;

/**
 * 
 * @ClassName: CommentController
 * @Description: 文章评论相关操作
 * @author kezeli
 * @date 2018年5月12日 上午11:37:35
 *
 */
@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	/**
	 * 
	 * @Title: saveArticle_Comment
	 * @Description: 实现保存读者对文章评论的功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/comment_article")
	public String saveArticle_Comment(HttpServletRequest request, @RequestParam("article_Id") String article_Id,
			@RequestParam("observer_account") String observer_account,
			@RequestParam("comment_content") String comment_content) {
		HttpSession session = request.getSession();
		if (session.getAttribute("account") == null) {
			return "error";
		}
		if (observer_account == null || observer_account == "" || article_Id == null || article_Id == ""
				|| comment_content == null || comment_content == "") {
			return "error";
		}

		System.out.println(observer_account.length());
		boolean result = commentService.saveArticle_Comment(article_Id, observer_account, comment_content);
		if (result) {
			return "";
		}
		return "error";
	}
	
}
