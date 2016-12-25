package com.ir.learning.springbootpoc.domainmodel;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Product.getProductPrice", query="SELECT product.productPrice FROM Product product WHERE product.id = :id")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String productName;
	private String productType;
	private String description;
	private double productPrice;
	private Date prodctMfgDate;
	
	public Product() {
		super();
	}
	
	public Product(String productName, String productType, String description, double productPrice,
			Date prodctMfgDate) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.description = description;
		this.productPrice = productPrice;
		this.prodctMfgDate = prodctMfgDate;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public Date getProdctMfgDate() {
		return prodctMfgDate;
	}
	public void setProdctMfgDate(Date prodctMfgDate) {
		this.prodctMfgDate = prodctMfgDate;
	}
	
	
	
	

}
