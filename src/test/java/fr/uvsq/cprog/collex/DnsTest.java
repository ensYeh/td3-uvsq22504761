package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Properties;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DnsTest {
    private Dns dns;
    private Path tempFile;

    @Before
    public void setUp() throws IOException {
        this.tempFile = Files.createTempFile("dns", ".txt");
        Files.write(this.tempFile, List.of("www.uvsq.fr 192.168.0.1", "poste.uvsq.fr 192.168.0.2"));
        Properties properties = new Properties();
        properties.setProperty("db.file", this.tempFile.toString());
        this.dns = new Dns(properties);
    }

    @Test
    public void testGetItemByIp() {
        DnsItem item = dns.getItem(new AdresseIP("192.168.0.1"));
        assertNotNull(item);
        assertEquals("www.uvsq.fr", item.getNomMachine().getNomComplet());
    }

    @Test
    public void testGetItemByNom() {
        DnsItem item = dns.getItem(new NomMachine("poste.uvsq.fr"));
        assertNotNull(item);
        assertEquals("192.168.0.2", item.getAdresseIP().getIp());
    }

    @Test
    public void testGetItems() {
        List<DnsItem> items = dns.getItems("uvsq.fr");
        assertEquals(2, items.size());
        assertEquals("poste.uvsq.fr", items.get(0).getNomMachine().getNomComplet());
        assertEquals("www.uvsq.fr", items.get(1).getNomMachine().getNomComplet());
    }

    @Test
    public void testAddItem() throws IOException {
        dns.addItem(new AdresseIP("192.168.0.3"), new NomMachine("new.uvsq.fr"));
        DnsItem item = dns.getItem(new AdresseIP("192.168.0.3"));
        assertNotNull(item);
        assertEquals("new.uvsq.fr", item.getNomMachine().getNomComplet());
        List<String> lines = Files.readAllLines(this.tempFile);
        assertTrue(lines.contains("new.uvsq.fr 192.168.0.3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemDejaContenu() throws IOException {
        dns.addItem(new AdresseIP("192.168.0.1"), new NomMachine("new.uvsq.fr"));
    }
}
