package app._03_usecase._02_remindersystem;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class ReminderSystemExample {
    public static void main(String[] args) {
        // Crear una lista de eventos
        List<Event> events = new ArrayList<>();

        // Añadir algunos eventos
        events.add(new Event(
                "Reunión de equipo",
                LocalDateTime.of(2025, 3, 20, 10, 0),
                Duration.ofHours(1),
                ZoneId.of("Europe/Madrid")));

        events.add(new Event(
                "Conferencia internacional",
                LocalDateTime.of(2025, 4, 15, 9, 0),
                Duration.ofHours(8),
                ZoneId.of("America/New_York")));

        events.add(new Event(
                "Entrega del proyecto",
                LocalDateTime.of(2025, 3, 31, 18, 0),
                Duration.ofMinutes(30),
                ZoneId.systemDefault()));

        // Mostrar todos los eventos
        System.out.println("Eventos programados:");
        for (Event event : events) {
            System.out.println(event);
        }

        // Verificar eventos próximos (en los siguientes 7 días)
        List<Event> upcomingEvents = getUpcomingEvents(events, 7);

        System.out.println("\nEventos próximos (7 días):");
        for (Event event : upcomingEvents) {
            System.out.println(event);

            // Calcular y mostrar tiempo restante
            LocalDateTime now = LocalDateTime.now();
            long daysUntil = ChronoUnit.DAYS.between(now, event.getStartTime());
            long hoursUntil = ChronoUnit.HOURS.between(now, event.getStartTime()) % 24;

            System.out.println("  Tiempo restante: " + daysUntil + " días y " + hoursUntil + " horas");

            // Mostrar hora del evento en diferentes zonas horarias
            ZonedDateTime eventInLocalZone = event.getStartTimeZoned();
            ZonedDateTime eventInNY = eventInLocalZone.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime eventInTokyo = eventInLocalZone.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");

            System.out.println("  Hora local: " + eventInLocalZone.format(formatter));
            System.out.println("  Hora en Nueva York: " + eventInNY.format(formatter));
            System.out.println("  Hora en Tokio: " + eventInTokyo.format(formatter));
        }

        // Calcular tiempo hasta el próximo recordatorio (1 día antes de cada evento)
        for (Event event : upcomingEvents) {
            LocalDateTime reminderTime = event.getStartTime().minusDays(1);

            if (reminderTime.isAfter(LocalDateTime.now())) {
                Duration untilReminder = Duration.between(LocalDateTime.now(), reminderTime);

                long days = untilReminder.toDays();
                long hours = untilReminder.toHoursPart();
                long minutes = untilReminder.toMinutesPart();

                System.out.println("\nRecordatorio para '" + event.getTitle() + "' en: "
                        + days + " días, " + hours + " horas y " + minutes + " minutos");
            }
        }
    }

    /**
     * Filtra eventos que ocurrirán en los próximos días
     */
    private static List<Event> getUpcomingEvents(List<Event> allEvents, int days) {
        List<Event> upcoming = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoff = now.plusDays(days);

        for (Event event : allEvents) {
            if (event.getStartTime().isAfter(now) && event.getStartTime().isBefore(cutoff)) {
                upcoming.add(event);
            }
        }

        return upcoming;
    }

    /**
     * Clase para representar un evento
     */
    static class Event {
        private String title;
        private LocalDateTime startTime;
        private Duration duration;
        private ZoneId zoneId;

        public Event(String title, LocalDateTime startTime, Duration duration, ZoneId zoneId) {
            this.title = title;
            this.startTime = startTime;
            this.duration = duration;
            this.zoneId = zoneId;
        }

        public String getTitle() {
            return title;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public ZonedDateTime getStartTimeZoned() {
            return startTime.atZone(zoneId);
        }

        public LocalDateTime getEndTime() {
            return startTime.plus(duration);
        }

        public Duration getDuration() {
            return duration;
        }

        public ZoneId getZoneId() {
            return zoneId;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return title + " - " + startTime.format(formatter) +
                    " (" + duration.toHours() + "h " + duration.toMinutesPart() + "m) " +
                    "- Zona: " + zoneId;
        }
    }

}
