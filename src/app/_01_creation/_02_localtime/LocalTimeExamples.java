package app._01_creation._02_localtime;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeExamples {
    public static void main(String[] args) {
        // Obtener la hora actual
        LocalTime now = LocalTime.now();
        System.out.println("Now: " + now);

        // Crear una hora específica (hora, minutos, segundos)
        LocalTime meetingTime = LocalTime.of(16,50,1);
        System.out.println("Meeting Time: " + meetingTime);

        // Parsear una hora desde String
        LocalTime parsedTime = LocalTime.parse("16:50:01");
        System.out.println("Hora parseada: " + parsedTime);


        // Formato personalizado
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime customTime = LocalTime.parse("14:30", timeFormatter);
        System.out.println("Hora con formato personalizado: "+customTime);

        // Formatear una hora
        String formattedTime = now.format(timeFormatter);
        System.out.println("Hora formateada: " + formattedTime);




    }
}
