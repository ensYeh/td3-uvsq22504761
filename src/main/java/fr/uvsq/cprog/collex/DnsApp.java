package fr.uvsq.cprog.collex;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

import fr.uvsq.cprog.collex.commands.QuitterCommande;

public class DnsApp {
    private DnsTUI tui;

    public DnsApp() throws IOException {
        Properties properties = new Properties();
        try (InputStream i = getClass().getResourceAsStream("/dns.properties")) {
            if (i != null) properties.load(i);
        }
        Dns dns = new Dns(properties);
        this.tui = new DnsTUI(dns);
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                Commande cmd = this.tui.nextCommande(input);
                if (cmd == null) {
                    tui.affiche("Commande invalide");
                } else {
                    String result = cmd.execute();
                    tui.affiche(result);
                    if (cmd instanceof QuitterCommande) break;
                }
                System.out.print("> ");
            }
        }
    }

    public static void main(String[] args) {
        try {
            new DnsApp().run();
        } catch (IOException e) {
            System.err.println("Erreur d'initialisation : " + e.getMessage());
        }
    }
}
