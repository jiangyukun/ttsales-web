/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename AbstractGenBase.java
 * @package nur.core.gen
 * @author dandyzheng
 * @date 2013-3-28
 */
package cn.ttsales.work.core.gen;

import java.io.File;

import cn.ttsales.work.core.util.FileUtil;




/**
 * @author dandyzheng
 * 
 */
@SuppressWarnings("unused")
public class GenBase {
	private String WORKSPACE = "E:\\workspace\\SASWeb\\";
	
	private String TEMPLATEPATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\core\\gen\\";
	
	/** domain工程项目的JAVA文件目录 **/
	private String DOMAIN_PROJECT_PATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\domain\\";
	/** dao工程项目的JAVA文件目录 **/
	private String DAO_PROJECT_PATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\persistence\\";
	/** server工程项目的JAVA文件目录 **/
	private String SERVER_PROJECT_PATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\service\\";
	/** web工程项目的JAVA文件目录 **/
	private String MB_PROJECT_PATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\web\\";
	/** web工程项目的页面文件目录 **/
	private String PAGE_PROJECT_PATH = WORKSPACE + "src\\main\\webapp\\pages\\";
	
	
	/** 实体的包目录 **/
	private String DMOMAIN_PACKAGE = "cn.ttsales.work.domain";
	/** dao的包目录 **/
	private String DAO_PACKAGE = "cn.ttsales.work.persistence";
	/** service的包目录 **/
	private String SERVICE_PACKAGE = "cn.ttsales.work.service";
	/** mb的包目录 **/
	private String MB_PACKAEE = "cn.ttsales.work.web";
	
	private String p = "cn.ttsales.work";
	/** 主题名 */
	private String THEME = "sys"; //改动
	/**要生成的实体套装名*/ 
	private String CLASS_QZ_NAME = "SysRole"; //改动
	
	private String MB_TYPE = "2"; //改动
	
	/** 模板主题名 */
	private String TEMPLATE_THEME = "rbac";
	/**模板实体名*/
	private String TEMPLATE_CLASS_QZ_NAME = "RbacProp";
	
	public GenBase(String theme,String class_qz_name,String mb_type){
		this.THEME = theme;
		this.CLASS_QZ_NAME = class_qz_name;
		this.MB_TYPE = mb_type;
	}

	private String replaceAll(String str, String oldStr, String newStr) {
		int i = str.indexOf(oldStr);
		while (i != -1) {
			str = str.substring(0, i) + newStr
					+ str.substring(i + oldStr.length());
			i = str.indexOf(oldStr, i + newStr.length());
		}
		return str;
	}
	
	public void createDao() {
		String templateFile = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "Dao.template";
		
		if(!FileUtil.checkExist(this.DAO_PROJECT_PATH +"\\"+this.THEME)){
			FileUtil.mkdir(this.DAO_PROJECT_PATH +"\\"+this.THEME);
		}
		String file = this.DAO_PROJECT_PATH +"\\"+this.THEME + "\\" + this.CLASS_QZ_NAME + "Dao.java";
		writeFile(templateFile, file,this.DAO_PACKAGE+"."+this.THEME);
		
		if(!FileUtil.checkExist(this.DAO_PROJECT_PATH +"\\"+this.THEME+ "\\impl")){
			FileUtil.mkdir(this.DAO_PROJECT_PATH +"\\"+this.THEME+ "\\impl");
		}
		String templateFile2 = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "DaoImpl.template";
		String file2 = this.DAO_PROJECT_PATH +"\\"+this.THEME + "\\impl\\" + this.CLASS_QZ_NAME + "DaoImpl.java";
		writeFile(templateFile2, file2,this.DAO_PACKAGE + "." + this.THEME + ".impl");
	}
	
	public void createService() {
		String templateFile = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "Service.template";
		
		if(!FileUtil.checkExist(this.SERVER_PROJECT_PATH +"\\"+this.THEME)){
			FileUtil.mkdir(this.SERVER_PROJECT_PATH +"\\"+this.THEME);
		}
		
		String file = this.SERVER_PROJECT_PATH +"\\"+this.THEME + "\\" + this.CLASS_QZ_NAME + "Service.java";
		writeFile(templateFile, file,this.SERVICE_PACKAGE+"."+this.THEME);
		
		
		if(!FileUtil.checkExist(this.SERVER_PROJECT_PATH +"\\"+this.THEME + "\\impl")){
			FileUtil.mkdir(this.SERVER_PROJECT_PATH +"\\"+this.THEME + "\\impl");
		}
		
		String templateFile2 = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "ServiceImpl.template";
		String file2 = this.SERVER_PROJECT_PATH +"\\"+this.THEME + "\\impl\\" + this.CLASS_QZ_NAME + "ServiceImpl.java";
		writeFile(templateFile2, file2,this.SERVICE_PACKAGE + "." + this.THEME + ".impl");
	}
	
	public void creatMB() {
		if(!FileUtil.checkExist(this.MB_PACKAEE +"\\"+this.THEME)){
			FileUtil.mkdir(this.MB_PROJECT_PATH +"\\"+this.THEME);
		}
		if("1".equals(this.MB_TYPE)){
			String templateFile = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "2MB.template";
			String file = this.MB_PROJECT_PATH +"\\"+this.THEME + "\\" + this.CLASS_QZ_NAME + "MB.java";
			writeFile(templateFile, file,this.MB_PACKAEE+"."+this.THEME);
		}
		if("2".equals(this.MB_TYPE)){
			String templateFile = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "MB.template";
			String file = this.MB_PROJECT_PATH +"\\"+this.THEME + "\\" + this.CLASS_QZ_NAME + "MB.java";
			writeFile(templateFile, file,this.MB_PACKAEE+"."+this.THEME);
			
			String templateFile2 = TEMPLATEPATH + "\\" + this.TEMPLATE_CLASS_QZ_NAME + "EditMB.template";
			String file2 = this.MB_PROJECT_PATH +"\\"+this.THEME + "\\" + this.CLASS_QZ_NAME + "EditMB.java";
			writeFile(templateFile2, file2,this.MB_PACKAEE+"."+this.THEME);
		}
	}
	
	
	private void writeFile(String templateFile, String filePath,String packagePath) {
		byte[] b = FileUtil.getBytesFromFile(new File(templateFile));
		String fileContent = new String(b);
		fileContent = this.replaceAll(fileContent, this.TEMPLATE_CLASS_QZ_NAME, this.CLASS_QZ_NAME);
		fileContent = this.replaceAll(fileContent, this.TEMPLATE_CLASS_QZ_NAME.substring(0, 1).toLowerCase()
				+ this.TEMPLATE_CLASS_QZ_NAME.substring(1, this.TEMPLATE_CLASS_QZ_NAME.length()),
				CLASS_QZ_NAME.substring(0, 1).toLowerCase()
						+ CLASS_QZ_NAME.substring(1, CLASS_QZ_NAME.length()));
		
		//替换包名
		//psychlab.core.gen
		fileContent = this.replaceAll(fileContent, "psychlab.core.gen", packagePath);
		fileContent = this.replaceAll(fileContent, "psychlab", this.p);
		fileContent = this.replaceAll(fileContent, this.TEMPLATE_THEME, this.THEME);
		System.out.println(fileContent);
		FileUtil.writeByte2File(fileContent.getBytes(), filePath);
	}

	public void createAll() {
		createDao();
		createService();
	}
	
	public static void main(String[] args){
		/*GenBase gen = new GenBase("pro","SdfProGoodsPic","2");
		gen.createDao();
		gen.createService();*/
		//gen.creatMB();
		String WORKSPACE = "E:\\workspace\\SASWeb\\";
		String DOMAIN_PROJECT_PATH = WORKSPACE + "src\\main\\java\\ratan\\sas\\domain\\";
		File domainDir = new File(DOMAIN_PROJECT_PATH);
		if(domainDir.isDirectory()){
			File[] files = domainDir.listFiles();
			for(int i=0;i < files.length; i++){
				if(files[i].isFile()){
					String theme = files[i].getName().substring(0,3).toLowerCase();
					
					String domain = files[i].getName().substring(0,files[i].getName().length()-5);
					if(domain.equals("SysPopularizeTrack")){
						GenBase gen = new GenBase(theme,domain,"1");
						gen.createDao();
						gen.createService();
					}
					//gen.creatMB();
				}
			}
		}
		
	}
}
