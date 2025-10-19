package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.collex.commands.*;

import java.util.regex.Pattern;

public class DnsTUI {
    private Dns dns;
    private static final Pattern IP_PATTERN = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    private static final Pattern NOM_PATTERN = Pattern.compile("^[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public Commande nextCommande(String input) { // pour analyser la commande saisie et appeler la bonne commande
        if (input == null || input.trim().isEmpty()) return null;
        input = input.trim();
        if (input.equalsIgnoreCase("quit")) return new QuitterCommande();
        if (IP_PATTERN.matcher(input).matches()) return new RechercheNomCommande(dns, new AdresseIP(input));
        if (NOM_PATTERN.matcher(input).matches()) return new RechercheIpCommande(dns, new NomMachine(input));
        String[] parts = input.split("\\s+");
        if (parts[0].equals("ls") && parts.length == 2) {
            return new ListeDomaineCommande(dns, parts[1]);
        } else if (parts[0].equals("add") && parts.length == 3) {
            return new AjoutCommande(dns, new AdresseIP(parts[1]), new NomMachine(parts[2]));
        }
        return null;
    }
    
    public void affiche(String result) {
        System.out.println(result);
    }
}
