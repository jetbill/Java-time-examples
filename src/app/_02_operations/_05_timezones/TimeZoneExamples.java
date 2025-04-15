package app._02_operations._05_timezones;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;



public class TimeZoneExamples {
    public static void main(String[] args) {


            // Listar todas las zonas horarias disponibles
            Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
            System.out.println("Número de zonas horarias disponibles: " + availableZoneIds.size());

            // Algunos ejemplos de zonas horarias importantes
            System.out.println("Algunas zonas horarias importantes:");
            String[] importantZones = {
                    "UTC", "Europe/Madrid", "America/New_York", "Asia/Tokyo",
                    "Australia/Sydney", "America/Los_Angeles", "Europe/London"
            };

            for (String zone : importantZones) {
                ZoneId zoneId = ZoneId.of(zone);
                ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
                System.out.println(zone + ": " + zonedDateTime);
            }

            // Convertir entre zonas horarias
            ZonedDateTime nowInMadrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
            System.out.println("Ahora en Madrid: " + nowInMadrid);

            ZonedDateTime sameInstantInTokyo = nowInMadrid.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
            System.out.println("Mismo instante en Tokio: " + sameInstantInTokyo);

            // Diferencia horaria entre dos zonas
            ZoneOffset madridOffset = nowInMadrid.getOffset();
            ZoneOffset tokyoOffset = sameInstantInTokyo.getOffset();

            Duration timeDifference = Duration.between(
                    LocalDateTime.now().atOffset(madridOffset),
                    LocalDateTime.now().atOffset(tokyoOffset)
            );

            System.out.println("Diferencia horaria entre Madrid y Tokio: " +
                    timeDifference.toHours() + " horas");

            // Trabajar con ZoneOffset
            ZoneOffset offset = ZoneOffset.of("+02:00");
            OffsetDateTime offsetDateTime = OffsetDateTime.now(offset);
            System.out.println("Hora con offset +02:00: " + offsetDateTime);

            // Convertir LocalDateTime a diferentes zonas horarias
            LocalDateTime localNow = LocalDateTime.now();

            ZonedDateTime localInNewYork = localNow.atZone(ZoneId.of("America/New_York"));
            ZonedDateTime localInLondon = localNow.atZone(ZoneId.of("Europe/London"));

            System.out.println("Local como si estuviera en Nueva York: " + localInNewYork);
            System.out.println("Local como si estuviera en Londres: " + localInLondon);

            // Planificar una reunión global con participantes de diferentes zonas
            ZonedDateTime meetingInMadrid = ZonedDateTime.of(
                    LocalDate.now().plusDays(7),
                    LocalTime.of(10, 0),
                    ZoneId.of("Europe/Madrid")
            );

            System.out.println("Reunión en Madrid: " +
                    meetingInMadrid.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm z")));

            System.out.println("Misma reunión en otras zonas:");
            for (String zone : new String[]{"America/New_York", "Asia/Tokyo", "Australia/Sydney"}) {
                ZonedDateTime meetingInZone = meetingInMadrid.withZoneSameInstant(ZoneId.of(zone));
                System.out.println(zone + ": " +
                        meetingInZone.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm z")));
            }
        }

    }

