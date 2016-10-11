package com.xxx.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article {
	/** Common Article */
	public static final int TYPE_NORMAL=0;
	
	/** Gorgeous Article */
	public static final int TYPE_BEST=1;
	/** Top Article */
	public static final int TYPE_TOP=2;
	
	private Forum forum;
	private Set<Reply> replies = new HashSet<Reply>();
	
	private int type;
	private int replyCount;
	private Reply lastReply;
	private Date lastUpdateTime;
	
	
	
	
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public static int getTypeNormal() {
		return TYPE_NORMAL;
	}
	public static int getTypeBest() {
		return TYPE_BEST;
	}
	public static int getTypeTop() {
		return TYPE_TOP;
	}
	
	
}
