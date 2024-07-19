package week5.ATM;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class CustomerConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        String[] parts = ((String) source).split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Expected format: name,password,balance");
        }
        String name = parts[0];
        String password = parts[1];
        double balance = Double.parseDouble(parts[2]);
        return new Customer(name, password, balance);
    }
}
