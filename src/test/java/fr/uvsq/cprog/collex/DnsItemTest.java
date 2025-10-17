package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class DnsItemTest {
    @Test
    public void testCreationDnsItem() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("www.uvsq.fr");
        DnsItem item = new DnsItem(ip, nom);
        assertEquals(ip, item.getAdresseIP());
        assertEquals(nom, item.getNomMachine());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionNull() {
        new DnsItem(null, new NomMachine("www.uvsq.fr"));
    }

    @Test
    public void testEquals() {
        DnsItem item1 = new DnsItem(new AdresseIP("192.168.0.1"), new NomMachine("www.uvsq.fr"));
        DnsItem item2 = new DnsItem(new AdresseIP("192.168.0.1"), new NomMachine("www.uvsq.fr"));
        DnsItem item3 = new DnsItem(new AdresseIP("192.168.0.2"), new NomMachine("poste.uvsq.fr"));
        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
    }
}
