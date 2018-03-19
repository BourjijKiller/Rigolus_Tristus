package test;

import exception.ExceptionJouteImpossible;
import exception.PlaneteDeserteException;
import main.creatures.Creature;
import main.creatures.CreatureEquanime;
import main.planete.Planete;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

class PlaneteTest extends AfterCommunTest
{
    private Planete planete;
    public ArrayList<Creature> habitants;

    @Before
    public void setUp() throws Exception
    {

        /* La planete Rigolus-Tristus */
        planete = Creature.laPlanete();
        try {
            planete.indiceDeBonheurBrut();
            throw new AssertionError();
        }
        catch (PlaneteDeserteException e) { }
        assertEquals( "Rigolus-Tristus population=0", planete.toString());

        /* Creation des habitants de la planete Lepiedecheze */
        habitants = new ArrayList<Creature>();
        habitants.add(new Creature(0,true));
        habitants.add(new CreatureEquanime(17,true));
        habitants.add(new Creature(5,true));
        habitants.add(new Creature(9,true));
        habitants.add(new Creature(0,false));
        habitants.add(new Creature(16,false));
        habitants.add(new Creature(8,false));
        habitants.add(new Creature(4,false));
        assertEquals( "Rigolus-Tristus population=8 IBB=9.0", planete.toString());
    }

    @Test
    public void test1() {
        String attendue, obtenue;
        attendue = "Jubilus Toutgaitus Jovialus Fourirus Taciturnus Bougonus Renfrognus Aplatus ";
        obtenue = "";
        for( Creature c : planete)
            obtenue += c.nom() + " ";
        assertEquals(attendue, obtenue);
    }

    @Test
    public void test2() {
        Iterator<Creature> it;
        String attendue, obtenue;
        attendue = "Jubilus Toutgaitus Jovialus Fourirus ";
        obtenue = "";
        it = planete.iterateurDesRigolus();
        while( it.hasNext())
            obtenue += it.next().nom() + " ";
        assertEquals(attendue, obtenue);
    }

    @Test
    public void test3() {
        Iterator<Creature> it;
        String attendue, obtenue;
        attendue = "Taciturnus Bougonus Renfrognus Aplatus ";
        obtenue = "";
        it = planete.iterateurDesTristus();
        while( it.hasNext())
            obtenue += it.next().nom() + " ";
        assertEquals(attendue, obtenue);
    }

    @Test
    public void test4() throws PlaneteDeserteException {
        assertEquals( 9.0, planete.indiceDeBonheurBrut(), 0.001);
    }

    @Test
    public void test5() {
        assertEquals( 0, planete.rigolusEnDangerEnDessousDe(10).size());
        assertEquals( 1, planete.rigolusEnDangerEnDessousDe(51).size());
        assertEquals( "[<Jovialus :) H=51 C=CISEAUX>]",
                planete.rigolusEnDangerEnDessousDe(51).toString());
    }

    @Test
    public void test6() {
        assertEquals( "[Aplatus, Bougonus, Renfrognus, Taciturnus, Toutgaitus, Jovialus, Jubilus, Fourirus]",
                planete.listeLesNomsDesCreaturesParHumeurCroissante().toString());
    }

    @Test
    public void test7() throws ExceptionJouteImpossible {
        Iterator<Creature> it = planete.iterateurDesRigolus();
        Creature jubilus = it.next();
        it = planete.iterateurDesTristus();
        it.next();
        Creature bougonus = it.next();
        jubilus.affronter( bougonus);
        bougonus.affronter( jubilus);
        it.next();
        Creature aplatus = it.next();
        aplatus.affronter( jubilus);
        aplatus.affronter( jubilus);
        assertEquals( 2, bougonus.nombreDeVictoires());
        assertEquals( 2, jubilus.nombreDeVictoires());
        assertEquals( 0, aplatus.nombreDeVictoires());
        assertEquals( 2, planete.listeLesNomsDesCreaturesLesPlusFortes().size());
        assertTrue( planete.listeLesNomsDesCreaturesLesPlusFortes().contains( "Jubilus"));
        assertTrue( planete.listeLesNomsDesCreaturesLesPlusFortes().contains( "Bougonus"));
    }
}