package app._01_creation._5_offsetdatetime;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeExamples {
    public static void main(String[] args) {
        OffsetDateTime nowWithOffset  = OffsetDateTime.now();
        System.out.println("Fecha y hora con offset actual: " + nowWithOffset);

        // Crear con offset específico
        OffsetDateTime dateTimeWithOffset = OffsetDateTime.of(2025, 4, 9,
                19, 43, 45, 0, ZoneOffset.ofHours(2));
        System.out.println("Fecha y hora con offset +02:00: " + dateTimeWithOffset);

        // Parsear desde String
        OffsetDateTime parsedODT = OffsetDateTime.parse("2023-09-01T15:30:00+02:00");
        System.out.println("OffsetDateTime parseado: " + parsedODT);

        // Formato personalizado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss XXXXX");
        System.out.println("Fecha y hora formateadas con offset: " + nowWithOffset.format(formatter));

        // Crear con offset en minutos
        OffsetDateTime oddOffset = OffsetDateTime.of(2023, 12, 31, 23, 59, 59, 0, ZoneOffset.ofHoursMinutes(5, 30)); // Offset como India (UTC+5:30)
        System.out.println("Fecha y hora con offset de India: " + oddOffset);
    }


}

