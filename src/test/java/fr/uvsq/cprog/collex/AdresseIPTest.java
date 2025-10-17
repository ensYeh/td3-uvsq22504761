package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class AdresseIPTest {
    @Test
    public void testIpValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.getIp());
    }

    @Test(expected = IllegalArgumentException.class) // pas d'assertThrows car on doit utiliser JUnit 4
    public void testFormatInvalide() {
        new AdresseIP("256.168.0.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIpCourt() {
        new AdresseIP("192.168.0");
    }

    @Test
    public void testEquals() {
        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        AdresseIP ip2 = new AdresseIP("192.168.0.1");
        AdresseIP ip3 = new AdresseIP("192.168.0.2");
        assertEquals(ip1, ip2);
        assertNotEquals(ip1, ip3);
    }
}
