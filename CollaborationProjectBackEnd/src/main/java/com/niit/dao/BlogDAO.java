package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.UserDetails;

public interface BlogDAO {

	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public List<Blog> listBlogs(String userName,String role);
	public List<Blog> listAllApprovedBlogs();
	public List<Blog> listPendingBlogs();
	public boolean incrementLikes(Blog blog);
	
	public boolean addBlogComment(BlogComment blogComment);
	public boolean deleteBlogComment(BlogComment blogComment);
	public BlogComment getBlogComment(int commentId);
	public List<BlogComment> listBlogComments(int blogId);
}
