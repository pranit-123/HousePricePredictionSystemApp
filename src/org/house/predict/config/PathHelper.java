package org.house.predict.config;

import java.io.*;

public class PathHelper {
	public static FileInputStream fin = null;
	public static File f = new File(".");
	public static String path = (f.getAbsolutePath().substring(0, f.getAbsolutePath().length()-1)) + "src\\";
	static {
		//f = new File(".");
		String path1 = path + "db.properties.txt";
		try {
			fin = new FileInputStream(path1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
