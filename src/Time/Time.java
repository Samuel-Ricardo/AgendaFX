/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Time;

import Time.Converters.LocalDateConverter;
import Time.Converters.LocalDateTimeConverter;
import Time.Converters.LocalTimeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class Time {
    
   //Types 
    
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    
    //Calendars
    
    private final Calendar CALENDAR = Calendar.getInstance(BRAZIL_NORTHEAST_ZONE, BRAZIL_LOCALE);;
    private final GregorianCalendar GREGORIAN_CALENDAR = new GregorianCalendar(BRAZIL_NORTHEAST_ZONE, BRAZIL_LOCALE);
    
    //Brazil ZTimeone and Locale 
    
    private static final TimeZone BRAZIL_NORTHEAST_ZONE = TimeZone.getTimeZone("Brazil/NorthEast");
    private static final Locale BRAZIL_LOCALE = new Locale("pt", "BR");
    
    //Converters
    
    private LocalDateConverter localDateConverter = new LocalDateConverter();
    private LocalTimeConverter localTimeConverter = new LocalTimeConverter();
    private LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();
    
    //Formats
            
    private static final SimpleDateFormat defaultDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final SimpleDateFormat HourAndMinute = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd/MM");

    //constructors
    
    public Time() {

        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.now();
        
    }

    public Time(java.sql.Date sqlDate) {

        this.localDate = sqlDate.toLocalDate();
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.of(localDate, localTime);
    }
    
    public Time(java.sql.Date sqlDate, java.sql.Time sqlTime) {

        this.localDate = sqlDate.toLocalDate();
        this.localTime = sqlTime.toLocalTime();
        this.localDateTime = LocalDateTime.of(localDate, localTime);
    }

    public Time(Date date) {
        
        this.localDateTime = localDateTimeConverter.fromDate(date);
        this.localDate = localDateConverter.fromDate(date);
        this.localTime = localTimeConverter.fromDate(date);
        
        localDateTime = localDate.atTime(localTime);
    }
    
    public Time(String string) {
           
            this.localDateTime = localDateTimeConverter.toLocalDateTime(string);
            
            this.localDate = localDateConverter.toLocalDate(string);

            this.localTime = localTimeConverter.toLocalTime(string);

    }
    
    public Time(LocalDateTime dateTime) {
        
        this.localDateTime = dateTime;
        this.localDate = localDateTime.toLocalDate();
        this.localTime = localDateTime.toLocalTime();    
    }
    
    public Time(LocalDate localDate) {
    
        this.localDate = localDate;
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.of(localDate, localTime);
    }
    

    public static Time now(){
        
        return new Time();
    }

 

    @Override
    public String toString() {
        
        return localDateTimeConverter.defaultFormat(localDateTime);
    }
    
        
      public String getOnlyDate() {
          
        return localDateConverter.defaultFormat(localDate);
    }
      
      public String getShortDate() {
          
        return localDateConverter.shortFormat(localDate);
    }
      
      public String getOnlyTime() {
          
        return localTimeConverter.shortFormat(localTime);
    }
    
    public Date toDate() {
       
        return localDateConverter.toDate(localDate);
    }
      
      //Getters and Setters
      
    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        
        this.localDate = localDate;
        this.localDateTime = LocalDateTime.of(localDate, localTime);
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        
        this.localTime = localTime;
        this.localDateTime = LocalDateTime.of(localDate, localTime);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        
        this.localDateTime = localDateTime;
        
        this.localTime = localDateTime.toLocalTime();
        this.localDate = localDateTime.toLocalDate();
    }

    public LocalDateConverter getLocalDateConverter() {
        return localDateConverter;
    }

    public void setLocalDateConverter(LocalDateConverter localDateConverter) {
        this.localDateConverter = localDateConverter;
    }

    public LocalTimeConverter getLocalTimeConverter() {
        return localTimeConverter;
    }

    public void setLocalTimeConverter(LocalTimeConverter localTimeConverter) {
        this.localTimeConverter = localTimeConverter;
    }

    public LocalDateTimeConverter getLocalDateTimeConverter() {
        return localDateTimeConverter;
    }

    public void setLocalDateTimeConverter(LocalDateTimeConverter localDateTimeConverter) {
        this.localDateTimeConverter = localDateTimeConverter;
    }

    public Calendar getCALENDAR() {
        return CALENDAR;
    }

    public GregorianCalendar getGREGORIAN_CALENDAR() {
        return GREGORIAN_CALENDAR;
    }

    public static TimeZone getBRAZIL_NORTHEAST_ZONE() {
        return BRAZIL_NORTHEAST_ZONE;
    }

    public static Locale getBRAZIL_LOCALE() {
        return BRAZIL_LOCALE;
    }

    public static SimpleDateFormat getDefaultDate() {
        return defaultDate;
    }

    public static SimpleDateFormat getHourAndMinute() {
        return HourAndMinute;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public static SimpleDateFormat getShortDateFormat() {
        return shortDateFormat;
    } 

    public java.sql.Date toSQLDate() {
    
        return localDateConverter.toSQLDate(localDate);
    }
    
    public java.sql.Time toSQLTime() {
    
        return localTimeConverter.toSQLTime(localTime);
    }
}
