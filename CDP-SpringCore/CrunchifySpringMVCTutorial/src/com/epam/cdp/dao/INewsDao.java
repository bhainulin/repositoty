package com.epam.cdp.dao;

import java.util.List;

import com.epam.cdp.entity.News;

public interface INewsDao {
	
	public List<News> getAll();
	
	public News get(int id);
	
	public void add(News news);
	
	public void update(News news);
	
	public void remove(int id);
}
