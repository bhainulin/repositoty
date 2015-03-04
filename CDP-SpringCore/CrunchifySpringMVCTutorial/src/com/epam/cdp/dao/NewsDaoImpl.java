package com.epam.cdp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.cdp.entity.News;

@Repository
public class NewsDaoImpl implements INewsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getAll() {
		return getSession().createQuery("from News").list();
	}

	@Override
	public News get(int id) {
		return (News) getSession().get(News.class, id);
	}

	@Override
	public void add(News news) {
		getSession().save(news);
	}

	@Override
	public void update(News news) {
		getSession().update(news);
	}

	@Override
	public void remove(int id) {
		News news = (News) getSession().load(News.class, id);
		if (null != news) {
			getSession().delete(news);
		}
	}

}
