package com.kzl.rm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kzl.rm.bean.Article;
import com.kzl.rm.bean.Comment;
import com.kzl.rm.bean.User;
import com.kzl.rm.dao.ArticleMapper;
import com.kzl.rm.dao.CommentMapper;
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

	@Autowired
	CommentMapper commentMapper;

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
			// System.out.println(count);
			return count == 1;
		}

	}

	/**
	 * 
	 * @Title: getAllByAccount
	 * @Description: 获取某一用户的所有文章
	 * @return List<Article> 返回类型
	 */
	public List<Article> getAllByAccount(int pn, String account) {
		User user = userMapper.findUserByAccount(account);
		if (user != null) {
			// 引入PageHelper分页插件
			PageHelper.startPage(pn, 8);
			List<Article> articles = articleMapper.getAllByAccount(user.getUserId());
			return articles;
		}
		return null;
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

	/**
	 * 
	 * @Title: findArticleById
	 * @Description: 通过文章ID查找文章详细信息
	 * @return Article 返回类型
	 */
	public Article findArticleById(long articleId) {
		Article article = articleMapper.selectByPrimaryKey(articleId);
		return article;
	}

	/**
	 * 
	 * @Title: updataArticle
	 * @Description: 修改文章，进行保存操作
	 * @return boolean 返回类型
	 */
	public boolean updataArticle(String article_Id, String publishType, String article_title, String article_type,
			String article_content) {

		Article article = new Article();
		article.setArticleId(Long.parseLong(article_Id));
		article.setPublishType(publishType);
		article.setArticleName(article_title);
		article.setArticleType(article_type);
		article.setArticleContent(article_content);
		Date now = new Date();
		article.setFinishTime(now);

		int count = articleMapper.updateByPrimaryKeySelective(article);
		return count == 1;
	}

	/**
	 * 
	 * @Title: deleteArticle
	 * @Description: 文章删除功能的具体实现
	 * @return boolean 返回类型
	 */
	public boolean deleteArticle(String article_Id) {

		int count = articleMapper.deleteByPrimaryKey(Long.parseLong(article_Id));
		return count == 1;
	}

	/**
	 * 
	 * @Title: findCommentByArticleId
	 * @Description: 通过文章Id来查找与之相关联的评论信息
	 * @return Comment 返回类型
	 */
	public List<Comment> findCommentByArticleId(Long articleId) {
		List<Comment> comments = commentMapper.selectByArticleId(articleId);
		return comments;
	}

	/**
	 * 
	 * @Title: findAccountByUserId
	 * @Description: 通过用户ID来查找用户账号
	 * @return String 返回类型
	 */
	public String findAccountByUserId(Long authorId) {
		User user = userMapper.selectByPrimaryKey(authorId);
		if (user != null)
			return user.getUserAccount();
		return null;
	}

	/**
	 * 
	 * @Title: countOriginal
	 * @Description: 统计原创文章个数
	 * @return long 返回类型
	 */
	public long countOriginal(Long userId) {
		long original = articleMapper.countOriginal(userId);
		return original;
	}

	/**
	 * 
	 * @Title: countReprint
	 * @Description: 统计转载文章个数
	 * @return long 返回类型
	 */
	public long countReprint(Long userId) {
		long reprint = articleMapper.countReprint(userId);
		return reprint;
	}

	/**
	 * 
	 * @Title: countVisit
	 * @Description: 统计总阅读（浏览）数
	 * @return long 返回类型
	 */
	public long countVisit(Long userId) {
		long visit = articleMapper.countVisit(userId);
		return visit;
	}

	/**
	 * 
	 * @Title: countDiscuss
	 * @Description: 统计总评论数
	 * @return long 返回类型
	 */
	public long countDiscuss(Long userId) {
		long discuss = articleMapper.countDiscuss(userId);
		return discuss;
	}

	/**
	 * 
	 * @Title: countTranslate
	 * @Description: 统计翻译文章总数
	 * @return long 返回类型
	 */
	public long countTranslate(Long userId) {
		long translate = articleMapper.countTranslate(userId);
		return translate;
	}

	/**
	 * 
	 * @Title: updataArticleAddTread
	 * @Description: 增加评论，评论数加1
	 * @return int 返回类型
	 */
	public boolean updataArticleAddTread(String article_Id) {
		int count = articleMapper.updataArticleAddTread(Long.parseLong(article_Id));
		return count == 1;
	}

	/**
	 * 
	 * @Title: updataArticleDelTread
	 * @Description: 删除评论，评论数减1
	 * @return boolean 返回类型
	 */
	public boolean updataArticleDelTread(String article_Id) {
		int count = articleMapper.updataArticleDelTread(Long.parseLong(article_Id));
		return count == 1;
	}

	public boolean updateArticleAddPraise(Long article_Id) {
		int count = articleMapper.updateArticleAddPraise(article_Id);
		return count == 1;
	}

	/**
	 * 
	 * @Title: getAllSearch
	 * @Description:模糊搜索
	 * @return List<Article> 返回类型
	 */
	public List<Article> getAllSearch(String search_name) {
		List<Article> articles = articleMapper.getAllSearch(search_name);
		return articles;
	}

}
