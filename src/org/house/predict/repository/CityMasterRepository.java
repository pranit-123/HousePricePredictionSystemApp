package org.house.predict.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.*;
import org.house.predict.config.DBConfig;
import org.house.predict.config.DBHelper;
import org.house.predict.config.PathHelper;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;

import com.mysql.cj.jdbc.CallableStatement;

import java.io.*;

public class CityMasterRepository extends DBHelper {
	
	private List<CityMasterModel> list = new ArrayList<CityMasterModel>();
	private List<Object[]> cityWiseAreaCountList;
	LinkedHashMap<String, ArrayList<String>> CityWiseNameAreaMap;
	private LinkedHashMap<String,Integer> map ;
	public boolean isAddNewCity(CityMasterModel model) {
		try {
			stmt = conn.prepareStatement("insert into citymaster values('0',?)");
			stmt.setString(1, model.getCityName());
			int value = stmt.executeUpdate();

			return value>0?true:false; 
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	public List<CityMasterModel> getAllCities(){
		
		try {
			stmt = conn.prepareStatement("select * from citymaster");
			rs = stmt.executeQuery();
			while(rs.next()) {
				CityMasterModel model = new CityMasterModel();
				model.setCityId(rs.getInt(1));
				model.setCityName(rs.getString(2));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	public boolean isBulkAddCities() {
		try {
			FileReader fr = new FileReader(PathHelper.path+"city.txt");
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			int value=0;
			while((line=br.readLine())!=null) {
				String data[] = line.split(",");
				stmt=conn.prepareStatement("insert into citymaster values('0',?)");
				stmt.setString(1, data[1]);
				value=stmt.executeUpdate();
			}
			return value>0 ? true : false;
	     }
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public int getCity(String name) {
		try {
			stmt = conn.prepareStatement("select cityid from citymaster where cityname=?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//System.out.println("cId:"+rs.getInt(1));
				return rs.getInt(1); // if cityid is present then city exist in database table. 
			}
			else {
				return -1; //-1 indicate city not present in database table
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return -1;
		}
	}
	public int getAreaIdAutomatic() {
		try {
			stmt=conn.prepareStatement("select max(aid) from areamaster");
			rs=stmt.executeQuery();
			if(rs.next()) {
				this.areaid =rs.getInt(1);
			}
			++areaid;
			//System.out.println("Area Id:"+areaid);
			return areaid;
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	public boolean isAddArea(AreaMasterModel ammodel) {
		try {
			
			CallableStatement cstmt = (CallableStatement) conn.prepareCall("{call savearea(?,?,?)}");
			cstmt.setInt(1, ammodel.getAreaId());//7
			cstmt.setString(2,ammodel.getAreaName());//kar
			cstmt.setInt(3, ammodel.getCityId());//1
			boolean b = cstmt.execute();
			return !b ;
		}catch(Exception e) {
			System.out.println(e);
		} 
		return false;
	}
/*	
	public List<Object[]> getCityWiseCount(){
		try {
			this.cityWiseAreaCountList = new ArrayList<Object[]>();
			stmt= conn.prepareStatement("SELECT CM.CITYNAME,COUNT(CRJ.CITYID) FROM CITYMASTER CM INNER JOIN CITYAREAJOIN CRJ ON CM.CITYID = CRJ.CITYID INNER JOIN AREAMASTER AM ON AM.AID = CRJ.AID GROUP BY CM.CITYNAME");
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	Object obj[] = new Object[] {rs.getString(1),rs.getInt(2)};
		    	this.cityWiseAreaCountList.add(obj);
		    }
		    return this.cityWiseAreaCountList;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
*/
	public LinkedHashMap<String,Integer> getCityWiseCount(){
		try {
			map = new LinkedHashMap<String,Integer>();
			this.cityWiseAreaCountList = new ArrayList<Object[]>();
			stmt= conn.prepareStatement("SELECT CM.CITYNAME,COUNT(CRJ.CITYID) FROM CITYMASTER CM INNER JOIN CITYAREAJOIN CRJ ON CM.CITYID = CRJ.CITYID INNER JOIN AREAMASTER AM ON AM.AID = CRJ.AID GROUP BY CM.CITYNAME");
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	map.put(rs.getString(1), rs.getInt(2));
		    }
		    return map;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaNames(){
		try {
			this.CityWiseNameAreaMap = new LinkedHashMap<String,ArrayList<String>>();
			stmt=conn.prepareStatement("select cityname from citymaster");
			rs=stmt.executeQuery();
			while(rs.next()) {
				String cityName = rs.getString(1);
				PreparedStatement stmt1 = conn.prepareStatement("select am.areaname,cm.cityname from areamaster am inner join "
						+ "cityareajoin caj on am.aid = caj.aid inner join citymaster cm on caj.cityid = cm.cityid where cm.cityname=?");
			    
				stmt1.setString(1, cityName); 
				ResultSet rs1 = stmt1.executeQuery();
			     ArrayList <String> areaNames = new ArrayList<String>();
			     
			     while(rs1.next()) {
			    	 areaNames.add(rs1.getString(1));
			     }
			     this.CityWiseNameAreaMap.put(cityName, areaNames);
			}
			return this.CityWiseNameAreaMap;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public int getAreaIdByName(AreaMasterModel model) {
		try {
			stmt=conn.prepareStatement("SELECT AM.AID FROM AREAMASTER AM INNER JOIN CITYMASTER CAJ ON AM.AID = CAJ.AID INNER JOIN CITYMASTER CM ON\r\n"
					+ "    -> CM.CITYID=CAJ.CITY WHERE AM.AREANAME=? AND CM.CITYNAME=?;");
            stmt.setString(1, model.getAreaName());
            stmt.setString(2, model.getCityName());
            rs = stmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1);
            }
            else {
            	return 0;
            }
         }
		catch(Exception e) {
			System.out.println(e);
			return -1;
		}
		
	}
} 










