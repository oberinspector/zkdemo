package net.empego.zkbasics.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class ContentService {
	
	private Map<String, String>contentStore = new HashMap<String, String>();
	{
		contentStore.put("home", 
				"<div xmlns=\"http://www.zkoss.org/2005/zk/native\">" +
				"<h1>Welcome to the Home of ZK-Basics</h1>" +
				"<p>Take a seat and feel like...</p></div>");
		contentStore.put("p1", 
				"<div xmlns=\"http://www.zkoss.org/2005/zk/native\">" +
				"<h1>Now ready for take off</h1>" +
				"<p>Keep clear the door and fasten your seat belt</p></div>");
		contentStore.put("p2", 
				"<div xmlns=\"http://www.zkoss.org/2005/zk/native\">" +
				"<h1>Ask your questions</h1>" +
				"<p>The answer is 42... as always</p></div>");
		contentStore.put("p3", "<zk><calendar/></zk>");
	}
	
	public String getContent(String key){
		return contentStore.get(key);
	}
}
