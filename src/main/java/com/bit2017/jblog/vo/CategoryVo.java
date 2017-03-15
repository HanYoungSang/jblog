package com.bit2017.jblog.vo;

/**
 * @author bit-user
 *
 */
public class CategoryVo {

	private Long no;
	private String name;
	private String description;
	private String regDate;
	private String blogID;
	private Long postCount;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogID() {
		return blogID;
	}
	public void setBlogID(String blogID) {
		this.blogID = blogID;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPostCount() {
		return postCount;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description="
				+ description + ", regDate=" + regDate + ", blogID=" + blogID
				+ ", postCount=" + postCount + "]";
	}
	
	
}
