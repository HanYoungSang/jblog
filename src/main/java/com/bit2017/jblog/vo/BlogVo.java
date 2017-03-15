package com.bit2017.jblog.vo;

/**
 * @author bit-user
 *
 */
public class BlogVo {

	
	private String blogID;
	private String title;
	private String logo;
		
	public String getBlogID() {
		return blogID;
	}
	public void setBlogID(String blogID) {
		this.blogID = blogID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [blogID=" + blogID + ", title=" + title + ", logo="
				+ logo + "]";
	}

	
	
}
