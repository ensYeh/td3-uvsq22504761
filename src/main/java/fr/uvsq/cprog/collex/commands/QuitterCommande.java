package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.*;

public class QuitterCommande implements Commande {
    @Override
    public String execute() {
        return "Quitter";
    }
}
