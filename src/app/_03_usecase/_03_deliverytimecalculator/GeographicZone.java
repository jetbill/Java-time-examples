package app._03_usecase._03_deliverytimecalculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeographicZone {
    private final String name;
    private LocalTime cutoffTime;
    private int standardDeliveryDays;
    private final Set<LocalDate> holidays;

    public GeographicZone(String name) {
        this.name = name;
        this.cutoffTime = LocalTime.of(13, 0); // Por defecto 13:00
        this.standardDeliveryDays = 2; // Por defecto 2 días
        this.holidays = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public LocalTime getCutoffTime() {
        return cutoffTime;
    }

    public void setCutoffTime(LocalTime cutoffTime) {
        this.cutoffTime = cutoffTime;
    }

    public int getStandardDeliveryDays() {
        return standardDeliveryDays;
    }

    public void setStandardDeliveryDays(int standardDeliveryDays) {
        this.standardDeliveryDays = standardDeliveryDays;
    }

    public Set<LocalDate> getHolidays() {
        return new  HashSet<>(holidays);
    }
    public void setHolidays(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.holidays.add(date);
    }

}
