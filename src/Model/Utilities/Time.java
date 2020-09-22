/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Samuel
 */
public class Time {
    
    private Date date;
    private java.sql.Date dateSql; 
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private Calendar calendar;
    private GregorianCalendar gregorianCalendar;
    private static final SimpleDateFormat defaultDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Time(Date date, java.sql.Date dateSql, LocalDate localDate, LocalTime localTime, Calendar calendar, GregorianCalendar gregorianCalendar) {
        this.date = date;
        this.dateSql = dateSql;
        this.localDate = localDate;
        this.localTime = localTime;
        this.calendar = calendar;
        this.gregorianCalendar = gregorianCalendar;
    }

    public Time() {
        
        this.date = new Date();
        this.dateSql = new java.sql.Date(date.getTime());
        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.now();
        this.gregorianCalendar = new GregorianCalendar();
        
    }
    
    public Time(java.sql.Date sqlDate) {
        
        this.date = new Date(sqlDate.getTime());
        this.dateSql = sqlDate;
        this.localDate = sqlDate.toLocalDate();
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.now();
        this.gregorianCalendar = new GregorianCalendar();
        
    }
    
    public Time(Date date) {
        
        this.date = date;
        this.dateSql = new java.sql.Date(date.getTime());
        this.localDate = l;
        this.localTime = LocalTime.now();
        this.localDateTime = LocalDateTime.now();
        this.gregorianCalendar = new GregorianCalendar();
        
    }

    public String getStringFromDate(Date date){
        
        String formated = defaultDate.format(date);
        
        return formated;
    }
    
    public static java.sql.Date getSQLDateFrom(Date date){
        
        return new java.sql.Date(date.getTime());
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public java.sql.Date getDateSql() {
        return dateSql;
    }

    public void setDateSql(java.sql.Date dateSql) {
        this.dateSql = dateSql;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }

    public void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }
    
    
    
}
