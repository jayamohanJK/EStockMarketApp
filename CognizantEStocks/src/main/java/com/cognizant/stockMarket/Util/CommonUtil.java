package com.cognizant.stockMarket.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

	public static LocalDateTime getDate()
	{
		LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        LocalDateTime locldate= LocalDateTime.parse(now.format(format),
				DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return locldate;
          
	}
}
