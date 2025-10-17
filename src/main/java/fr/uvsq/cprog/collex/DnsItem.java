package fr.uvsq.cprog.collex;

import java.util.Objects;

/**
 * Représente une entrée DNS
 */
public class DnsItem {
    private AdresseIP ip;
    private NomMachine nom;

    public DnsItem(AdresseIP ip, NomMachine nom) {
        if (ip == null || nom == null) {
            throw new NullPointerException("L'adresse IP ou la machine a une valeur null");
        }
        this.ip = ip;
        this.nom = nom;
    }

    // Accesseurs
    public AdresseIP getAdresseIP() {
        return this.ip;
    }

    public NomMachine getNomMachine() {
        return this.nom;
    }

    @Override
    public String toString() {
        return this.ip + " " + this.nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem autre = (DnsItem) o;
        return this.ip.equals(autre.ip) && this.nom.equals(autre.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ip, this.nom);
    }
}
