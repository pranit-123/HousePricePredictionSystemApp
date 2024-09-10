package org.house.predict.service;

import org.house.predict.model.AreaSquareFeetModel;
import org.house.predict.repository.AreaSquareFeetRepository;

public class AreaSquareFeetService {

	AreaSquareFeetRepository areaSq = new AreaSquareFeetRepository();
	public boolean isAddSquareFeet(AreaSquareFeetModel model) {
		
		return areaSq.isAddSquareFeet(model);
	}
	public int getSquareFeetId(int sqFeet) {
		return areaSq.getSquareFeetId(sqFeet);
	}
}
