package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.*;

public class RechercheNomCommande implements Commande {
    private Dns dns;
    private AdresseIP ip;

    public RechercheNomCommande(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public String execute() {
        DnsItem item = this.dns.getItem(this.ip);
        return item != null ? item.getNomMachine().toString() : "Adresse IP inconnue";
    }
}
