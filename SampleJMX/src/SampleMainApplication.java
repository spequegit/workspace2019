import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class SampleMainApplication {

    public static void main(String[] args) throws Exception {

        Calculator calculator = new Calculator();
        registerWithJmxAgent(calculator);
        startConsoleApp(calculator);
    }

    private static void startConsoleApp(Calculator calculator) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------");
            String input1 = getUserInput(scanner, "enter first number");
            double d1 = toDouble(input1);

            String input2 = getUserInput(scanner, "enter second number");
            double d2 = toDouble(input2);

            double sum = calculator.add(d1, d2);
            System.out.printf("sum = %s (rounded to %s decimal places)%n",
                    sum, calculator.getDecimalPlacesXXX());

            System.out.println(calculator.getName());
        }
    }

    private static double toDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number, defaulting to 0");
            return 0;
        }
    }

    private static void registerWithJmxAgent(Calculator calculator) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("moj.calculator:type=calculator");
        mbs.registerMBean(calculator, name);
    }

    public static String getUserInput(Scanner scanner, String msg) {
        System.out.print(msg + ">");
        String s = scanner.nextLine();
        if ("exit".equals(s)) {
            System.exit(0);
        }
        return s;
    }
}