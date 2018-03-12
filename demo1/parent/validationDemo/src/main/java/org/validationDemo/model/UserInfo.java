package org.validationDemo.model;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.validationDemo.Utils.CannotHaveBlank;

public class UserInfo {

	@CannotHaveBlank(message="this is martin errors")
	@NotEmpty(message = "Input name",groups= {First.class})  
	@Length(min = 1, max = 10, message = "1-10 char",groups= {First.class}) 
	private String name;
	@Min(value=18,groups= {First.class})
	private Integer age;
	private Date birthday;

	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "phone is error")
	@NotBlank(message = "phone is required")
	private String phone;
	@Email(message = "email is error")
	private String email;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	public interface First { }  
	public interface Second { }  
	  
	@GroupSequence({First.class, Second.class})  
	public interface All {  
	  
	}  
}
