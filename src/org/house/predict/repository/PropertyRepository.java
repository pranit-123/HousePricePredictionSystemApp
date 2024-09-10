package org.house.predict.repository;

import java.util.List;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AminityModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;

public class PropertyRepository extends DBHelper {

	public boolean isAddNewProperty(PropertyModel model) {

		int pid = model.getId();
		String propetyName = model.getName();
		int sqid = model.getAreaModel().getAreaId();
		int areaId = model.getAreaModel().getAreaId();
		int cityId = model.getAreaModel().getCityId();
		int nbed = model.getNbed();
		int nbath = model.getNbath();
        System.out.println("Property Master");
        System.out.println("Id\tName\tSquare Feet\tArea Id\tCity Id\tNbed\tNbath");
		System.out.println((pid + 1) + "\t" + propetyName + "\t" + sqid + "\t" + areaId + "\t" + cityId + "\t" + nbed
				+ "\t" + nbath);
        
		List<AminityModel> list = model.getList();
        System.out.println("Aminities");
        int count=0;
        for(AminityModel m : list) {
        	count = count +(m.getId()+1); 
        	System.out.println(count+"\t"+m.getName());
        }
        System.out.println("Property Aminity Relationship");
        count=0;
        pid++;
        for(AminityModel m : list) {
        	System.out.println(pid+"\t"+count);
        }
        System.out.println("Property And price Relationship");
        DealModel dealModel = model.getDealModel();
        System.out.println((dealModel.getTransId()+1)+"\t"+dealModel.getPrice()+"\t"+dealModel.getDate());
		return true;
	}
}
