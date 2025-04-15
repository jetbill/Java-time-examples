package app._02_operations._08_utc;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class UTCExamples    {
    public static void main(String[] args) {
        // Obtener hora actual en UTC
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("Hora actual en UTC: " + utcNow);

        // Obtener hora actual en la zona del sistema
        ZonedDateTime localNow = ZonedDateTime.now();
        System.out.println("Hora local: " + localNow);

        // Convertir una hora local a UTC
        ZonedDateTime localToUTC = localNow.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Hora local convertida a UTC: " + localToUTC);

        // Convertir UTC a zona local
        ZonedDateTime utcToLocal = utcNow.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("UTC convertido a hora local: " + utcToLocal);

        // Trabajar con ISO 8601 (formato estándar para intercambio de fechas)
        String iso8601 = OffsetDateTime.now(ZoneOffset.UTC).toString();
        System.out.println("Fecha actual en formato ISO 8601: " + iso8601);

        // Parsear una fecha ISO 8601
        OffsetDateTime parsedIso = OffsetDateTime.parse("2023-12-31T23:59:59+00:00");
        System.out.println("ISO 8601 parseada: " + parsedIso);

        // Ejemplo práctico: guardar timestamps en UTC en una base de datos
        // y mostrarlos en la zona horaria del usuario
        OffsetDateTime storageTimestamp = OffsetDateTime.now(ZoneOffset.UTC);
        System.out.println("Timestamp guardado en BD (UTC): " +
                storageTimestamp.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        // Simular recuperación y mostrar al usuario en su zona
        ZonedDateTime userView = storageTimestamp.atZoneSameInstant(ZoneId.systemDefault());
        System.out.println("Vista para el usuario (zona local): " +
                userView.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));

        // Convertir entre múltiples zonas (útil para aplicaciones globales)
        ZonedDateTime tokioTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime nyTime = tokioTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime sydneyTime = tokioTime.withZoneSameInstant(ZoneId.of("Australia/Sydney"));

        System.out.println("\nMisma hora en diferentes zonas:");
        System.out.println("Tokio:   " + tokioTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
        System.out.println("NY:      " + nyTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
        System.out.println("Sydney:  " + sydneyTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
    }

}
