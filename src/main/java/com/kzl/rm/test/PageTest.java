package com.kzl.rm.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.kzl.rm.bean.Article;

/**
 * 
 * @ClassName: PageTest
 * @Description: 使用Spring测试模块提供的测试请求功能，测试查询功能的正确性
 * @author kezeli
 * @date 2018年5月8日 下午11:36:43
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })

public class PageTest {
	// 传入Springmvc的IOC
	@Autowired
	WebApplicationContext context;
	// 虚拟MVC请求，获取请求结果
	MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		// 模拟请求，拿到 返回值
		System.out.println("Fuck");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user_articles").param("pn", "1")).andReturn();

		// 请求成功后，请求域中汇会有PageInfo,取出PageInfo进行校验
		MockHttpServletRequest request = result.getRequest();
		PageInfo pages = (PageInfo) request.getAttribute("pageInfo");

		System.out.println(pages.getTotal());
		
		List<Article> articles = pages.getList();
		for(Article a : articles){
			System.out.println(a.getArticleName());
		}

	}

}
