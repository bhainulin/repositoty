package com.epam.cdp.service;

import java.util.List;

import com.epam.cdp.entity.News;

public interface INewsService {

	public List<News> getNewsList();

	public News getNews(int id);

	public void addNews(News news);

	public void editNews(News news);

	public void deleteNews(int id);

}
