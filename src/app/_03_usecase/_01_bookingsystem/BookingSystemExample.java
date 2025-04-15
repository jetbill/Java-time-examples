package app._03_usecase._01_bookingsystem;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class BookingSystemExample {
    public static void main(String[] args) {
        // Caso de uso: Sistema de reservas de hotel

        LocalDate checkIn = LocalDate.of(2025, 7, 15);
        LocalDate checkOut = LocalDate.of(2025, 7, 20);

        // Calcular duración de la estancia
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        System.out.println("Duración de la estancia: " + nights + " noches");

        // Verificar disponibilidad
        boolean isAvailable = checkAvailability(checkIn, checkOut);
        System.out.println("¿Disponible para reserva? " + isAvailable);

        // Calcular precio total (suponiendo diferentes tarifas para fin de semana)
        double totalPrice = calculateTotalPrice(checkIn, checkOut);
        System.out.println("Precio total: " + String.format("%.2f", totalPrice) + " €");

        // Hora de check-in y check-out
        LocalTime standardCheckInTime = LocalTime.of(14, 0);
        LocalTime standardCheckOutTime = LocalTime.of(12, 0);

        // Combinar fecha y hora
        LocalDateTime checkInDateTime = LocalDateTime.of(checkIn, standardCheckInTime);
        LocalDateTime checkOutDateTime = LocalDateTime.of(checkOut, standardCheckOutTime);

        System.out.println("Check-in: " +
                checkInDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("Check-out: " +
                checkOutDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        // Verificar si incluye días festivos
        List<LocalDate> holidays = getHolidays(checkIn.getYear());
        List<LocalDate> holidaysDuringStay = new ArrayList<>();

        for (LocalDate date = checkIn; !date.isAfter(checkOut); date = date.plusDays(1)) {
            if (holidays.contains(date)) {
                holidaysDuringStay.add(date);
            }
        }

        if (!holidaysDuringStay.isEmpty()) {
            System.out.println("La estancia incluye los siguientes días festivos:");
            for (LocalDate holiday : holidaysDuringStay) {
                System.out.println(holiday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        } else {
            System.out.println("La estancia no incluye días festivos.");
        }

        // Verificar si la reserva está dentro de la temporada alta
        boolean isHighSeason = isHighSeason(checkIn, checkOut);
        System.out.println("¿Temporada alta? " + isHighSeason);

        // Calcular fechas de pago (suponiendo un 20% al reservar y el resto 7 días antes del check-in)
        LocalDate bookingDate = LocalDate.now();
        LocalDate depositDueDate = bookingDate.plusDays(2); // 2 días para pagar el depósito
        LocalDate finalPaymentDate = checkIn.minusDays(7);  // 7 días antes del check-in

        System.out.println("Fecha de reserva: " +
                bookingDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Fecha límite para depósito: " +
                depositDueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Fecha límite para pago completo: " +
                finalPaymentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Verifica disponibilidad entre fechas (ejemplo simplificado)
     */
    private static boolean checkAvailability(LocalDate checkIn, LocalDate checkOut) {
        // En una aplicación real, consultaríamos una base de datos
        // Este es solo un ejemplo básico
        return true;
    }

    /**
     * Calcula el precio total considerando diferentes tarifas para días entre semana y fin de semana
     */
    private static double calculateTotalPrice(LocalDate checkIn, LocalDate checkOut) {
        double weekdayRate = 100.0;
        double weekendRate = 150.0;
        double totalPrice = 0.0;

        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                totalPrice += weekendRate;
            } else {
                totalPrice += weekdayRate;
            }
        }

        return totalPrice;
    }

    /**
     * Obtiene una lista simplificada de días festivos
     */
    private static List<LocalDate> getHolidays(int year) {
        // Simplificado para el ejemplo
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(year, 1, 1));   // Año Nuevo
        holidays.add(LocalDate.of(year, 5, 1));   // Día del Trabajo
        holidays.add(LocalDate.of(year, 12, 25)); // Navidad
        return holidays;
    }

    /**
     * Verifica si la estancia está en temporada alta (ejemplo simplificado)
     */
    private static boolean isHighSeason(LocalDate checkIn, LocalDate checkOut) {
        // Ejemplo: Temporada alta es verano (junio-septiembre) y Navidad
        Month checkInMonth = checkIn.getMonth();
        Month checkOutMonth = checkOut.getMonth();

        boolean isSummer = (checkInMonth == Month.JUNE || checkInMonth == Month.JULY ||
                checkInMonth == Month.AUGUST || checkInMonth == Month.SEPTEMBER);

        boolean isChristmas = (checkInMonth == Month.DECEMBER && checkIn.getDayOfMonth() >= 20) ||
                (checkOutMonth == Month.JANUARY && checkOut.getDayOfMonth() <= 6);

        return isSummer || isChristmas;
    }

}
