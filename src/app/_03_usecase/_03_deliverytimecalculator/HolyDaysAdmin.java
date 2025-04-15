package app._03_usecase._03_deliverytimecalculator;

import java.time.LocalDate;

public class HolyDaysAdmin {
    private final GeographicZone geographicZone;

    public HolyDaysAdmin(GeographicZone geographicZone) {
        this.geographicZone = geographicZone;
    }

    public boolean createHolyDays(LocalDate date) {
        // Validar que la fecha no sea nula
        if (date == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }

        // Validar que la fecha no sea anterior a la fecha actual
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden agregar días festivos en fechas pasadas.");
        }

        // Validar que la lista de días festivos no sea nula
        if (geographicZone.getHolidays() == null) {
            throw new IllegalStateException("La lista de días festivos no está inicializada.");
        }

        // Verificar si la fecha ya existe en la lista
        if (!geographicZone.getHolidays().contains(date)) {
            geographicZone.setHolidays(date);
            return true;
        }

        return false;
    }

    public void removeHoliday(LocalDate date) {
        validateDateNotNull(date);
        validateHolidaysListInitialized();
        validateHolidayExists(date);

        // Eliminar la fecha de la lista
        geographicZone.getHolidays().remove(date);
    }

    // Validación: La fecha no puede ser nula
    private void validateDateNotNull(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
    }

    // Validación: La lista de días festivos debe estar inicializada
    private void validateHolidaysListInitialized() {
        if (geographicZone.getHolidays() == null) {
            throw new IllegalStateException("La lista de días festivos no está inicializada.");
        }
    }

    // Validación: La fecha debe existir en la lista de días festivos
    private void validateHolidayExists(LocalDate date) {
        if (!geographicZone.getHolidays().contains(date)) {
            throw new IllegalArgumentException("La fecha proporcionada no está registrada como día festivo.");
        }
    }
}



