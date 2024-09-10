package org.house.predict.client;

import java.util.*;

import org.house.predict.model.AminityModel;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.AreaSquareFeetModel;
import org.house.predict.model.CityMasterModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;
import org.house.predict.service.AminityService;
import org.house.predict.service.AreaSquareFeetService;
import org.house.predict.service.CityMasterService;
import org.house.predict.service.PropertyService;

public class PredictionClientApplication {

	public static void main(String[] args) {
		CityMasterService cms = new CityMasterService();
		AreaSquareFeetService areaSq = new AreaSquareFeetService();
		AminityService amService = new AminityService();
		PropertyService propService = new PropertyService();
		do {
			Scanner scn = new Scanner(System.in);
			int choice;
			System.out.println("1.Add new City");
			System.out.println("2.Display All Cities");
			System.out.println("3.Add new Bulk Cities");
			System.out.println("4.Add new Area");
			System.out.println("5.Display City Wise Area Count");
			System.out.println("6.Display City Wise Area Names");
			System.out.println("7.Add Area in Square Feet");
			System.out.println("8.Add Aminities");
			System.out.println("9.Insert property");
			System.out.println("Enter your choice");
			choice = scn.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter city Name");
				scn.nextLine();
				String cityName = scn.nextLine();
				CityMasterModel model = new CityMasterModel();
				model.setCityName(cityName);
				boolean b = cms.isAddcity(model);

				if (b) {
					System.out.println("New City Added Succefully.....");
				} else {
					System.out.println("City Not Added.....");
				}
				break;
			case 2:
				List<CityMasterModel> list = cms.getAllCities();
				System.out.println("CityId\tCityName\n=================");
				if (list != null) {
					list.forEach((m) -> System.out.println(m.getCityId() + "\t" + m.getCityName()));
				} else {
					System.out.println("There is no city Present.....");
				}
				break;
			case 3:
				b = cms.isBulkAddCities();
				if (b) {
					System.out.println("Cities Added Succesfully.....");
				} else {
					System.out.println("Some Problem is there.....");
				}
				break;
			case 4:
				scn.nextLine();
				System.out.println("Enter city name");
				cityName = scn.next();// PUNE
				int cityId = cms.getcityId(cityName);

				if (cityId != -1) {
					System.out.println("Enter Area name");
					String areaName = scn.next();// karve
					int aId = cms.getAreaIdAutomatic();
					// System.out.println(cityId+"\t"+areaName+"\t"+areaid);
					AreaMasterModel ammodel = new AreaMasterModel();
					ammodel.setCityId(cityId);
					ammodel.setAreaName(areaName);
					ammodel.setAreaId(aId);
					b = cms.isAddArea(ammodel);
					if (b) {
						System.out.println("Area Added Succesfully.......");
					} else {
						System.out.println("Area Not Added.....");
					}
				} else {
					System.out.println("City not present in database.....");
					System.out.println("Do you want to add new city in database.....");

					String msg = scn.nextLine();
					if (msg.equals("yes")) {
						cityName = scn.nextLine();
						model = new CityMasterModel();
						model.setCityName(cityName);
						b = cms.isAddcity(model);

						if (b) {
							System.out.println("New City Added in Database table.....");
						} else {
							System.out.println("City Not Added.....");
						}
					} else {
						System.out.println("THANK YOU.....");
					}
				}
				break;
			case 5:
				LinkedHashMap<String, Integer> map = cms.getCityWiseCount();
				Set<Map.Entry<String, Integer>> s = map.entrySet();
				System.out.println("City\tAreas\n===============");
				for (Map.Entry<String, Integer> m : s) {
					System.out.println(m.getKey() + "\t" + m.getValue());
				}
				break;
			case 6:
				LinkedHashMap<String, ArrayList<String>> areaNames = cms.getCityWiseAreaNames();
				Set<Map.Entry<String, ArrayList<String>>> set = areaNames.entrySet();
				for (Map.Entry<String, ArrayList<String>> m : set) {

					ArrayList<String> values = m.getValue();
					if (values.size() > 0) {
						System.out.println("City Name " + m.getKey());
						System.out.println("============================");
						for (String areaName : values) {
							System.out.println(areaName);
						}
						System.out.println("===========================\n");
					}
				}
				break;
			case 7:
				System.out.println("Enter Area in square feet");
				int sqFeet = scn.nextInt();

				AreaSquareFeetModel areaFeetModel = new AreaSquareFeetModel();
				areaFeetModel.setSquareFeet(sqFeet);
				b = areaSq.isAddSquareFeet(areaFeetModel);
				if (b) {
					System.out.println("Square Feet Added in Database Table");
				} else {
					System.out.println("Some Problem is there ");
				}
				break;
			case 8:
				scn.nextLine();
				System.out.println("Enter Aminity name");
				String name = scn.nextLine();
				AminityModel ammodel = new AminityModel();
				ammodel.setName(name);
				b = amService.isAddAminity(ammodel);
				if (b) {
					System.out.println("New Aminity Added...");
				} else {
					System.out.println("No Aminities Added");
				}
				break;
			case 9:
				scn.nextLine();
				System.out.println("Enter city name");
				cityName = scn.nextLine();
				System.out.println("Enter Area Name");
				String areaName = scn.nextLine();
				System.out.println("Enter Address of Property");
				String PropertyAddress = scn.nextLine();
				System.out.println("Enter Land Area");
				int landArea = scn.nextInt();
				System.out.println("Enter nbed and nbath Rooms");
				int nbed = scn.nextInt();
				int nbath = scn.nextInt();
				cityId = cms.getcityId(cityName);
				AreaMasterModel m = new AreaMasterModel();
				m.setCityName(cityName);
				m.setAreaName(areaName);
				int areaId = cms.getAreaId(m);
				System.out.println(areaId);

				int sqid = areaSq.getSquareFeetId(landArea);
				if (sqid == -1) {
					System.out.println("There is some Exception");
				}
				if (sqid == 0) {
					System.out.println("Square are not present do you want to add new area");
				}
				List<AminityModel> aminitylist = new ArrayList<AminityModel>();
				String str = "";
				do {
					scn.nextLine();
					System.out.println("Enter Aminity name");
					String amname = scn.nextLine();
					AminityModel amModel = new AminityModel();
					amModel.setName(amname);
					aminitylist.add(amModel);
					System.out.println("Do you want to add more aminities ");
				} while (str.equals("yes"));

				PropertyModel propModel = new PropertyModel();
				propModel.setAreaModel(m);
				propModel.setName(PropertyAddress);
				propModel.setNbath(nbath);
				propModel.setNbed(nbed);
				areaFeetModel = new AreaSquareFeetModel();
				areaFeetModel.setSquareFeet(landArea);
				areaFeetModel.setId(sqid);
				propModel.setSqModel(areaFeetModel);

				System.out.println("Enter price and date of registry");
				int price = scn.nextInt();
				scn.nextLine();
				String rDate = scn.nextLine();
				DealModel dModel = new DealModel();
				dModel.setPrice(price);
				dModel.setDate(rDate);
				propModel.setDealModel(dModel);
				b = propService.isAddProperty(propModel);
				if (b) {
					System.out.println("New Property Added Succesfully...");
				} else {
					System.out.println("Property Not Added...");
				}

			}
		} while (true);

	}

}
