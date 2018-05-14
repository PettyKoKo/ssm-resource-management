package com.kzl.rm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kzl.rm.bean.Comment;
import com.kzl.rm.dao.CommentMapper;

/**
 * 
 * @ClassName: CommentService
 * @Description: 对评论进行增删改查操作
 * @author kezeli
 * @date 2018年5月12日 上午11:51:44
 *
 */
@Service
public class CommentService {

	@Autowired
	CommentMapper commentMapper;

	/**
	 * 
	 * @Title: saveArticle_Comment
	 * @Description: 具体实现保存读者对文章评论的功能
	 * @return boolean 返回类型
	 */
	public boolean saveArticle_Comment(String article_Id, String observer_account, String comment_content) {
		Comment comment = new Comment();
		comment.setArticleId(Long.parseLong(article_Id));
		comment.setObserverAccount(observer_account);
		comment.setCommentContent(comment_content);
		Date now = new Date();
		comment.setCommentTime(now);

		int result = commentMapper.insertSelective(comment);
		return result == 1;
	}
	
	

	
}
