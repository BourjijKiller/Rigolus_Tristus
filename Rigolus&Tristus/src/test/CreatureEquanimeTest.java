package test;

import exception.*;
import main.creatures.Creature;
import main.creatures.CreatureEquanime;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

class CreatureEquanimeTest extends CreatureTestAbstraite {
    @Override
    protected Creature creerCreature(int i, boolean b) throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee {
        return new CreatureEquanime(i, b);
    }

    /**
     * Methode utilisee par testToString().
     */
    @Override
    protected String emoticoneRigolus() {
        return ":D";
    }

    /**
     * Methode utilisee par testToString().
     */
    @Override
    protected String emoticoneTristus() {
        return ":[";
    }

    /**
     * Methode utilisee par affrontementsSuccessifsEntreUnTristusEtUnRigolus.
     */
    @Override
    protected int calculHumeurVaincu(int fois, int valeur) {
        return valeur;
    }

    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * Affrontements entre Deprimus et Chantatutetus, puis entre Hilarus et Chantatutetus.
     *
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testAffronter6() throws ExceptionJouteImpossible {
        Creature deprimus = creatures[indiceEncyclopedieTristus][5];
        Creature chantatutetus = creatures[indiceEncyclopedieRigolus][16];
        affrontementsSuccessifsEntreUnTristusEtUnRigolus(chantatutetus, deprimus);
        assertEquals(new Integer(40), deprimus.tableauDeChasse().get(chantatutetus));
        assertTrue(chantatutetus.estUnRigolus());
        Creature hilarus = creatures[indiceEncyclopedieRigolus][2];
        hilarus.affronter(chantatutetus);
        assertTrue(compare(hilarus, chantatutetus) == 0);
        assertEquals(82, hilarus.humeur());
        assertTrue(hilarus.estUnRigolus());
        assertTrue(hilarus.tableauDeChasse().isEmpty());
        assertTrue(chantatutetus.tableauDeChasse().isEmpty());
    }
}