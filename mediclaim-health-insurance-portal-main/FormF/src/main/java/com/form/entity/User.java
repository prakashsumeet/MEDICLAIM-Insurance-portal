package com.form.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NotNull
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String dob;
	private String city;
	private String state;
	private String nationality;
	private String cpassword;
	private String gender;
	private String imagePath;
	private boolean isAmin;
	private String isVerified = "pending";
	
	public User(Long id, String username, String password, String firstname, String lastname, String dob,
			String city, String state, String nationality, String cpassword, String gender,String isVerified) 
	{
		
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.city = city;
		this.state = state;
		this.nationality = nationality;
		this.cpassword = cpassword;
		this.gender = gender;
		this.isVerified = isVerified;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public String getDob() 
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getNationality()
	{
		return nationality;
	}

	public void setNationality(String nationality) 
	{
		this.nationality = nationality;
	}

	public String getCpassword() 
	{
		return cpassword;
	}

	public void setCpassword(String cpassword) 
	{
		this.cpassword = cpassword;
	}

	public String getGender() 
	{
		return gender;
	}	

	public boolean isAmin() {
		return isAmin;
	}

	public void setAmin(boolean isAmin) {
		this.isAmin = isAmin;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}	
	
	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", dob=" + dob + ", city=" + city + ", state=" + state + ", nationality="
				+ nationality + ", cpassword=" + cpassword + ", gender=" + gender + ", imagePath=" + imagePath
				+ ", isAmin=" + isAmin + ", isVerified=" + isVerified + "]";
	}  	
       public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public User()
       {
    	   
       }
}
	