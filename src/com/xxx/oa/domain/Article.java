package com.xxx.oa.domain;

import java.util.Date;


public class Article {

		private Long id;
		
		private String title;
		private String content;
		private String faceIcon;
		
		
		private User author;
		private Date postTime;
		private String ipAddr;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getFaceIcon() {
			return faceIcon;
		}
		public void setFaceIcon(String faceIcon) {
			this.faceIcon = faceIcon;
		}
		public User getAuthor() {
			return author;
		}
		public void setAuthor(User author) {
			this.author = author;
		}
		public Date getPostTime() {
			return postTime;
		}
		public void setPostTime(Date postTime) {
			this.postTime = postTime;
		}
		public String getIpAddr() {
			return ipAddr;
		}
		public void setIpAddr(String ipAddr) {
			this.ipAddr = ipAddr;
		}
}
