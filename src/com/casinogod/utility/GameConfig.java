package com.casinogod.utility;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	
	private  Document doc = null;
	private  long lastCheckTime = System.currentTimeMillis();
	private  static long modifyTime = System.currentTimeMillis();
	private	 String name; 
	private	 volatile boolean loading = false;
	private	 Map<String, String> buffer = Collections.synchronizedMap(new HashMap<String, String>());
	private  String path;
	
	public GameConfig(String confName){
		this.name = confName;
	}
	
	public GameConfig(String confName, String confPath){//e.g.  CONF/XXX
		this.name = confName;
		this.path = this.getClass().getResource("").getPath();
		this.path = path.substring(path.indexOf("/"), path.indexOf("WEB-INF")) + "WEB-INF" + File.separator + confPath +".xml";
	}
	
	public  String getConfigPath(){
		if(path == null) {
			path = this.getClass().getResource("").getPath();
			System.out.println("path1-->"+path);
			path = path.substring(path.indexOf("/"), path.indexOf("WEB-INF"))+"WEB-INF"+File.separator+"GAME-CONF"+File.separator+name;
			System.out.println("path2-->"+path);
		}
		return path;
	}
	
	public  boolean isModified() {
		String filePath = getConfigPath();
		return (modifyTime != new File(filePath).lastModified());
	}
	
	public void loadConfig(){
		try {
			doc = null;
			SAXReader reader = new SAXReader();
			String filePath = getConfigPath();
			File file = new File(filePath);
			modifyTime = file.lastModified();
			doc = reader.read(file);
			lastCheckTime = System.currentTimeMillis();
			buffer.clear();//清除旧的缓存
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public  void updateConfigDoc(){
		if ((System.currentTimeMillis() - lastCheckTime) > 5*60*1000 || doc == null) {
			if (isModified()) {				
				synchronized(this) {					
					if(isModified()) {
						//loading = true;//still there is thread sync problem, but no big deal for little importance and frequency of this block
						loadConfig();
						//loading = false;
					}
				}
			}
		}
	}
	/*
	public  String getConfigValue(String s1, String s2){
		String ret = null;
		try {
			updateConfigDoc();
			ret = buffer.get(s1 + "_" + s2);
			if (ret != null) {
				return ret;
			}
			Element root = doc.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				if (element.getName().equals(s1)){
					for (Iterator<?> j = element.elementIterator(); j.hasNext();) {
						Element e = (Element) j.next();
						if (e.getName().equals(s2)){
							ret = e.getStringValue();
							break;
						}
						
					}				
					break;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		if(ret == null || ret.length() == 0) {
			return null;
		}
		buffer.put(s1 + "_" + s2, ret);
		return ret;
	}
	*/
	public String getConfigValue(String... args) {
		if(args == null || args.length == 0) {
			return null;
		}
		
		String ret = null;
		int len = args.length;	
		
		StringBuffer buf = new StringBuffer();
		for(String str:args) {
			buf.append(str + "_");
		}
		buf.deleteCharAt(buf.length() - 1);
		String key = buf.toString();
		
		try {
			updateConfigDoc();
			if (buffer.containsKey(key)) {//已查找过且未失效（未被清除）
				ret = buffer.get(key);
				return ret;
			}

			Element base = doc.getRootElement();
			
			int depth = 0;
			while(depth < len) {
				depth ++;
				for (Iterator<?> i = base.elementIterator(); i.hasNext();) {
					Element element = (Element) i.next();
					if (element.getName().equals(args[depth - 1])){
						if(depth < len) {
							base = element;
						}
						else {
							ret = element.getStringValue().trim();
						}
						break;
					}
				}
				if(ret != null) {
					break;
				}			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		if(ret == null || ret.length() == 0) {
			ret = null;
		}
		buffer.put(key, ret);
		return ret;		
	}
	
	public static void main(String args[])
	{
		 String configName = "lottery-config.xml";
		 GameConfig config = new GameConfig(configName);
		
		System.out.println(config.getConfigValue("gold","first"));
	}

}
