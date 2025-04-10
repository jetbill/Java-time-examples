package app.creation.localdatetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExamples {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Fecha y hora actuales: " + now);

        // Crear una fecha y hora específica
        LocalDateTime eventDateTime = LocalDateTime.of(2025, 4, 9, 19, 1, 59);
        System.out.println("Fecha y hora del evento: " + eventDateTime);

        // Parsear fecha y hora desde String
        LocalDateTime parsedDateTime = LocalDateTime.parse("2025-04-09T19:30:00");
        System.out.println("Fecha y hora parseadas: " + parsedDateTime);

        // Formato personalizado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime customDateTime = LocalDateTime.parse("15/03/2025 14:30:00", formatter);
        System.out.println("Fecha y hora con formato personalizado: " + customDateTime);


        // Formatear fecha y hora
        String formattedDateTime = now.format(formatter);
        System.out.println("Fecha y hora formateadas: " + formattedDateTime);






    }
}
