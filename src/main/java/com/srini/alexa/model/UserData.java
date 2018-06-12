package com.srini.alexa.model;

public class UserData {
	private String userId;
	private String firstName;
	private String lastName;
	private String planName;
	private String accountBalance;
	private String personalRateofReturn;
	private String benchmark;
	private String age;

   
	public UserData(String userId, String firstName, String lastName, String planName, String accountBalance, String personalRateofReturn, String benchmark, String age) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName= lastName;
		this.planName = planName;
		this.accountBalance = accountBalance;
		this.personalRateofReturn= personalRateofReturn;
		this.benchmark = benchmark;
		this.age = age;
	}
	
    public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



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



	public String getPlanName() {
		return planName;
	}



	public void setPlanName(String planName) {
		this.planName = planName;
	}



	public String getAccountBalance() {
		return accountBalance;
	}



	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}



	public String getPersonalRateofReturn() {
		return personalRateofReturn;
	}



	public void setPersonalRateofReturn(String personalRateofReturn) {
		this.personalRateofReturn = personalRateofReturn;
	}



	public String getBenchmark() {
		return benchmark;
	}



	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	@Override
    public String toString()
    {
        return "ClassPojo [userId = "+userId+"]";
    }
}
