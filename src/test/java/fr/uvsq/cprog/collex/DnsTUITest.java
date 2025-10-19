package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.collex.commands.*;

import java.io.IOException;
import java.util.Properties;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

public class DnsTUITest {
    private DnsTUI tui;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("db.file", Files.createTempFile("dns", ".txt").toString());
        Dns dns = new Dns(properties);
        this.tui = new DnsTUI(dns);
    }

    // On vérifie ici que l'interface utilisateur fonctionne bien, que la bonne commande est appelée en fonction de l'input

    @Test
    public void testRechercheIpCommande() {
        Commande cmd = tui.nextCommande("www.uvsq.fr");
        assertTrue(cmd instanceof RechercheIpCommande);
    }

    @Test
    public void testRechercheNomCommande() {
        Commande cmd = tui.nextCommande("192.168.0.1");
        assertTrue(cmd instanceof RechercheNomCommande);
    }

    @Test
    public void testListeDomaineCommande() {
        Commande cmd = tui.nextCommande("ls uvsq.fr");
        assertTrue(cmd instanceof ListeDomaineCommande);
    }

    @Test
    public void testAjoutCommande() {
        Commande cmd = tui.nextCommande("add 192.168.0.1 www.uvsq.fr");
        assertTrue(cmd instanceof AjoutCommande);
    }

    @Test
    public void testQuitterCommande() {
        Commande cmd = tui.nextCommande("quit");
        assertTrue(cmd instanceof QuitterCommande);
    }
}
