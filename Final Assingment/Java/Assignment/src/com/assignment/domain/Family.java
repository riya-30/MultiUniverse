package com.assignment.domain;

public class Family {
	private int familyId;
	private int universeId;
	private int familyPower;
	
	
	public Family() {
	}

	public Family(int familyId, int universeId) {
		this.familyId = familyId;
		this.universeId = universeId;
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
	public int getFamilyPower() {
		return familyPower;
	}
	public void setFamilyPower(int familyPower) {
		this.familyPower = familyPower;
	}
		
}
