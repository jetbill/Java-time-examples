package app._02_operations._03_datetime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class DateTimeComparisonExamples {

    public static void main(String[] args) {
        // Comparar fechas
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);

        System.out.println("¿Hoy es antes que mañana? " + today.isBefore(tomorrow));  // Output: true
        System.out.println("¿Hoy es después que ayer? " + today.isAfter(yesterday));  // Output: true
        System.out.println("¿Hoy es igual a hoy? " + today.isEqual(LocalDate.now()));  // Output: true

        // Comparar horas
        LocalTime now = LocalTime.now();
        LocalTime later = now.plusHours(1);
        LocalTime earlier = now.minusHours(1);

        System.out.println("¿Ahora es antes que después? " + now.isBefore(later));  // Output: true
        System.out.println("¿Ahora es después que antes? " + now.isAfter(earlier));  // Output: true

        // Comparar fecha y hora combinadas
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime futureDateTime = nowDateTime.plusDays(1).plusHours(2);
        LocalDateTime pastDateTime = nowDateTime.minusDays(1).minusHours(2);

        System.out.println("¿Ahora es antes que el futuro? " + nowDateTime.isBefore(futureDateTime));  // Output: true
        System.out.println("¿Ahora es después que el pasado? " + nowDateTime.isAfter(pastDateTime));  // Output: true

        // Comparadores para ordenamiento y lógica condicional
        int comparisonResult = today.compareTo(tomorrow);  // Devuelve un entero negativo si today < tomorrow
        System.out.println("Resultado de comparación (today vs tomorrow): " + comparisonResult);

        // Ejemplo de uso en condiciones
        if (today.isBefore(tomorrow) && now.isBefore(later)) {
            System.out.println("Tanto la fecha como la hora actuales son anteriores a las futuras");
        }

        // Método equals() para comparación de igualdad completa
        boolean isEqual = today.equals(LocalDate.now());
        System.out.println("¿Fechas iguales con equals()? " + isEqual);  // Puede ser true o false dependiendo de la hora exacta de ejecución
    }

}
