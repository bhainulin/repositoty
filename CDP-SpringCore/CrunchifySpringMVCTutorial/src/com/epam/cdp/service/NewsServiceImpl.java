package com.epam.cdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.cdp.dao.INewsDao;
import com.epam.cdp.entity.News;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private INewsDao newsDao;

	@Transactional
	public List<News> getNewsList() {
		return newsDao.getAll();
	}

	@Transactional
	public News getNews(int id) {
		return newsDao.get(id);
	}

	@Transactional
	public void addNews(News news) {
		newsDao.add(news);
	}

	@Transactional
	public void editNews(News news) {
		newsDao.update(news);
	}

	@Transactional
	public void deleteNews(int id) {
		newsDao.remove(id);
	}

}
