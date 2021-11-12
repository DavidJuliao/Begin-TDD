
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CamelCaseConvertertest {
    private CamelCaseConverter camelCaseConverter;
    @Before
    public void setup(){
        camelCaseConverter = new CamelCaseConverter();
    }


    @Test
    public void aplicarCamelCase() throws Exception{
        assertEquals("Gates",camelCaseConverter.converter("gates"));
    }

    @Test
    public void aplicarCamelCaseEmPalavraComMisturaDeMaiusculosEMinusculos() throws Exception{
        assertEquals("Jobs", camelCaseConverter.converter("jOBs"));
    }
}
