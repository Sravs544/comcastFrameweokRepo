package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random ranDom=new Random();
		
		int ranDomNumber=ranDom.nextInt(5000);
		
		return ranDomNumber;
		
		}
	
	public String getSystemDateYYYYMMDD() {
		Date dateObj=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		String date=sdf.format(dateObj);
		
		return date;
	}
	
	public String getRequiredDateYYYYMMDD(int days) {
		
		/*SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sim.format(cal.getTime());
		return reqDate;*/
		
		Date dateobj = new Date();

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateobj);

		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sim.format(cal.getTime());
       return endDate;
	}

}
