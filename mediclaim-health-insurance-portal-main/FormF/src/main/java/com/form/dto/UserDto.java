 package com.form.dto;

public class UserDto 
{	
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
	
	public UserDto(String username, String password, String firstname, String lastname,
			String dob,String city, String state, String nationality, String cpassword, 
			String gender )
	{
		super();
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

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", dob=" + dob + ", city=" + city + ", state=" + state + ", nationality=" + nationality
				+ ", cpassword=" + cpassword + ", gender=" + gender + "]";
		
	}

}
