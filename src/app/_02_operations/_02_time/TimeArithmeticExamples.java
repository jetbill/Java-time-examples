package app._02_operations._02_time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeArithmeticExamples {
    public static void main(String[] args) {

        LocalTime now = LocalTime.now();
        System.out.println("Hora actual: " + now);

        // Sumar horas, minutos, segundos
        LocalTime later = now.plusHours(3);
        System.out.println("3 horas después: " + later);

        LocalTime laterMinutes = now.plusMinutes(15);
        System.out.println("15 minutos después: " + laterMinutes);

        LocalTime laterSeconds = now.plusSeconds(30);
        System.out.println("30 segundos después: " + laterSeconds);

        // Restar horas, minutos, segundos
        LocalTime earlier = now.minusHours(2);
        System.out.println("2 horas antes: " + earlier);

        LocalTime earlierMinutes = now.minusMinutes(30);
        System.out.println("30 minutos antes: " + earlierMinutes);

        // Modificar componentes específicos
        LocalTime withDifferentHour = now.withHour(10);
        System.out.println("Modificar hora a las 10: " + withDifferentHour);

        LocalTime withDifferentMinute = now.withMinute(30);
        System.out.println("Modificar minutos a 30: " + withDifferentMinute);

        LocalTime withDifferentSecond = now.withSecond(0);
        System.out.println("Modificar segundos a 0: " + withDifferentSecond);

        // Truncar a la unidad especificada
        LocalTime truncatedToHour = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println("Truncado a hora: " + truncatedToHour);

        LocalTime truncatedToMinute = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Truncado a minuto: " + truncatedToMinute);
    }

}
