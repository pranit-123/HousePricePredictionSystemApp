package org.house.predict.model;

import java.util.*;

public class PropertyModel {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbed() {
		return nbed;
	}

	public void setNbed(int nbed) {
		this.nbed = nbed;
	}

	public int getNbath() {
		return nbath;
	}

	public void setNbath(int nbath) {
		this.nbath = nbath;
	}

	public AreaMasterModel getAreaModel() {
		return areaModel;
	}

	public void setAreaModel(AreaMasterModel areaModel) {
		this.areaModel = areaModel;
	}

	public AreaSquareFeetModel getSqModel() {
		return sqModel;
	}

	public void setSqModel(AreaSquareFeetModel sqModel) {
		this.sqModel = sqModel;
	}

	private String name;
	private int nbed;
	private int nbath;
	private AreaMasterModel areaModel;
	private AreaSquareFeetModel sqModel;
	private List<AminityModel> list;
	private DealModel dealModel;

	public DealModel getDealModel() {
		return dealModel;
	}

	public void setDealModel(DealModel dealModel) {
		this.dealModel = dealModel;
	}

	public List<AminityModel> getList() {
		return list;
	}

	public void setList(List<AminityModel> list) {
		this.list = list;
	}

}
