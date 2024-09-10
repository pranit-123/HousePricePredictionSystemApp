package org.house.predict.service;

import org.house.predict.model.AminityModel;
import org.house.predict.repository.AminityRepository;

public class AminityService {

	AminityRepository amRepo = new AminityRepository();
	public boolean isAddAminity(AminityModel model) {
		return amRepo.isAddAminity(model);
	}
}
