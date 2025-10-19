package fr.uvsq.cprog.collex;

import java.util.Properties;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class Dns {
    private List<DnsItem> items;
    private Path dbPath;

    public Dns(Properties properties) throws IOException {
        this.items = new ArrayList<>();
        String filePath = properties.getProperty("db.file", "dns.txt");
        if (filePath == null) throw new IllegalArgumentException("il manque la propriété dns.file");
        this.dbPath = Paths.get(filePath);

        if (Files.exists(dbPath)) {
            List<String> lines = Files.readAllLines(dbPath);
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 2) continue;

                NomMachine nom = new NomMachine(parts[0]);
                AdresseIP ip = new AdresseIP(parts[1]);
                
                this.items.add(new DnsItem(ip, nom));
            }
        }

        
    }   

    public DnsItem getItem(AdresseIP ip) {
        return this.items.stream().filter(item -> item.getAdresseIP().equals(ip)).findFirst().orElse(null);
    }

    public DnsItem getItem(NomMachine nom) { // pareil ici
        return this.items.stream().filter(item -> item.getNomMachine().equals(nom)).findFirst().orElse(null);
    }

    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> result = this.items.stream().filter(item -> item.getNomMachine().getDomaine().equals(domaine)).collect(Collectors.toList());
        Collections.sort(result, (item1, item2) -> item1.getNomMachine().getMachine().compareTo(item2.getNomMachine().getMachine()));
        return result;
    }

    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (getItem(ip) != null) throw new IllegalArgumentException("L'adresse IP existe déjà");
        if (getItem(nom) != null) throw new IllegalArgumentException("Le nom de machine existe déjà");
        DnsItem item = new DnsItem(ip, nom);
        this.items.add(item);
        
        List<String> lines = this.items.stream().map(i -> i.getNomMachine().getNomComplet() + " " + i.getAdresseIP().getIp()).collect(Collectors.toList());
        Files.write(this.dbPath, lines);
    }
}
