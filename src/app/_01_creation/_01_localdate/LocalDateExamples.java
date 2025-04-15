package app._01_creation._01_localdate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateExamples {

    public static void main(String[] args) {

        // Obtener la fecha actual
        LocalDate today = LocalDate.now();
        System.out.println("Today is: " + today);

        //Crear fecha específica
        LocalDate myBirthday = LocalDate.of(1983, 3, 30);
        System.out.println("My birthday is: " + myBirthday);

        // Parsear una fecha desde String
        LocalDate parsedDate = LocalDate.parse("2025-04-09");
        System.out.println("Parsed date: " + parsedDate);

        // Usar DateTimeFormatter para formatos personalizados
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate customDate = LocalDate.parse("09/04/2025", formatter);
        System.out.println("Formated date: " + customDate);

        // Formatear una fecha
        LocalDate actualDate = LocalDate.now();
        String formatDate = actualDate.format(formatter);
       System.out.println("Formatted date: " + formatDate);



    }
}
