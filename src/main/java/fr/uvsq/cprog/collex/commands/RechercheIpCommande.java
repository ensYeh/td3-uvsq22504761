package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.*;

public class RechercheIpCommande implements Commande {
    private Dns dns;
    private NomMachine nom;

    public RechercheIpCommande(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        DnsItem item = this.dns.getItem(this.nom);
        return item != null ? item.getAdresseIP().toString() : "Nom inconnu";
    }
}
