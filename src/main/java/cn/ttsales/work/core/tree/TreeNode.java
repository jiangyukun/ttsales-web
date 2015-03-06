/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename TreeNode.java
 * @package ratan.bds.web.common.tree
 * @author lenovoo
 * @date 2013-9-4
 */
package cn.ttsales.work.core.tree;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author lenovoo
 *
 */
public class TreeNode {
	private String id;
	private String text;
	private String state;
	private boolean checked;
	private String iconCls;
	private String target;
	private JSONObject attributes;
	private JSONArray children;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public JSONObject getAttributes() {
		return attributes;
	}
	public void setAttributes(JSONObject attributes) {
		this.attributes = attributes;
	}
	public JSONArray getChildren() {
		return children;
	}
	public void setChildren(JSONArray children) {
		this.children = children;
	}
	
	
}
