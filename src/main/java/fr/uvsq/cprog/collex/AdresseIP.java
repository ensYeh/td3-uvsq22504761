package fr.uvsq.cprog.collex;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Représente une adresse IP
 */
public class AdresseIP {
    private static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$"); // vérifie que l'IP soit au format xxx.xxx.xxx.xxx

    // on stocke l'IP à la fois sous forme de tableau d'octets et sous chaine de caractères pour faciliter certaines méthodes
    private String ip;
    private int[] octets;

    public AdresseIP(String ip) {
        if (ip == null || !IP_PATTERN.matcher(ip).matches()) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        String[] parts = ip.split("\\.");
        this.octets = new int[4];
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(parts[i]);
            if (octet < 0 || octet > 255) {     // chaque nombre (xxx) dans l'IP doit être compris entre 0 et 255
                throw new IllegalArgumentException("Octet hors plage [0,255] : " + octet);
            }
            this.octets[i] = octet;
        }
        this.ip = ip;
    }

    // Accesseur
    public String getIp() {
        return this.ip;
    }

    @Override
    public String toString() {
        return this.ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP ip2 = (AdresseIP) o;
        return this.ip.equals(ip2.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(octets[0], octets[1], octets[2], octets[3]);
    }
}
