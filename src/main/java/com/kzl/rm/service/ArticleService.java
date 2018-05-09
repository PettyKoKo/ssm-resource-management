package com.kzl.rm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kzl.rm.bean.Article;
import com.kzl.rm.bean.User;
import com.kzl.rm.dao.ArticleMapper;
import com.kzl.rm.dao.UserMapper;

/**
 * 
 * @ClassName: ArticleService
 * @Description: 文章相关处理业务类
 * @author kezeli
 * @date 2018年5月8日 下午6:33:56
 *
 */
@Service
public class ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	@Autowired
	UserMapper userMapper;

	/**
	 * 
	 * @Title: user_publish_article
	 * @Description:实现文章保存到数据库的功能
	 * @return String 返回类型
	 */
	public boolean user_publish_article(String publishType, String article_title, String article_type,
			String article_content, String user_account) {

		if ((publishType == null || publishType == "") || (article_title == null || article_title == "")
				|| (article_type == null || article_type == "") || (article_content == null || article_content == "")
				|| (user_account == null || user_account == "")) {
			// System.out.println("Hello1");
			return false;
		} else {
			Article article = new Article();
			article.setPublishType(publishType);
			article.setArticleName(article_title);
			article.setArticleType(article_type);
			article.setArticleContent(article_content);
			System.out.println(article.getArticleContent());
			Date now = new Date();
			System.out.println(now);
			article.setFinishTime(now);
			User user = userMapper.findUserByAccount(user_account);
			long userId = 0;
			if (user != null)
				userId = user.getUserId();

			article.setAuthorId(userId);
			int count = articleMapper.insertSelective(article);
			//System.out.println(count);
			return count == 1;
		}

	}

	/**
	 * 
	 * @Title: getAll
	 * @Description: 获取所有文章数据
	 * @return List<Article> 返回类型
	 */
	public List<Article> getAll() {
		List<Article> articles = articleMapper.getAll();
		return articles;
		
	}

}
