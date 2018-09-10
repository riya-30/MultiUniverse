package com.assignment.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.assignment.dao.UniverseDao;
import com.assignment.domain.Family;
import com.assignment.domain.FamilyAndUniverse;
import com.assignment.domain.Person;

@Path("/")
public class FamilyService {

	@POST
	@Path("/person/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public boolean addPerson(Person person) {
		UniverseDao universeDao = new UniverseDao();
		return universeDao.addPerson(person);
	}

	@GET
	@Path("/getFamilies")
	@Produces("application/json")
	public  Map<Integer, List<String>> listFamilies(@QueryParam("universeId") int universeId) {
		Map<Integer, List<String>> response = new HashMap<Integer, List<String>>();
		UniverseDao universeDao = new UniverseDao();
		List<Person> persons = universeDao.getPersonLists(universeId);

		for(Person person: persons) {
			
			//add familyId as key and (personId and name) as value in response
			if(!response.containsKey(person.getFamilyId())) {
				response.put(person.getFamilyId(), new ArrayList<String>());
			}
			
			response.get(person.getFamilyId()).add(person.getPersonId() + " "+person.getName());
		}

		return response;
	}

	@GET
	@Path("/isBalanced")
	@Produces("application/json")
	public boolean isBalanced() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		UniverseDao universeDao = new UniverseDao();
		List<Family> families = universeDao.getFamilies();

		for(Family family: families) {
			
			//return false when you got the first entry when power is not equal
			if(map.containsKey(family.getFamilyId()) && map.get(family.getFamilyId()) != family.getFamilyPower()){
				return false;
			}
			map.put(family.getFamilyId(), family.getFamilyPower());
		}

		return true;
	}

	@GET
	@Path("/makeBalanced")
	@Produces("application/json")
	public boolean makeBalanced() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<FamilyAndUniverse, Integer> updatePersonEntries = new HashMap<FamilyAndUniverse, Integer>();
		Map<FamilyAndUniverse, Integer> updateFamilyEntries = new HashMap<FamilyAndUniverse, Integer>();

		UniverseDao universeDao = new UniverseDao();
		List<Family> families = universeDao.getFamilies();

		for(Family family: families) {
			
			//increment/decrement power in person table
			//increment/decrement power in families table
			if(map.containsKey(family.getFamilyId()) && map.get(family.getFamilyId()) != family.getFamilyPower()){
				FamilyAndUniverse familyAndUniverse = new FamilyAndUniverse(family.getFamilyId(), family.getUniverseId());
 				updatePersonEntries.put(familyAndUniverse, map.get(family.getFamilyId())-family.getFamilyPower());
 				updateFamilyEntries.put(familyAndUniverse, map.get(family.getFamilyId()));
			}
			
			map.put(family.getFamilyId(), family.getFamilyPower());
		}
		
		universeDao.updatePower(updatePersonEntries);
		universeDao.updateFamilyPower(updateFamilyEntries);
		return true;
	}

}
