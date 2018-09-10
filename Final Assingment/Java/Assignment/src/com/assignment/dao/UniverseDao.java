package com.assignment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assignment.domain.Family;
import com.assignment.domain.FamilyAndUniverse;
import com.assignment.domain.Person;
import com.assignment.util.DbConnection;

public class UniverseDao {
	
	public boolean addPerson(Person person) {
		boolean response = false;

		String name = person.getName();
		int familyId = person.getFamilyId();
		int	universeId = person.getUniverseId();
		int power = person.getPower();

		try {
			Connection connection = DbConnection.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM families WHERE family_id = ? AND universe_id = ?");

			ps.setInt(1, familyId);
			ps.setInt(2, universeId);
			ResultSet rs = ps.executeQuery();

			if(!rs.isBeforeFirst()) {
				PreparedStatement psInsert = connection.prepareStatement("INSERT INTO families VALUES(?, ? , ?)");
				psInsert.setInt(1, familyId);
				psInsert.setInt(2, universeId);
				psInsert.setInt(3, power);
				psInsert.executeUpdate();
			}
			else {
				PreparedStatement psUpdate = connection.prepareStatement("UPDATE families SET family_power = family_power + ? where family_id = ? AND universe_id = ?");
				psUpdate.setInt(1, power);
				psUpdate.setInt(2, familyId);
				psUpdate.setInt(3, universeId);
				psUpdate.executeUpdate();
			}

			PreparedStatement psInsert = connection.prepareStatement("INSERT INTO person (name, family_id, universe_id, power) VALUES(?, ? , ?, ?)");
			psInsert.setString(1, name);
			psInsert.setInt(2, familyId);
			psInsert.setInt(3, universeId);
			psInsert.setInt(4, power);
			psInsert.executeUpdate();

			response = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;

	}

	public List<Person> getPersonLists(int universeId){
		List<Person> persons = new ArrayList<Person>();
		Connection connection;

		try {
			connection = DbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Person where universe_id = ?");

			ps.setInt(1, universeId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Person person = new Person();
				person.setPersonId(rs.getInt(1));
				person.setName(rs.getString(2));
				person.setFamilyId(rs.getInt(3));
				person.setUniverseId(rs.getInt(4));
				person.setPower(rs.getInt(5));

				persons.add(person);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return persons;
	}

	public List<Family> getFamilies(){
		List<Family> families = new ArrayList<Family>();
		Connection connection;

		try {
			connection = DbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM families");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Family family = new Family();
				family.setFamilyId(rs.getInt(1));
				family.setUniverseId(rs.getInt(2));
				family.setFamilyPower(rs.getInt(3));

				families.add(family);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return families;
	}

	public void updatePower(Map<FamilyAndUniverse, Integer> updateEntries) {
		Connection connection;

		try {
			connection = DbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE person SET power = power + ? WHERE family_id = ? AND universe_id = ? LIMIT 1");
			
			for(Map.Entry<FamilyAndUniverse, Integer> updateEntry: updateEntries.entrySet()) {
				ps.setInt(1, updateEntry.getValue());
				ps.setInt(2, updateEntry.getKey().getFamilyId());
				ps.setInt(3, updateEntry.getKey().getUniverseId());
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void updateFamilyPower(Map<FamilyAndUniverse, Integer> updateEntries) {
		Connection connection;

		try {
			connection = DbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE families SET family_power = ? WHERE family_id = ? AND universe_id = ?");
			
			for(Map.Entry<FamilyAndUniverse, Integer> updateEntry: updateEntries.entrySet()) {
				ps.setInt(1, updateEntry.getValue());
				ps.setInt(2, updateEntry.getKey().getFamilyId());
				ps.setInt(3, updateEntry.getKey().getUniverseId());
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
