package com.ir.learning.springbootpoc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.YmlProperties;

@ConfigurationProperties(prefix="spring.learning")
@RestController
@RequestMapping("ymlprop")
public class YmlPropertiesController {
	
	private List<Integer> mylist;
	private Map<String, Integer> mymap;
	private String name;
	
	@Value("${Spring.learning.value}")
	String value;
	
	@Autowired
	private Environment environemnt;
	
	//The getters and setters are advisable, since binding is via standard Java Beans property
	// descriptors, just like in Spring MVC. 
	
	public List<Integer> getMylist() {
		return mylist;
	}

	public void setMylist(List<Integer> mylist) {
		this.mylist = mylist;
	}

	public Map<String, Integer> getMymap() {
		return mymap;
	}

	public void setMymap(Map<String, Integer> mymap) {
		this.mymap = mymap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	
	@RequestMapping("listmap")
	public YmlProperties listmap() {
		
		YmlProperties ymlproperties = new YmlProperties();
		ymlproperties.setMylist(mylist);
		ymlproperties.setMymap(mymap);
		ymlproperties.setName(name);
		
		return ymlproperties;
	}

	@RequestMapping("envListMap")
	public YmlProperties envListMap() {
		
		YmlProperties ymlproperties = new YmlProperties();
		ymlproperties.setName(environemnt.getProperty("spring.learning.name"));
		ymlproperties.setValue(value);
		//ymlproperties.setMymap(environemnt.getProperty("spring.learning.mymap"));
		//ymlproperties.setName(name);
		
		return ymlproperties;
	}
	
	

}
