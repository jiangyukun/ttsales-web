/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename NewsRespMsg.java
 * @package cn.ttsales.work.wxapi.mp.resp
 * @author dandyzheng
 * @date 2014-9-28
 */
package cn.ttsales.work.wxapi.mp.resp;

import java.util.List;

/**
 * @author dandyzheng
 *
 */
public class NewsRespMsg extends BaseRespMsg {
	private int ArticleCount = 0;
	private Articles Articles;
	
	
	public NewsRespMsg() {
		super.setMsgType(cn.ttsales.work.wxapi.MsgType.NEWS.getName());
	}

	public NewsRespMsg(int articleCount,
			cn.ttsales.work.wxapi.mp.resp.NewsRespMsg.Articles articles) {
		super.setMsgType(cn.ttsales.work.wxapi.MsgType.NEWS.getName());
		ArticleCount = articleCount;
		Articles = articles;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public Articles getArticles() {
		return Articles;
	}

	public void setArticles(Articles articles) {
		Articles = articles;
	}

	public class Articles{
		private List<Item> item;
		
		public Articles(){
			
		}
		
		public Articles(List<Item> item) {
			this.item = item;
		}

		public List<Item> getItem() {
			return item;
		}

		public void setItem(List<Item> item) {
			this.item = item;
		}
		
		public String toString(){
			StringBuilder msg = new StringBuilder();
			msg.append("<Articles>");
			for(Item item : this.getItem()){
				msg.append(item);
			}
			msg.append("</Articles>");
			return msg.toString();
		}
		
		
	}
	
	public class Item{
		private String Title;
		private String Description;
		private String PicUrl;
		private String Url;
		
		public Item(){
			
		}
		
		public Item(String title, String description, String picUrl, String url) {
			Title = title;
			Description = description;
			PicUrl = picUrl;
			Url = url;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
		public String toString(){
			StringBuilder msg = new StringBuilder();
			msg.append("<item>");
			msg.append("<Title><![CDATA[").append(this.getTitle()).append("]]></Title>"); 
			msg.append("<Description><![CDATA[").append(this.getDescription()).append("]]></Description>"); 
			msg.append("<PicUrl><![CDATA[").append(this.getPicUrl()).append("]]></PicUrl>"); 
			msg.append("<Url><![CDATA[").append(this.getUrl()).append("]]></Url>");
			msg.append("</item>");
			return msg.toString();
		}
	}
	
	public String toString(){
		StringBuilder msg = new StringBuilder();
		msg.append("<xml>");
		msg.append(super.toString());
		msg.append("<ArticleCount>").append(String.valueOf(this.getArticleCount())).append("</ArticleCount>");
		msg.append(Articles.toString());
		msg.append("</xml>");
		return msg.toString();
	}

	@Override
	public String toXml() {
		return this.toString();
	}

	
}
