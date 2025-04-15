package app._01_creation._04_zonedatetime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class ZonedDateTimeExamples {
    public static void main(String[] args) {

        // Obtener fecha y hora actuales con zona horaria del sistema
        ZonedDateTime nowLocal = ZonedDateTime.now();
        System.out.println("Fecha y hora locales: " + nowLocal);

        // Obtener fecha y hora actuales en una zona horaria específica
        ZonedDateTime nowTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Fecha y hora en Tokio: " + nowTokyo);

        // Crear una fecha y hora específica con zona horaria
        ZonedDateTime eventInNY = ZonedDateTime.of(2023, 12, 31, 23, 59,
                59, 0, ZoneId.of("America/New_York"));
        System.out.println("Evento en Nueva York: " + eventInNY);

        // Parsear ZonedDateTime desde String
        ZonedDateTime parsedZDT = ZonedDateTime.parse("2023-09-01T15:30:00+02:00[Europe/Madrid]");
        System.out.println("ZonedDateTime parseado: " + parsedZDT);

        // Formato personalizado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");
        System.out.println("Fecha y hora formateadas con zona: " + nowLocal.format(formatter));

        // Listar zonas horarias disponibles
        System.out.println("Zonas horarias disponibles: ");
        ZoneId.getAvailableZoneIds().stream()
                .sorted()
                .limit(5)  // Limitar salida a 5 zonas por brevedad
                .forEach(System.out::println);







    }
}
