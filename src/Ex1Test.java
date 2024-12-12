import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {
        @Test
        void computeNumberTest() {
            String s2 = "1011b2";
            int v = Ex1.number2Int(s2);
            assertEquals(v,11);
            String s10 = "1011bA";
            v = Ex1.number2Int(s10);
            assertEquals(1011,v);// למחוק
            s2 = Ex1.int2Number(v,2);
            assertEquals("1111110011",s2); // למחוק
            int v2 = Ex1.number2Int(s2);
            assertEquals("1111110011",v2); // למחוק
            //assertEquals(v,v2);
           // assertTrue(Ex1.equals(s10,s2));
        }

        @Test
        void isBasisNumberTest() {
            String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
            for(int i=0;i<good.length;i=i+1) {
                boolean ok = Ex1.isNumber(good[i]);
                assertTrue(ok);
            }
            String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
            for(int i=0;i<not_good.length;i=i+1) {
                boolean not_ok = Ex1.isNumber(not_good[i]);
                assertFalse(not_ok);
            }
        }
        @Test
        void int2NumberTest() {
            assertEquals("5A", Ex1.int2Number(90, 16));
            assertEquals("100", Ex1.int2Number(256, 16));
            assertEquals("", Ex1.int2Number(2, 18)); // Invalid base, should return ""
            assertEquals("", Ex1.int2Number(-5, 10)); // Invalid number, should return ""
            assertEquals("0", Ex1.int2Number(0, 5));
            assertEquals("0", Ex1.int2Number(0, 10));
            assertEquals("1000", Ex1.int2Number(8, 2));
            assertEquals("F", Ex1.int2Number(15, 16));
            assertEquals("A", Ex1.int2Number(10, 16));
        }
        @Test
        void maxIndexTest() {

            String[] arr2 = {"5b7", "6bG", "14b4" ,"5b9" ,"4b8" };
            assertEquals(1, Ex1.maxIndex(arr2));

            String[] arr3 = {"2bA", "4bA", "7bA"};
            //assertEquals("2", Ex1.maxIndex(arr4));

            String[] arr4 = {"2bA", "2bA", "2bA"};
           // assertEquals("0", Ex1.maxIndex(arr5));


        }

    @Test
    void converted() {
        assertEquals(123, Ex1.converted("123")); // should return 123
        assertEquals(0, Ex1.converted("0"));
        assertEquals(10, Ex1.converted("A"));
        assertEquals(111, Ex1.converted("AB"));
        assertEquals(-1, Ex1.converted("H"));
        assertEquals(-1, Ex1.converted("A1"));
        assertEquals(-1, Ex1.converted(""));

    }

    @Test
    void split() {
        String[] result1 = Ex1.split("1011b2");
        assertNotNull(result1); // Should not return null
        assertEquals(2, result1.length); // two parts
        assertEquals("1011", result1[0]); // number
        assertEquals("2", result1[1]); // base

        String[] result2 = Ex1.split("3 b4");  // whitespace
        assertNull(result2);

        String[] result3 = Ex1.split("01b2");
        assertNotNull(result3);
        assertEquals(2, result3.length);
        assertEquals("01", result3[0]);
        assertEquals("2", result3[1]);

        String[] result4 = Ex1.split("12345b"); // no base side
        assertNull(result4);

        String[] result5 = Ex1.split("1111110011");
        assertNotNull(result5);
        assertEquals(2, result5.length);
        assertEquals("1111110011", result5[0]);
    }

    @Test
    void number2Int() {
        assertEquals(11, Ex1.number2Int("1011b2")); // Binary number
        assertEquals(-1, Ex1.number2Int("FFb16")); //  wrong format 16 is prohibited - need G
        assertEquals(-1, Ex1.number2Int("123b10")); // base 10
        assertEquals(0, Ex1.number2Int("0b2")); //
        assertEquals(-1, Ex1.number2Int("6B8")); // B
        assertEquals(-1, Ex1.number2Int("12b1")); // Invalid base
        assertEquals(-1, Ex1.number2Int("")); // Empty
        assertEquals(-1, Ex1.number2Int("null")); // Null
        assertEquals(-1, Ex1.number2Int("b8")); // Missing number part
        assertEquals(-1, Ex1.number2Int("12b")); // Missing base part
        assertEquals(-1, Ex1.number2Int("2 3b4")); // Whitespace in the number part
        assertEquals(-1, Ex1.number2Int("1b20")); // Base greater than 16
        assertEquals(14, Ex1.number2Int("12bC")); // Invalid base (non-numeric base)
        assertEquals(-1, Ex1.number2Int("11b0")); // Base less than 2
        assertEquals(-1, Ex1.number2Int("FFb10"));
        assertEquals(-1, Ex1.number2Int("ZZb14")); // base 14 invalid
        assertEquals(-1, Ex1.number2Int("22b2b2")); // multiple b
        assertEquals(-1, Ex1.number2Int("12b A")); // Whitespace after 'b'
    }

    @Test
    void isNumber() {
        assertFalse(Ex1.isNumber("b2"));
        assertFalse(Ex1.isNumber("0b1"));
        assertFalse(Ex1.isNumber("123b"));
        assertFalse(Ex1.isNumber("1234b11"));
        assertFalse(Ex1.isNumber("3b3"));
        assertFalse(Ex1.isNumber("3 b4"));
        assertFalse(Ex1.isNumber("-3b5"));
        assertFalse(Ex1.isNumber("GbG"));
        assertFalse(Ex1.isNumber(""));
        assertFalse(Ex1.isNumber("null"));
        assertFalse(Ex1.isNumber("123b2"));   // Base 2, digit above 1
        assertFalse(Ex1.isNumber("5b10"));   // should be base A
        assertFalse(Ex1.isNumber("123b16"));
        assertFalse(Ex1.isNumber("123b17")); // Invalid base 17
        assertTrue(Ex1.isNumber("EFbG"));
        assertTrue(Ex1.isNumber("135"));
        assertTrue(Ex1.isNumber("135bA"));
    }

    @Test
    void testEquals() {
          assertTrue(Ex1.equals("1000b2", "8bA"));
          assertFalse(Ex1.equals("101b3", "4b9"));
          assertTrue(Ex1.equals("100bB", "100bB"));
          assertFalse(Ex1.equals("100bC", "100bH"));
          assertFalse(Ex1.equals("100b", "100"));
    }
 }
