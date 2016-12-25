package com.ir.learning.springbootpoc.domainmodel;

import java.util.List;
import java.util.Map;

public class YmlProperties {
	
	private List<Integer> mylist;
	private Map<String, Integer> mymap;
	private String name;
	private String value;
	
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
	
	
}
