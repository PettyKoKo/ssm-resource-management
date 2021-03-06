package com.kzl.rm.dao;

import com.kzl.rm.bean.Article;
import com.kzl.rm.bean.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
	long countByExample(ArticleExample example);

	int deleteByExample(ArticleExample example);

	int deleteByPrimaryKey(Long articleId);

	int insert(Article record);

	int insertSelective(Article record);

	List<Article> selectByExampleWithBLOBs(ArticleExample example);

	List<Article> selectByExample(ArticleExample example);

	Article selectByPrimaryKey(Long articleId);

	int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	List<Article> getAllByAccount(Long authorId);

	List<Article> getAll();

	long countOriginal(Long authorId);

	long countReprint(Long userId);

	long countVisit(Long userId);

	long countDiscuss(Long userId);

	long countTranslate(Long userId);

	int updataArticleAddTread(Long article_Id);

	int updataArticleDelTread(Long article_Id);

	int updateArticleAddPraise(Long article_Id);

	List<Article> getAllSearch(@Param("search_name") String search_name);
}