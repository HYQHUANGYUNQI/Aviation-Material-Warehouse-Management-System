package com.amwms.utils;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 读取配置文件，建立与Mysql的连接
 * @author BigBing
 *
 */
public class JDBCUtils {

	private static Properties properties = null;
	private static Connection con = null;

	private static String profilePath = "E:/eclipseEE-workspace/AMWMS/conf/conn.properties";
	private static String drivermanager;
	private static String location;
	private static String user;
	private static String password;
	private final static String PARATIME = "serverTimezone=GMT";
	private final static String PARAFLAG = "?";
	
	//静态加载，读取配置文件
	static {
		try {
			properties = new Properties();
			
			//classloader 加载的内容需要在src包下
//			properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream(profilePath));
//			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(profilePath));
			File file = new File(profilePath);
			properties.load(new BufferedInputStream(new FileInputStream(file)));
			
			drivermanager = properties.getProperty("drivermanager");
			location = properties.getProperty("location");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			
			Class.forName(drivermanager);
			
			con = DriverManager.getConnection(location + PARAFLAG + PARATIME , user, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
	public static void close(Closeable ...closeables ) {
		for(Closeable clab:closeables) {
			try {
				if(clab != null) {					
					clab.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {	
		
		return con;
	}
	
}
