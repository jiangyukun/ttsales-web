package cn.ttsales.work.wxapi.corp.send;

import java.util.ArrayList;
import java.util.List;

import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.wxapi.CorpApi;
import net.sf.json.JsonConfig;
/**
touser	否	UserID列表（消息接收者，多个接收者用‘|’分隔）特殊情况：指定为@all，则向关注该企业应用的全部成员发送
toparty	否	PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数
totag	否	TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数
msgtype	是	消息类型，此时固定为：mpnews
agentid	是	企业应用ID
articles	是	图文消息，一个图文消息支持1到10个图文
thumb_media_id	是	图文消息缩略图的media_id, 可以在上传多媒体文件接口中获得。此处thumb_media_id即上传接口返回的media_id
title	是	图文消息的标题
author	否	图文消息的作者
content_source_url	否	图文消息点击“阅读原文”之后的页面链接
content 	是	图文消息的内容，支持html标签
digest	否	图文消息的描述
show_cover_pic	否	是否显示封面，1为显示，0为不显示
safe	否	表示是否是保密消息，0表示否，1表示是，默认0

 * @author fish
 *
 */
public class MPNewsSendMsg extends BaseSendMsg {
	
	private String safe;
	private Articles mpnews;
	public static class Articles {
		
		
		private List<Article> articles;

		public Articles(List<Article> articles) {
			this.articles = articles;
		}
		public Articles() {	
		}
		
		public List<Article> getArticles() {
			return articles;
		}

		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
	}
	public static class Article {
		private String thumb_media_id;
		private String title;
		private String author;
		public String getContent_source_url() {
			return content_source_url;
		}

		public void setContent_source_url(String content_source_url) {
			this.content_source_url = content_source_url;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getDigest() {
			return digest;
		}

		public void setDigest(String digest) {
			this.digest = digest;
		}

		public String getShow_cover_pic() {
			return show_cover_pic;
		}

		public void setShow_cover_pic(String show_cover_pic) {
			this.show_cover_pic = show_cover_pic;
		}

		private String content_source_url;
		private String content;
		private String digest;
		private String show_cover_pic;


		public String getThumb_media_id() {
			return thumb_media_id;
		}

		public void setThumb_media_id(String thumb_media_id) {
			this.thumb_media_id = thumb_media_id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	

	public String getSafe() {
		return safe;
	}



	public void setSafe(String safe) {
		this.safe = safe;
	}



	public Articles getMpnews() {
		return mpnews;
	}



	public void setMpnews(Articles mpnews) {
		this.mpnews = mpnews;
	}

	public static void main(String[] args) {
		
		CorpApi c = CorpApi.getInstance();
		MPNewsSendMsg mpNewsMsg = new MPNewsSendMsg();
		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article());
		articles.add(new Article());
		Articles article = new Articles();
		article.setArticles(articles);
		mpNewsMsg.setMpnews(article);
		
		String msg = JsonUtil.fromObject(mpNewsMsg, new JsonConfig()).toString();
		//MsgReqResult r = c.sendMsg("1", "1", new TextReqMsg());
		System.out.println(msg);
	}

}
