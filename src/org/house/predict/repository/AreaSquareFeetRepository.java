package org.house.predict.repository;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AreaSquareFeetModel;

public class AreaSquareFeetRepository extends DBHelper {

	public boolean isAddSquareFeet(AreaSquareFeetModel model) {
		try
		{
			this.stmt = conn.prepareStatement("insert into areasquarefeet values('0',?)");
			stmt.setInt(1,model.getSquareFeet());
			int value = stmt.executeUpdate();
			return value>0 ? true : false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	public int getSquareFeetId(int areaSqFeet) {
		try {
			stmt=conn.prepareStatement("select sqid from areasquarefeet");
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("Error is "+e);
			return -1;
		}
	}
}
