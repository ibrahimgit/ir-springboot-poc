package com.ir.learning.springbootpoc.domainmodel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

public class Student {
	
	//@NotNull(message = "First name cannot be null")
	@NotEmpty(message = "First name cannot be empty")
	private String firstName;
	//@NotNull(message = "Last name cannot be null")
	@NotEmpty(message = "Last name cannot be empty")
	private String lastName;
	//@NotNull(message = "Email cannot be null")
	//@NotEmpty(message = "Email name cannot be empty")
	@Email(message = "Email is not valid")
	private String email;
	@Max(100)
	@Min(3)
	private int age;
	
	@SafeHtml
	@NotNull(message = "safeString cannot be null")
	@NotEmpty(message = "safeString cannot be empty")
	private String safeString;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSafeString() {
		return safeString;
	}
	public void setSafeString(String safeString) {
		this.safeString = safeString;
	}

}
