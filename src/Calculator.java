import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;


public class Calculator {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("CalculatorLogger");
        XMLParser parser = new XMLParser();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, BigDecimal> currencies = new HashMap();
        parser.parseXML(currencies);

        BigDecimal sum = BigDecimal.ZERO;
        String currency;
        Optional<BigDecimal> result;

        log.info("Wprowadz kwote w Euro, aby zakonczyc dzialanie programu wprowadz 0");
        sum = scanner.nextBigDecimal();
        while(!sum.equals(BigDecimal.ZERO)){
            log.info("Wprowadz symbol waluty: ");
            currency = scanner.next();
            result = Optional.ofNullable(currencies.get(currency));
            if(result.isPresent())
                log.info(sum + "EUR = " + sum.multiply(result.get()) + currency);
            else {
                log.info("Nieznany symbol waluty, podaj jeden z ponizszych");
                for (String key : currencies.keySet()) {
                    log.info(key);
                }
            }
            log.info("Wprowadz kwote w Euro, aby zakonczyc dzialanie programu wprowadz 0");
            sum = scanner.nextBigDecimal();
        }
    }
}
