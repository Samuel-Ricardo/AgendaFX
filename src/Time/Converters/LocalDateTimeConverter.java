/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Time.Converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Samuel
 */
public class LocalDateTimeConverter {
    
    private final DateTimeFormatter COMPLET_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    private final DateTimeFormatter SHORT_FORMAT = DateTimeFormatter.ofPattern("dd/MM hh:mm");
    
    public LocalDateTime toLocalDateTime(String string){
        
        try {
            
            String date = string.replace(" ", "");
            
          return  LocalDateTime.parse(string,DEFAULT_FORMAT);
            
        } catch (Exception e) {
            System.out.println("Erro: "+e);
            return null;
        }
       
    }
    
    public LocalDateTime fromDate(Date date) {

        LocalDateTime local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return local;
    }
    
    // String
    
    public String defaultFormat(LocalDateTime localDateTime) {
       
        return DEFAULT_FORMAT.format(localDateTime);
    }

    public String shortFormat(LocalDateTime localDateTime) {
      
        return SHORT_FORMAT.format(localDateTime);
    }
}
