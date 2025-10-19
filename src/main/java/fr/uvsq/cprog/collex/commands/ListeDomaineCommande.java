package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.*;
import java.util.List;

public class ListeDomaineCommande implements Commande {
    private Dns dns;
    private String domaine;

    public ListeDomaineCommande(Dns dns, String domaine) {
        this.dns = dns;
        this.domaine = domaine;
    }

    @Override
    public String execute() {
        List<DnsItem> items = dns.getItems(domaine);
        if (items.isEmpty()) return "Aucune machine dans le domaine " + domaine;
        StringBuilder result = new StringBuilder(); // utilsiation d'un StringBuilder car une String normale est immuable
        for (DnsItem item : items) {
            result.append(item.getAdresseIP()).append(" ").append(item.getNomMachine()).append("\n");
        }
        return result.toString().trim();
    }
}
