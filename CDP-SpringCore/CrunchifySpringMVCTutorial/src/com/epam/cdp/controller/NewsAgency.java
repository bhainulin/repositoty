package com.epam.cdp.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.cdp.entity.News;
import com.epam.cdp.service.INewsService;

@Controller
@RequestMapping("news")
public class NewsAgency {

	@Autowired
	private INewsService newsService;

	@RequestMapping(method = RequestMethod.GET)
	public String getNews(Map<String, Object> map) {
		map.put("news", new News());
		map.put("newsList", newsService.getNewsList());
		return "news";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/news";
	}
	
	@RequestMapping("/create")
	public String create(Map<String, Object> map) {
		map.put("news", new News());
		return "create";
	}
	
	@RequestMapping("/update/{newsId}")
	public String redirectToUpdate(@PathVariable("newsId") Integer newsId,Map<String, Object> map) {
		map.put("news", newsService.getNews(newsId));
		return "edit";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("news") News news, BindingResult result) {
		newsService.editNews(news);
		return "redirect:/news/" + news.getId();
	}

	@RequestMapping(value = "/{newsId}", method = RequestMethod.DELETE)
	public String deleteNews(@PathVariable("newsId") Integer newsId) {
		newsService.deleteNews(newsId);
		return "redirect:/news";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(News news) {
		news.setCreated(new Date());
		newsService.addNews(news);
		return "redirect:/news/" + news.getId();
	}

	@RequestMapping(value = "/{newsId}", method = RequestMethod.GET)
	public String viewNews(@PathVariable("newsId") Integer newsId,
			Map<String, Object> map) {
		map.put("newsView", newsService.getNews(newsId));
		return "view";
	}
}
