package org.house.predict.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;
import org.house.predict.repository.CityMasterRepository;

public class CityMasterService {
	CityMasterRepository cityRepo = new CityMasterRepository();
    
	public boolean isAddcity(CityMasterModel model) {
    	boolean b = cityRepo.isAddNewCity(model);
    	System.out.println(b);
    	return b;
	}

	public List<CityMasterModel> getAllCities(){
		
		return cityRepo.getAllCities();
	}
	public boolean isBulkAddCities() {
		return cityRepo.isBulkAddCities();
	}
	public int getcityId(String cityName) {
		return cityRepo.getCity(cityName);
	}
	public int getAreaIdAutomatic() {
		return cityRepo.getAreaIdAutomatic();
	}
	public boolean isAddArea(AreaMasterModel ammodel) {
		return cityRepo.isAddArea(ammodel);
	}
	public LinkedHashMap<String,Integer> getCityWiseCount(){
		return this.cityRepo.getCityWiseCount();
	}
	public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaNames(){
		return this.cityRepo.getCityWiseAreaNames();
	}
	public int getAreaId(String areaName) {
		return 0;
	}
	public int getAreaId(AreaMasterModel model) {
		return cityRepo.getAreaIdByName(model);
	}
	
}









