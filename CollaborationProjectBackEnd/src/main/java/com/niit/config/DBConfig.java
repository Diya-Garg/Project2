package com.niit.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.BlogDAO;
import com.niit.dao.ForumDAO;
import com.niit.daoimpl.BlogDAOImpl;
import com.niit.daoimpl.ForumDAOImpl;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;



@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig {
	
	
	//DataSource object
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("project2");
		dataSource.setPassword("project2");
		return dataSource;
	}

	//Create SessionFactory Bean
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(){
		
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto","update");
		hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
		hibernateProperties.put("hibernate.show_sql",true);
		
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProperties);
		sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(Forum.class);
		
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		System.out.println("====SessionFactory Object======");
		return sessionFactory;
		
	}
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
		System.out.println("---Creating Transaction Manager---");
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO(){
		System.out.println("Creating Blog DAO");
		return new BlogDAOImpl();
	}
	
	@Bean(name="forumDAO")
	public ForumDAO getForumDAO(){
		System.out.println("Creating Forum DAO");
		return new ForumDAOImpl();
	}
	
}
