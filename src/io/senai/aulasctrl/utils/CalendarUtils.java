package io.senai.aulasctrl.utils;

import java.util.Calendar;
import java.util.Date;

public final class CalendarUtils {
	
	private CalendarUtils() {}
	
	public static Date add(int amount, int field) {
		return add(new Date(), amount, field);
	}
	
	public static Date add(Date date, int amount, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);;
		c.add(field, amount);
		
		return c.getTime();
	}
	
	public static Date resetHourTo0() {
		return resetHourTo0(new Date());
	}
	
	public static Date resetHourTo0(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        
        return c.getTime();
	}
}
