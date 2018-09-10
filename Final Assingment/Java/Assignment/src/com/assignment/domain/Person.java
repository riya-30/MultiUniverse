package com.assignment.domain;

public class Person {
	private int personId;
	private String name = "";
	private int familyId;
	private int universeId;
	private int power;
	
	
	public Person() {
	}

	public Person(String name, int familyId, int universeId, int power) {
		this.name = name;
		this.familyId = familyId;
		this.universeId = universeId;
		this.power = power;
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public int getUniverseId() {
		return universeId;
	}
	public void setUniverseId(int universeId) {
		this.universeId = universeId;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}	
	
}
