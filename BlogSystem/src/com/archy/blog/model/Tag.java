package com.archy.blog.model;

import java.util.ArrayList;

public class Tag {
	
	private static long id = 1;
	
	// 每个 tag 本身的 id
	private long tag_id;
	private String tagAttribute;
	// 每一个 tag 对应的博文id
	private ArrayList<Long> post_ids;
	
	public Tag(String tagAttribute, Long post_id) {
		this.tag_id = id;
		id++;
		this.tagAttribute = tagAttribute;
		post_ids.add(post_id);
	}
	
	public long getTag_id() {
		return tag_id;
	}
	public void setTag_id(long tag_id) {
		this.tag_id = tag_id;
	}
	public String getTagAttribute() {
		return tagAttribute;
	}
	public void setTagAttribute(String tagAttribute) {
		this.tagAttribute = tagAttribute;
	}
	public ArrayList<Long> getPost_ids() {
		return post_ids;
	}
	public void setPost_ids(ArrayList<Long> post_ids) {
		this.post_ids = post_ids;
	}

}
