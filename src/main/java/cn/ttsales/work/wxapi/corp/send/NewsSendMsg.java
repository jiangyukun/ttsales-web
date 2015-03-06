package cn.ttsales.work.wxapi.corp.send;

import java.util.ArrayList;
import java.util.List;

import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.wxapi.CorpApi;
import cn.ttsales.work.wxapi.MsgType;
import net.sf.json.JsonConfig;
/**
touser 		否 	UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
toparty 	否 	PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数
totag 		否 	TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数
msgtype 	是 	消息类型，此时固定为：news
agentid 	是 	企业应用的id，整型。可在应用的设置页面查看
articles 	是 	图文消息，一个图文消息支持1到10条图文
title 		否 	标题
description 否 	描述
url 		否 	点击后跳转的链接。企业可根据url里面带的code参数校验员工的真实身份。具体参考“9 微信页面跳转员工身份查询”
picurl 		否 	图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片

 * @author fish
 *
 */
public class NewsSendMsg extends BaseSendMsg {
	
	public NewsSendMsg(){
		
	}
	
	public  NewsSendMsg(String agentId){
		super(MsgType.NEWS.getName(), agentId);
		
	}
	
	private Articles news;
	public static class Articles {
		
		
		private List<Article> articles = new ArrayList<Article>();

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
		
		public Article(String title, String description, String url, String picurl) {
			this.title = title;
			this.description = description;
			this.url = url;
			this.picurl = picurl;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getPicurl() {
			return picurl;
		}
		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}
		private String title;
		private String description;
		private String url;
		private String picurl;
		

	}
	
	

	public Articles getNews() {
		return news;
	}



	public void setNews(Articles news) {
		this.news = news;
	}

	public static void main(String[] args) {
		
		CorpApi c = CorpApi.getInstance();
		NewsSendMsg newsMsg = new NewsSendMsg();
		List<Article> articles = new ArrayList<Article>();
		articles.add(new Article("","","",""));
		articles.add(new Article("","","",""));
		Articles news = new Articles();
		news.setArticles(articles);
		newsMsg.setNews(news);
		
		String msg = JsonUtil.fromObject(newsMsg, new JsonConfig()).toString();
		//MsgSendResult r = c.sendMsg("1", "1", newsMsg);
		System.out.println(msg);
	}

}
