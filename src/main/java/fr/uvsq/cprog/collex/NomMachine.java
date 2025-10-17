package fr.uvsq.cprog.collex;

import java.util.Objects;   // pour le hashcode
import java.util.regex.Pattern;

/**
 * Représente un nom qualifié de machine
 */
public class NomMachine {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"); // vérifie qu'il y ait au moins un . et que les caractères aient du sens

    private String nomComplet;

    public NomMachine(String nomComplet) {
        if (nomComplet == null|| !NAME_PATTERN.matcher(nomComplet).matches()) {
            throw new IllegalArgumentException("Le nom vaut null");
        }
        this.nomComplet = nomComplet;
    }

    // Accesseur
    public String getNomComplet() {
        return this.nomComplet;
    }

    public String getMachine() {
        String[] parts = nomComplet.split("\\.");
        return parts[0];
    }

    public String getDomaine() {
        String[] parts = nomComplet.split("\\.", 2); // limite à 2 pour récupérer tout après la 1ère partie pour le nom de domaine
        return parts[1];
    }

    @Override
    public String toString() {
        return this.nomComplet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine autre = (NomMachine) o;
        return this.nomComplet.equals(autre.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nomComplet);
    }
}
