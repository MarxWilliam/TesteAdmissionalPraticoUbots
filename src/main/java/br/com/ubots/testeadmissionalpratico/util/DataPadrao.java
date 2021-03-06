package br.com.ubots.testeadmissionalpratico.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DataPadrao {
	private static final ZoneId id_brasil = ZoneId.of( "America/Sao_Paulo" );
	
	public static ZonedDateTime parse(String data) {
		LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		ZonedDateTime zdt_Brasil = ZonedDateTime.of(dt, LocalTime.of(0, 0), id_brasil);
		
		return zdt_Brasil;
	}
}
