package com.group.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group.bingSearch.BingPlaceSearch;
import com.group.bingSearch.SearchResults;
import com.group.models.Business;
import com.group.repositories.BusinessRepository;

@Service
public class BusinessService {
	private final BusinessRepository repo;
	private final BingPlaceSearch bingSearch;
	
	public BusinessService(BusinessRepository repo) {
		this.repo = repo;
		this.bingSearch = new BingPlaceSearch();
	}
	
	// Get All Database Entries
	public List<Business> getAll() {
		return repo.findAll();
	}
	
	private String formatAddress(JsonObject address) {
		String addressLocality = address.get("addressLocality").getAsString();
		String addressRegion = address.get("addressRegion").getAsString();
		String postalCode = address.get("postalCode").getAsString();
		String addressCountry = address.get("addressCountry").getAsString();
		String neighborhood = address.get("neighborhood").getAsString();
		
		String finalAddress = "";
		
		if (neighborhood.length() > 0) {
			finalAddress += neighborhood + ", ";
		}
		finalAddress += addressLocality;
		finalAddress += ", " + addressRegion;
		finalAddress += "\n";
		finalAddress += postalCode + ", " + addressCountry;
		
		return finalAddress;
	}
	
	// Perform a bing Places search then convert places to Business objects. Pull Business
	// objects from database when found.
	public List<Business> bingSearch(String searchTerm, String searchLocation) {
		SearchResults result = bingSearch.search(searchTerm, searchLocation);
		JsonParser parser = new JsonParser();
		JsonObject placeJson = parser.parse(result.jsonResponse).getAsJsonObject().getAsJsonObject("places");
		JsonArray places = placeJson.getAsJsonArray("value");
		List<Business> businesses = new ArrayList<Business>();
		
		for (int i = 0; i < places.size(); i++) {
			JsonObject place = (JsonObject)places.get(i);
//			System.out.println(place);
			// Get all the individual attributes of the business
			String name = place.get("name").getAsString();
			
			JsonObject address = place.get("address").getAsJsonObject();
			String fullAddress = this.formatAddress(address);
			String postalCode = address.get("postalCode").getAsString();
			
			JsonElement urlObject = place.get("url");
			String url = "";
			if (urlObject != null) {
				url = urlObject.getAsString();
			}
			String bingId = name + "|" + postalCode + "|" + url;
			String type = place.get("_type").getAsString();
			String telephone = place.get("telephone").getAsString();
			
			Business business = repo.findByBingId(bingId);
			if (business == null) {
				business = new Business();
			}
			// Set or update all variables of business
			business.setName(name);
			business.setBingId(bingId);
			business.setWebsiteUrl(url);
			business.setType(type);
			business.setAddress(fullAddress);
			business.setTelephone(telephone);
			
			if (business.getId() != null) {
				repo.save(business);
			}
						
			businesses.add(business);
		}
		
		return businesses;
	}
	
	// Get One (By ID)
	public Business getBusiness(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	// Get One (By Bing ID)
	public Business getByBingId(String bingId) {
		return repo.findByBingId(bingId);
	}
	
	// Add Business
	public Business addBusiness(Business business) {
		return repo.save(business);
	}
	
	// Update Business
	public Business updateBusiness(Business business) {
		return repo.save(business);
	}
	
	// Delete Business (with ID)
	public void deleteBusiness(Long id) {
		repo.deleteById(id);
	}
	
	// Delete Business (with Business Object)
	public void deleteBusiness(Business business) {
		repo.delete(business);
	}
}