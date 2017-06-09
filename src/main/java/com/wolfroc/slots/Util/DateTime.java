package com.wolfroc.slots.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTime {
	public static String getDateTimeString(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = simpleDateFormat.format(date);
		return dateTime;
	}
	
	public static Date getDateForZeroClock() throws ParseException{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateTime = simpleDateFormat.format(date);
		return simpleDateFormat.parse(dateTime);
	}
	
	public static Date getDateTime(int hour){
		 
		Calendar c = Calendar.getInstance();
		 
		c.set(Calendar.HOUR_OF_DAY, hour);
		Date date = c.getTime();
		return date;
	}
	
	public static List<Integer> getWeekList(String date,int num){
		List<Integer> weekList = new ArrayList<Integer>();
		int thisWeek = getWeekNumYear(date);
		for (int i = 1; i <= num; i++) {
			int weekNum = thisWeek - i;
			if (weekNum <= 0 ) {
				weekNum = weekNum + 52;
			}
			weekList.add(weekNum);
		}
		return weekList;
	}
	
	public static int getDayOfWeek(){
		Calendar c = Calendar.getInstance();

		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		
		return dayofweek;
	}
	
	public static int getWeekNumYear(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		Calendar cl = Calendar.getInstance();   
		try {
			cl.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		int week = cl.get(Calendar.WEEK_OF_YEAR); 
		
		return week;
	}
	
	public static int getWeekNumCha(int weekNum,int cha){
		int week = weekNum - cha;
		if (week <= 0) {
			week = 52 + week;
		}
		if (week > 52) {
			week = week - 52;
		}
		return week;
	}
	
	public static String getDateTimeStringByD(int cha){
		Date date = new Date();
		long time = date.getTime()/1000+cha;
		Date dt = new Date(time * 1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = simpleDateFormat.format(dt);
		return dateTime;
	}
	
	public static String getDateTimeStringByD(int cha,Date date){
		long time = date.getTime()/1000+cha;
		Date dt = new Date(time * 1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = simpleDateFormat.format(dt);
		return dateTime;
	}
	
	public static String getDateTimeStringByDEnd(int cha,Date date){
		long time = date.getTime()/1000+cha;
		Date dt = new Date(time * 1000);
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(dt);//设置当前日期
		calendar.add(Calendar.MONTH, 1);//月份减一
		calendar.add(Calendar.DATE, -1);//月份减一
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = simpleDateFormat.format(calendar.getTime());
		return dateTime;
	}
	
	public static int getMonthCha(String begin,String end){
		int result;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
			c1.setTime(sdf.parse(begin));
			c2.setTime(sdf.parse(end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int chaYear = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + 12 * chaYear;
        
        return result;
	}
	public static List<String> getMonthList(String begin,String end){
		List<String> montList = new ArrayList<String>();
		int num = getMonthCha(begin,end) + 1;
		for (int i = 0; i < num; i++) {
			montList.add(getMonthStringDate(i,StringToDateByM(begin)));
		}
		return montList;
	}
	
	public static String getMonthStringDate(int cha,Date date){
		long time = date.getTime()/1000;
		Date dt = new Date(time * 1000);
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(dt);//设置当前日期
		calendar.add(Calendar.MONTH, cha);//月份减一
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String dateTime = simpleDateFormat.format(calendar.getTime());
		return dateTime;
	}
	
	public static String getDateTimeStringByDNoYear(int cha,Date date){
		long time = date.getTime()/1000+cha;
		Date dt = new Date(time * 1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
		String dateTime = simpleDateFormat.format(dt);
		return dateTime;
	}
	
	public static boolean contrastTime(String time,String closeTime,String beginTime,String endTime){
		if ((StringToDateByD(time).compareTo(StringToDateByD(beginTime)) == -1 && StringToDateByD(closeTime).compareTo(StringToDateByD(beginTime)) == -1)
				|| (StringToDateByD(time).compareTo(StringToDateByD(endTime)) == 1 && StringToDateByD(closeTime).compareTo(StringToDateByD(endTime)) == 1)) {
			return false;
		}else {
			return true;
		}
	}
	public static int contrastTwoTime(String date,String bidt){
		return StringToDateByD(date).compareTo(StringToDateByD(bidt));
	}
	public static String DateToString(Date dt){
		if (dt == null) {
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = simpleDateFormat.format(dt);
		return dateTime;
	}
	
	public static Date StringToDateByH(String date){
		if (date == null || date.equals("")) {
			return null;
		}
		Date parse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			parse = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	
	public static Date StringToDateByD(String date){
		if (date == null || date.equals("")) {
			return null;
		}
		Date parse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			parse = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	
	public static Date StringToDateByM(String date){
		if (date == null || date.equals("")) {
			return null;
		}
		Date parse = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		try {
			parse = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
	
	public static int getDateCha(String beginDate,String endDate){
		Date begin = StringToDateByD(beginDate);
		Date end =StringToDateByD(endDate);
		long cha = (end.getTime() - begin.getTime())/1000/(60*60*24);
		return (int)cha;
	}
	
	public static int getDateSecondCha(String beginDate,String endDate){
		Date begin = StringToDateByH(beginDate);
		Date end =StringToDateByH(endDate);
		long cha = (end.getTime() - begin.getTime())/1000;
		return (int)cha;
	}
	
	public static int getDateHourCha(String beginDate,String endDate){
		Date begin = StringToDateByH(beginDate);
		Date end =StringToDateByH(endDate);
		long cha = (end.getTime() - begin.getTime())/1000;
		return (int)cha;
	}
	
	public static String getDateListString(String beginDate,String endDate){
		String dateString = beginDate;
		Date date = StringToDateByD(dateString);
		String retrunString = "\"" + getDateTimeStringByDNoYear(0,date) + "\"";
		int cha = getDateCha(beginDate,endDate);
		if (cha < 0) {
			return "";
		}else if (cha == 0) {
			return retrunString;
		}else{
			for (int i = 0; i < cha; i++) {
				//dateString = getDateTimeStringByD(60*60*24*(i+1),date);
				dateString = getDateTimeStringByDNoYear(60*60*24*(i+1),date);
				retrunString = retrunString + ",\"" + dateString + "\"";
			}
		}
		
		return retrunString;
	}
	
	public static String getDateListStringTarget(String beginDate,String endDate){
		String dateString = beginDate;
		Date date = StringToDateByD(dateString);
		String retrunString = "3";
		int cha = getDateCha(beginDate,endDate);
		if (cha < 0) {
			return "";
		}else if (cha == 0) {
			return retrunString;
		}else{
			for (int i = 0; i < cha; i++) {
				//dateString = getDateTimeStringByD(60*60*24*(i+1),date);
				dateString = getDateTimeStringByDNoYear(60*60*24*(i+1),date);
				retrunString = retrunString + ",3";
			}
		}
		
		return retrunString;
	}
	
	public static List<String> getDateList(String begin,String end){
		List<String> list = new ArrayList<String>();
		int cha = getDateCha(begin,end);
		if (cha < 0) {
			return list;
		}else if (cha == 0) {
			list.add(begin);
			return list;
		}else{
			list.add(begin);
			Date date = StringToDateByD(begin);
			for (int i = 0; i < cha; i++) {
				String dateString = getDateTimeStringByD(60*60*24*(i+1),date);
				list.add(dateString);
			}
		}
		
		return list;
	}
	
	public static Date addDay(Date date, int num){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	public static String getStringFromList(List<String> list){
		String str = "";
		for (String ss : list) {
			if (str == "") {
				str = "\"" + ss + "\"";
			}else{
				str = str + ",\"" + ss + "\"";
			}
		}
		return str;
	}
	
	public static String getStringFromList(List<String> list,String fstr){
		String str = "";
		for (String ss : list) {
			if (str == "") {
				str = "\"" + fstr + ss + "\"";
			}else{
				str = str + ",\"" + fstr + ss + "\"";
			}
		}
		return str;
	}
	
	public static List<String> getYearList(String begin,String end){
		List<String> list = new ArrayList<String>();
		int bYear = Integer.valueOf(begin.split("-")[0]);
		int eYear = Integer.valueOf(end.split("-")[0]);
		int num = eYear - bYear;
		for (int i = 0; i < num; i++) {
			list.add(String.valueOf(bYear + i));
		}
		return list;
	}
	public static String getDateWeek(int day,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();

		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + day);
		return sdf.format(c.getTime());
	}
	
	public static String getDateWeek(int day,String format,int addDay) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, addDay);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + day);
		return sdf.format(c.getTime());
	}
	
	public static String getDateDay(String format,int addDay) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, addDay);
		 
		return sdf.format(c.getTime());
	}
	
	public static int getYearNum(String date){
		if (date == "") {
			date = getDateTimeStringByD(0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();

        try {
			c1.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int year = c1.get(Calendar.YEAR);
        
        return year;
	}
	
	public static long getSecond(){
		Date date = new Date();
		long second = date.getTime()/1000;
		
		return second;
	}
	public static long getMilliSecond(){
		Date date = new Date();
		long second = date.getTime();
		
		return second;
	}
	public static long getSecondFromStringDate(String date){
		Date thisDate =  StringToDateByH(date);
		long second = thisDate.getTime()/1000;
		
		return second;
	}
	public static long getMilliSecondFromStringDate(String date){
		Date thisDate =  StringToDateByH(date);
		long second = thisDate.getTime();
		
		return second;
	}
}
