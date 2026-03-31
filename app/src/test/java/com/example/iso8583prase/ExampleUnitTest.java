package com.example.iso8583prase;

import org.jpos.iso.ISOMsg;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void testParser() {
        JPosParser parser = new JPosParser();
        // Example Hex Message (TPDU + MTI + Bitmap + Data)
        // This is a dummy message for testing purposes
        String testHex = "60000000000800202000000000000004123456"; 
        
        try {
            ISOMsg isoMsg = parser.parse(testHex);
            assertNotNull(isoMsg);
            System.out.println("MTI: " + isoMsg.getMTI());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Parsing failed: " + e.getMessage());
        }
    }
}
