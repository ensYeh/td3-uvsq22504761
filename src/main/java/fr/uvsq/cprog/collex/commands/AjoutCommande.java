package fr.uvsq.cprog.collex.commands;

import java.io.IOException;

import fr.uvsq.cprog.collex.*;

public class AjoutCommande implements Commande {
    private Dns dns;
    private AdresseIP ip;
    private NomMachine nom;

    public AjoutCommande(Dns dns, AdresseIP ip, NomMachine nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute() {
        try {
            dns.addItem(ip, nom);
            return "Ajout réussi";
        } catch (IllegalArgumentException e) {
            return "Erreur : " + e.getMessage();
        } catch (IOException e) {
            return "Problème d'écriture dans le fichier";
        }
    }
}
