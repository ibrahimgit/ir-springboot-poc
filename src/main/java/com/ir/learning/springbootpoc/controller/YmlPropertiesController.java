package com.ir.learning.springbootpoc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.YmlProperties;

@ConfigurationProperties(prefix="spring.learning")
@RestController()
@RequestMapping("ymlprop")
public class YmlPropertiesController {
	
	private List<Integer> mylist;
	private Map<String, Integer> mymap;
	private String name;
	
	
	@RequestMapping("listmap")
	public YmlProperties listmap() {
		
		YmlProperties ymlproperties = new YmlProperties();
		ymlproperties.setMylist(mylist);
		ymlproperties.setMymap(mymap);
		ymlproperties.setName(name);
		
		return ymlproperties;
	}

}
