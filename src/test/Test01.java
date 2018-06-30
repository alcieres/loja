package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test01 {

	@Test
    public void testConcatenate() {
        

        String result = "one" + "two";

        assertEquals("onetwo", result);

    }
}
