package app._02_operations._01_date;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;

public class DateValidationExamples {
    public static void main(String[] args) {
        // Verificación de fecha válida con try-catch
        try {
            LocalDate invalidDate = LocalDate.of(2023, 2, 30);  // Febrero no tiene 30 días
            System.out.println("Fecha válida: " + invalidDate);  // Este código nunca se ejecutará
        } catch (DateTimeException e) {
            System.out.println("Error al crear fecha: " + e.getMessage());
            // Output: Error al crear fecha: Invalid date 'February 30'
        }

        // Verificar validez al parsear fechas
        String dateStr = "31/02/2023";  // Febrero no tiene 31 días
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate parsedDate = LocalDate.parse(dateStr, formatter);
            System.out.println("Fecha parseada: " + parsedDate);  // Este código nunca se ejecutará
        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear fecha: " + e.getMessage());
            // Output: Error al parsear fecha: Text '31/02/2023' could not be parsed: Invalid date 'February 31'
        }

        // Método isValidDate (implementación personalizada para verificar una fecha)
        System.out.println("¿Es válida 2023-02-28? " + isValidDate(2023, 2, 28));  // Output: true
        System.out.println("¿Es válida 2023-02-29? " + isValidDate(2023, 2, 29));  // Output: false (2023 no es bisiesto)
        System.out.println("¿Es válida 2024-02-29? " + isValidDate(2024, 2, 29));  // Output: true (2024 es bisiesto)

        // Validación personalizada con manejo de errores
        validateAndParseDate("15/03/2025", formatter);  // Output: Fecha válida: 2025-03-15
        validateAndParseDate("30/02/2025", formatter);  // Output: Fecha inválida: Text '30/02/2025' could not be parsed...
        validateAndParseDate("31/04/2025", formatter);  // Output: Fecha inválida: Text '31/04/2025' could not be parsed...
    }

    /**
     * Verifica si una fecha es válida sin lanzar excepciones
     * @param year Año
     * @param month Mes (1-12)
     * @param day Día del mes
     * @return true si la fecha es válida, false en caso contrario
     */
    public static boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Valida y parsea una fecha con manejo de errores
     * @param dateStr String con la fecha
     * @param formatter Formateador a utilizar
     * @return LocalDate o null si la fecha es inválida
     */
    public static LocalDate validateAndParseDate(String dateStr, DateTimeFormatter formatter) {
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            System.out.println("Fecha válida: " + date);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida: " + e.getMessage());
            return null;
        }
    }
}



