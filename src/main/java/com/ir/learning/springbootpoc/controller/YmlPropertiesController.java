package com.ir.learning.springbootpoc.controller;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
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
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	//The getters and setters are advisable, since binding is via standard Java Beans property
	// descriptors, just like in Spring MVC. 
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

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
	
	@RequestMapping("externalProp")
	public YmlProperties externalProp() {
		
		YmlProperties ymlproperties = new YmlProperties();
		ymlproperties.setName(environemnt.getProperty("ibrahim.test.name"));
		ymlproperties.setValue(environemnt.getProperty("springpoc.test"));
		ymlproperties.setProjectName(environemnt.getProperty("ibrahim.test.age"));
		//ymlproperties.setMymap(environemnt.getProperty("spring.learning.mymap"));
		//ymlproperties.setName(name);
		
		return ymlproperties;
	}
	
	@RequestMapping("infoBuild")
	@Transactional
	public YmlProperties infoBuild() {
		
		YmlProperties ymlproperties = new YmlProperties();
		ymlproperties.setProjectName(environemnt.getProperty("test.yml"));
		ymlproperties.setVersion(environemnt.getProperty("info.build.version"));
		ymlproperties.setDescription(environemnt.getProperty("info.build.description"));
		ymlproperties.setName(environemnt.getProperty("test.delimeter"));
		throw new DataRetrievalFailureException("");
		//return ymlproperties;
	}
	
	

}
