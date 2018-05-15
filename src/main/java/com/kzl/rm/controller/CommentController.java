package com.kzl.rm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kzl.rm.bean.Comment;
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
		String reviewer_account = (String) session.getAttribute("account");
		if (reviewer_account == null) {
			return "error";
		}
		if (observer_account == null || observer_account == "" || article_Id == null || article_Id == ""
				|| comment_content == null || comment_content == "") {
			return "error";
		}

		// System.out.println(observer_account.length());
		boolean result = commentService.saveArticle_Comment(article_Id, observer_account, comment_content,
				reviewer_account);
		if (result) {
			return "redirect:/article_details?articleId=" + article_Id;
		}
		return "error";
	}

	/**
	 * 
	 * @Title: mycomment_management
	 * @Description: 评论管理功能(我发表的评论)
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/mycomment_management")
	public String mycomment_management() {
		return "mycomment_management";
	}

	/**
	 * 
	 * @Title: deleteComment
	 * @Description: 实现对评论的删除功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/deleteComment")
	public String deleteComment(@RequestParam("comment_id") String comment_id,
			@RequestParam("article_Id") String article_Id, Model model) {
		boolean result = commentService.deleteComment(comment_id);
		if (result) {
			return "redirect:/article_details?articleId=" + article_Id;
		}
		return null;

	}

	/**
	 * 
	 * @Title: findCommentByArticleIdAndObeserver
	 * @Description: 通过文章Id来查找与之相关的评论(不包括作者回复的)
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/comment_management")
	public String findCommentByObserver(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String reviewer_account = (String) session.getAttribute("account");
		List<Comment> comments = commentService.findCommentByObserver(reviewer_account);
		if (comments != null)
			model.addAttribute("comments", comments);
		return "comment_management";

	}
}
