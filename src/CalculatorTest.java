import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

class CalculatorTest {
    Calculator calculator = new Calculator();
    XMLParser parser = new XMLParser();
    HashMap<String, BigDecimal> currencies = new HashMap();
    @Test
    public void analyze_incorrectCurrency(){
        //given
        parser.parseXML(currencies);
        BigDecimal sum = BigDecimal.ZERO;
        String currency = "xx";

        //when
        Optional<BigDecimal> result = Optional.ofNullable(currencies.get(currency));

        //then
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void analyze_allParamsOK_resultTrue(){
        //given
        parser.parseXML(currencies);
        BigDecimal sum = BigDecimal.ONE;
        String currency = "USD";
        Optional<BigDecimal> result = Optional.ofNullable(currencies.get(currency));

        //when
        Assertions.assertEquals(new BigDecimal("1.0991"), result.get());
    }

    @Test
    public void analyze_invalidResult_resultFalse(){
        //given
        parser.parseXML(currencies);
        BigDecimal sum = BigDecimal.ONE;
        String currency = "JPY";
        Optional<BigDecimal> result = Optional.ofNullable(currencies.get(currency));

        //when
        Assertions.assertNotEquals(new BigDecimal("11.0"), result.get());
    }
}