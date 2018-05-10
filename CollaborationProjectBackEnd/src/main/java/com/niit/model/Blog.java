package com.niit.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="Blog")
@SequenceGenerator(name="blogidseq",sequenceName="blogidsequence")
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="blogidseq")
	int blogId;
	
	String blogName;
	String blogContent;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	Date createDate;
	String loginName;
	String status;
	int likes;
	
	
	//@OneToMany(fetch=FetchType.LAZY,mappedBy="blog")
	//private Set<BlogComment> blogComments=new HashSet<BlogComment>();
	
	
	
	
	/*public Set<BlogComment> getBlogComments() {
		return blogComments;
	}
	public void setBlogComments(Set<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}*/
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogName=" + blogName
				+ ", blogContent=" + blogContent + ", createDate=" + createDate
				+ ", loginName=" + loginName + ", status=" + status
				+ ", likes=" + likes + "]";
	}
	
	
	
}
