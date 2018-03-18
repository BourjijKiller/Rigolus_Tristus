package test;

import exception.*;
import main.creatures.Creature;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureTest extends CreatureTestAbstraite {

    @Override
    protected Creature creerCreature(int i, boolean b) throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee {
        return new Creature(i,b);
    }

    /**
     * Methode utilisee par testToString().
     */
    @Override
    protected String emoticoneRigolus() {
        return ":)";
    }

    /**
     * Methode utilisee par testToString().
     */
    @Override
    protected String emoticoneTristus() {
        return ":(";
    }

    /**
     * Methode de test de {@link creatures.Creature#affronter()}.
     * Affrontements entre Deprimus et Chantatutetus, puis entre Hilarus et Chantatutetus.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testAffronter6() throws ExceptionJouteImpossible {
        Creature deprimus = creatures[indiceEncyclopedieTristus][5];
        Creature chantatutetus = creatures[indiceEncyclopedieRigolus][16];
        affrontementsSuccessifsEntreUnTristusEtUnRigolus( chantatutetus, deprimus);
        assertEquals(new Integer(9), deprimus.tableauDeChasse().get(chantatutetus));
        assertFalse( chantatutetus.estUnRigolus());
        Creature hilarus = creatures[indiceEncyclopedieRigolus][2];
        hilarus.affronter(chantatutetus);
        assertTrue( compare(hilarus,chantatutetus) > 0);
        assertEquals( 4, chantatutetus.humeur());
        assertTrue( chantatutetus.estUnRigolus());
        assertEquals(new Integer(1), hilarus.tableauDeChasse().get(chantatutetus));
        assertTrue(hilarus.vaincus().contains( chantatutetus));
        assertTrue(hilarus.vaincus().size() == 1);
        assertTrue( chantatutetus.tableauDeChasse().isEmpty());
    }
}