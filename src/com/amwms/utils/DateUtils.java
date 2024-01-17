package com.amwms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static Date toDate(String str) {
		Date date = null;
		if(!str.equalsIgnoreCase("")) {			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date ;
	}
}
