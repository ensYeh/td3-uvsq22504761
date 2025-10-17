package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class NomMachineTest {
    @Test
    public void testNomMachineValide() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        assertEquals("www.uvsq.fr", nom.getNomComplet());
        assertEquals("www", nom.getMachine());
        assertEquals("uvsq.fr", nom.getDomaine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomMachineInvalide() {
        new NomMachine("invalid");
    }

    @Test
    public void testEquals() {
        NomMachine nom1 = new NomMachine("www.uvsq.fr");
        NomMachine nom2 = new NomMachine("www.uvsq.fr");
        NomMachine nom3 = new NomMachine("poste.uvsq.fr");
        assertEquals(nom1, nom2);
        assertNotEquals(nom1, nom3);
    }
}
