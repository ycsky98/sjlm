package cn.sjlm.sjlmapi.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author yangcong
 * LocalDate,LocalDateTime,Date类型互转
 * create on 2019/10/9
 * */
public class LocalDateUtil {
	
	/**
	 * LocalDate转Date
	 * @param localDate
	 * */
	public static Date localDate2Date(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}
	
	/**
	 * Date转LocalDate
	 * @param date
	 * */
	public static LocalDate date2LocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
     * Date转换为LocalDateTime
     * @param date
     */
	public static LocalDateTime date2LocalDateTime(Date date) {
		if (date == null) {
			return null;
		}
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}
	
	/**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
       return date;
    }
}
