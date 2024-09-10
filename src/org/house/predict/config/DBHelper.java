package org.house.predict.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
public class DBHelper {
   
	protected DBConfig db = DBConfig.getDBInstance();
    protected Connection conn=DBConfig.getConnection();
    protected PreparedStatement stmt = DBConfig.getStatement();
    protected ResultSet rs = DBConfig.getResultSet();
    protected int areaid = 0;
}
