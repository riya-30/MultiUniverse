package com.assignment.domain;

public class FamilyAndUniverse {
	private int familyId;
	private int universeId;
	
	public FamilyAndUniverse() {
	}

	public FamilyAndUniverse(int familyId, int universeId) {
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
	
	
}
