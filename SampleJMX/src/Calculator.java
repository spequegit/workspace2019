import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator implements CalculatorMBean{
    private int decimalPlaces = 2;
    private String name = "ojojojoj";

    public double add(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);

        BigDecimal sum = bd1.add(bd2);
        return sum.setScale(decimalPlaces, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void setDecimalPlacesXXX(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public int getDecimalPlacesXXX() {
        return decimalPlaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
