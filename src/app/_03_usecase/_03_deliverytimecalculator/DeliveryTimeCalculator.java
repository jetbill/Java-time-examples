package app._03_usecase._03_deliverytimecalculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeliveryTimeCalculator {

    private final GeographicZone geographicZone;

    public DeliveryTimeCalculator(GeographicZone geographicZone) {
        this.geographicZone = geographicZone;
    }

    public LocalDate calculateDeliveryDate(LocalDateTime orderDateTime) {
        LocalDate processingDate = orderDateTime.toLocalDate();
        LocalTime orderTime = orderDateTime.toLocalTime();

        // Si la hora del pedido es posterior a la hora de corte,
        // el procesamiento empieza al siguiente día hábil
        if (orderTime.isAfter(geographicZone.getCutoffTime())) {
            processingDate = processingDate.plusDays(1);
        }

        // Aseguramos que el día de inicio de procesamiento sea un día hábil
        processingDate = getNextWorkingDay(processingDate);

        // Calculamos la fecha de entrega sumando los días laborables estándar
        LocalDate deliveryDate = processingDate;
        int workingDaysToAdd = geographicZone.getStandardDeliveryDays();

        while (workingDaysToAdd > 0) {
            deliveryDate = deliveryDate.plusDays(1);
            if (isWorkingDay(deliveryDate)) {
                workingDaysToAdd--;
            }
        }

        return deliveryDate;
    }


    private LocalDate getNextWorkingDay(LocalDate date) {
        LocalDate nextDay = date;
        while (!isWorkingDay(nextDay)) {
            nextDay = nextDay.plusDays(1);
        }
        return nextDay;
    }
    private boolean isWorkingDay(LocalDate date) {
        // Verificar si es fin de semana (sábado o domingo)
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }

        // Verificar si es un día festivo configurado
        return !geographicZone.getHolidays().contains(date);
    }

}
