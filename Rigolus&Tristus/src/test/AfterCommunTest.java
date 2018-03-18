package test;

import main.creatures.Creature;
import main.planete.Planete;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

class AfterCommunTest
{
    /* ************************************************************
	 * Methode "after"
	 * ************************************************************/

    /**
     * Reinitialisation des variables statiques de Creature pour garantir l'independance
     * des tests. Les variables statiques rangsDesRigolusCreees et rangsDesRTristusCreees
     * conservent les rangs des encyclopedies deja utilises pour creer des instances de
     * Creature ou CreatureEquanime afin d'eviter la creation de "doublons". Ces variables
     * doivent etre reinitialisees a une liste vide d'Integer avant chaque nouveau test pour
     * pouvoir permettre la creation de toutes les creatures.
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    @After
    public void tearDown() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        java.lang.reflect.Field champ1 = Creature.class.getDeclaredField("rangsDesRigolusCreees");
        assertFalse(champ1.isAccessible());
        champ1.setAccessible(true);
        champ1.set(null, new ArrayList<Integer>());
        champ1.setAccessible(false);

        java.lang.reflect.Field champ2 = Creature.class.getDeclaredField("rangsDesTristusCreees");
        assertFalse(champ2.isAccessible());
        champ2.setAccessible(true);
        champ2.set(null, new ArrayList<Integer>());
        champ2.setAccessible(false);

        java.lang.reflect.Field champ3 = Creature.class.getDeclaredField("laPlanete");
        assertFalse(champ3.isAccessible());
        champ3.setAccessible(true);
        champ3.set(null, new Planete("Rigolus-Tristus"));
        champ3.setAccessible(false);
    }
}