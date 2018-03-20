package test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import TypeJeuMot.TypeDeJeuDeMot;
import org.junit.Before;
import org.junit.Test;

import main.creatures.*;
import outils.*;
import exception.*;

/**
 * Classe abstraite qui definit le code commun des classes de test
 * CreatureTest et CreatureEquanimeTest.
 * NB : Deux bonnes pratiques d'ecriture des tests :
 * 1 - Ne pas initialiser les variables de la classe de test dans un
 * constructeur de la classe de test : il faut utiliser plutot la methode
 * annotee par @Before
 * 2 - Ne pas definir de variables statiques dans une classe de test de
 * facon a garantir que tous les tests restent independants.
 * @author Lucile Torres
 *
 */
public abstract class CreatureTestAbstraite extends AfterCommunTest {

	/* ************************************************************
	 * Variables d'instance communes pour tous les tests : elles
	 * definissent l'environnement constant (fixture) d'execution
	 * des differentes methodes de test.
	 * ************************************************************/

    /** Variable utilisee pour generer un numero de Creature aleatoire. */
    private Random hasard;

    /** Un filtre qui conserve toutes les Creatures. */
    private Filtre<Creature> toujoursVrai;

    /** Un filtre qui ne conserve aucune Creature. */
    private Filtre<Creature> toujoursFaux;

    /** Effectif initial des Rigolus. */
    private int effectifInitialRigolus;

    /** Effectif initial des Tristus. */
    private int effectifInitialTristus;

    /** Indices de l'encyclopedie des Rigolus dans les matrices noms, humeurs et jeuxDeMots. */
    protected int indiceEncyclopedieRigolus;

    /** Indices de l'encyclopedie des Tristus dans les matrices noms, humeurs et jeuxDeMots. */
    protected int indiceEncyclopedieTristus;

    /** Creatures a tester :
     * - le tableau Creature[indiceEncyclopedieRigolus] contient les Rigolus a tester
     * - le tableau Creature[indiceEncyclopedieTristus] contient les Tristus a tester
     */
    protected Creature [][] creatures;

    /** Noms des creatures dans l'encyclopedie :
     * - le tableau noms[indiceEncyclopedieRigolus] contient les noms des Rigolus de l'encyclopedie
     * - le tableau noms[indiceEncyclopedieTristus] contient les noms des Tristus de l'encyclopedie
     */
    protected String [] [] noms;

    /** Humeurs des creatures dans l'encyclopedie :
     * - le tableau humeurs[indiceEncyclopedieRigolus] contient les humeurs des Rigolus de l'encyclopedie
     * - le tableau humeurs[indiceEncyclopedieTristus] contient les humeurs des Tristus de l'encyclopedie
     */
    protected int [] [] humeurs;

    /** JeuxDeMots des creatures dans l'encyclopedie :
     * - le tableau jeuxDeMots[indiceEncyclopedieRigolus] contient le type de jeu de mot initial des Rigolus de l'encyclopedie
     * - le tableau jeuxDeMots[indiceEncyclopedieTristus] contient le type de jeu de mot initial des Tristus de l'encyclopedie
     */
    protected int [] [] jeuxDeMots;

    /** Penalite de base */
    protected int penaliteDeBase;

    /** Creation de la creature de rang donne dans l'encyclopedie
     * des Rigolus ou dans celle des Tristus. Le chef de chaque camp
     * (creature de rang 0 dans l'encyclopedie) doit etre cree le premier
     * et il ne peut pas y avoir d'autre creature creee avec le meme nom.
     * @param i		rang dans l'encyclopedie
     * @param b		true pour creer un Rigolus, false pour un Tristus
     * @return		la creature creee
     * @throws ExceptionCreatureDejaCreee si le chef de ce camp a deja ete cree
     * @throws ExceptionChefNonCree si le chef de ce camp n'a pas encore ete cree
     */
    abstract protected Creature creerCreature(int i, boolean b) throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee;

	/* ************************************************************
	 * Methode "before"
	 * ************************************************************/

    /**
     * Initialisation des creatures.
     * @throws ExceptionChefNonCree
     * @throws ExceptionCreatureDejaCreee
     */
    @Before
    public void setUpBefore() throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee {
        hasard = new Random();
        penaliteDeBase = 5;

		/* Creation de 3 matrices noms, humeurs et jeuxDeMots simulant le contenu des encyclopedies,
		 * telles que :
		 * - les caracteristiques des differents Rigolus sont donnees par les
		 * tableaux noms[indiceEncyclopedieRigolus] , humeurs[indiceEncyclopedieRigolus]
		 * et jeuxDeMots[indiceEncyclopedieRigolus]
		 * - les caracteristiques des differents Tristus sont donnees par les
		 * tableaux noms[indiceEncyclopedieTristus] , humeurs[indiceEncyclopedieTristus]
		 * et jeuxDeMots[indiceEncyclopedieTristus]
		 * Par exemple, noms[indiceEncyclopedieRigolus][0], humeurs[indiceEncyclopedieRigolus][0]
		 * et jeuxDeMots[indiceEncyclopedieRigolus][0] definissent les caracteristiques du premier
		 * Rigolus : Jubilus, 60, 0
		 */
        indiceEncyclopedieRigolus = 1;
        indiceEncyclopedieTristus = 0;
        noms = new String [2][];
        noms[indiceEncyclopedieTristus] = new String [] { "Taciturnus", "Morfondus", "Deplorus",
                "Bilus", "Aplatus", "Deprimus", "Morosus", "Pleurus", "Renfrognus",
                "Lamentatus", "Abattus", "Sinistrus", "Mornus", "Sombrus", "Chagrinus",
                "Melancolicus", "Bougonus", "Colerus" };
        noms[indiceEncyclopedieRigolus] = new String [] { "Jubilus", "Petitmalinus", "Hilarus",
                "Divertitus", "Joyus", "Jovialus", "Pouffus", "Bouffus", "Risettus",
                "Fourirus", "Allegrus", "Plaisantinus", "Blagus", "Humourus", "Sourirus",
                "Esclaffus", "Chantatutetus", "Toutgaitus" };
        humeurs = new int[2][];
        humeurs[indiceEncyclopedieTristus] = new int [] {-60, -51, -75, -56, -105, -40,
                -60, -53, -65, -60, -66, -48, -95, -51, -130, -80, -82, -92};
        humeurs[indiceEncyclopedieRigolus] = new int [] {60, 92, 82, 80, 130, 51,
                95, 48, 66, 225, 65, 53,	60, 40, 105, 56, 75, 51};
        jeuxDeMots = new int[2][];
        jeuxDeMots[indiceEncyclopedieTristus] = new int [] {0, 1, 2, 0, 1, 2,
                0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        jeuxDeMots[indiceEncyclopedieRigolus] = new int [] {0, 1, 2, 0, 1, 2,
                0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        effectifInitialRigolus = noms[indiceEncyclopedieRigolus].length;
        effectifInitialTristus = noms[indiceEncyclopedieTristus].length;
        assertEquals(effectifInitialTristus, humeurs[indiceEncyclopedieTristus].length);
        assertEquals(effectifInitialTristus, jeuxDeMots[indiceEncyclopedieTristus].length);
        assertEquals(effectifInitialRigolus, humeurs[indiceEncyclopedieRigolus].length);
        assertEquals(effectifInitialRigolus, jeuxDeMots[indiceEncyclopedieRigolus].length);

		/* definition du filtre toujoursVrai qui filtre toutes les creatures */
        toujoursVrai = new Filtre<Creature>()  {
            @Override
            public boolean accepte(final Creature element) {
                return true;
            }
        };

		/* definition du filtre toujoursFaux qui ne filtre aucune des creatures */
        toujoursFaux = new Filtre<Creature>()  {
            @Override
            public boolean accepte(final Creature element) {
                return false;
            }
        };

		/* La premiere creature creee est necessairement un chef (de rang 0) */
        try { creerCreature(3, true); throw new AssertionError(); }
        catch ( ExceptionChefNonCree | ExceptionChefDejaCreee e) { }
        try { creerCreature(1, false); throw new AssertionError(); }
        catch ( ExceptionChefNonCree | ExceptionChefDejaCreee e) { }

		/* Creation de la matrice creatures formee de deux lignes :
		 * - la 1ere ligne creatures[indiceEncyclopedieTristus] contient les Tristus initialement crees
		 * - la 2eme ligne creatures[indiceEncyclopedieRigolus] contient les Rigolus initialement crees
		 * On cree un exemplaire de chaque Rigolus possible et un exemplaire de chaque
		 * Tristus possible, donc un nombre de Rigolus egal a effectifInitialRigolus et
		 * un nombre de Tristus egal a effectifInitialTristus.
		 */
        creatures = new Creature[2][];
        creatures[indiceEncyclopedieRigolus] = new Creature[effectifInitialRigolus];
        creatures[indiceEncyclopedieTristus] = new Creature[effectifInitialTristus];
        for (int i = 0; i < effectifInitialRigolus; i++) {
            creatures[indiceEncyclopedieRigolus][i] = creerCreature(i, true);
        }
        for (int i = 0; i < effectifInitialTristus; i++) {
            creatures[indiceEncyclopedieTristus][i] = creerCreature(i, false);
        }
    }

	/* ************************************************************
	 * Methodes utilisees par les methodes de test
	 * ************************************************************/

    /**
     * Methode utilisee par testToString().
     */
    private String dernierCoup( int c) {
        switch (c) {
            case 0 : return "PIERRE";
            case 1 : return "FEUILLE";
            case 2 :
            default :return "CISEAUX";
        }
    }

    /**
     * Methode utilisee par testToString().
     */
    abstract protected String emoticoneRigolus();

    /**
     * Methode utilisee par testToString().
     */
    abstract protected String emoticoneTristus();

    /**
     * Renvoie un entier aleatoire entre deux valeurs incluses.
     * Methode utilisee par plusieurs methodes de test.
     * @param debut		plus petite valeur possible
     * @param fin		plus grande valeur possible
     * @return			un entier aleatoire entre debut et fin inclus
     */
    private int indiceAleatoireEntre(int debut, int fin) {
        return debut + hasard.nextInt(fin - debut);
    }

    /**
     * Affrontement d'un Tristus contre des Rigolus.
     * utilisee par testNombredeJoutes2() et TestVaincusTelQue2().
     * @throws ExceptionJouteImpossible
     */
    private Creature affrontementDUnTristusContreDesRigolus(int nombreDeRigolus) throws ExceptionJouteImpossible {
        assertTrue (nombreDeRigolus <= effectifInitialRigolus);
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        Creature tristus = creatures[indiceEncyclopedieTristus][indice];
        for (int j = indice; j < indice + nombreDeRigolus; j++) {
            tristus.affronter(creatures[indiceEncyclopedieRigolus][j% effectifInitialRigolus]);
        }
        return tristus;
    }

    /**
     * Comparaison de deux creatures par le type de leur dernier jeu de mot.
     * Methode utilisee par affrontementsSuccessifsEntreUnTristusEtUnRigolus(),
     * testAffronter4(), testAffronter5() et testAffronter6().
     */
    protected static int compare( Creature c1, Creature c2) {
        switch ( c1.dernierJeuDeMot() ) {
            case PIERRE : 	if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.FEUILLE) return -1;
            else if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.CISEAUX) return 1;
            else return 0;
            case FEUILLE : 	if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.PIERRE) return 1;
            else if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.CISEAUX) return -1;
            else return 0;
            case CISEAUX : 	if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.FEUILLE) return 1;
            else if (c2.dernierJeuDeMot() == TypeDeJeuDeMot.PIERRE) return -1;
            else return 0;
            default : throw new AssertionError();
        }
    }

    /**
     * Methode utilisee par affrontementsSuccessifsEntreUnTristusEtUnRigolus.
     */
    protected int calculHumeurVaincu(int fois, int valeur) {
        int humeurVaincu = valeur;
        if ( fois != 0 && fois % 10 == 0 ) {
            if ( penaliteDeBase > 0 )
                penaliteDeBase++;
            else
                penaliteDeBase--;
        }
        if ( fois < 3 )
            humeurVaincu += penaliteDeBase;
        else {
            humeurVaincu += 2 * penaliteDeBase;
        }
        if ( humeurVaincu == 0 ) {
            if ( penaliteDeBase > 0 )
                humeurVaincu++;
            else
                humeurVaincu--;
        }
        return humeurVaincu;
    }

    /**
     * Affrontements entre un Rigolus et un Tristus utilisant des types de jeux de mot differents.
     * Methode utilisee par testAffronter4(), testAffronter5() et testAffronter6().
     * @throws ExceptionJouteImpossible
     */
    protected void affrontementsSuccessifsEntreUnTristusEtUnRigolus(Creature un, Creature autre)
            throws ExceptionJouteImpossible {
        assertFalse( compare(un, autre) == 0 );
        Creature vainqueur = null, vaincu = null;
        boolean vaincuEstUnRigolus;
        int humeurVainqueur, humeurVaincu;
        int fois;
        if ( compare(un,autre) > 0 ) {
            vainqueur = un;
            vaincu = autre;
        } else if ( compare(un,autre) < 0 ) {
            vainqueur = autre;
            vaincu = un;
        }
        if ( vainqueur.estUnTristus())
            penaliteDeBase = - penaliteDeBase;
        for ( fois = 0; fois < 40 && vainqueur.estUnRigolus() == vaincu.estUnTristus(); fois++) {
            humeurVainqueur = vainqueur.humeur();
            humeurVaincu = vaincu.humeur();
            un.affronter( autre);
            assertTrue( compare(vainqueur,vaincu) > 0);
            assertEquals( humeurVainqueur, vainqueur.humeur());
            humeurVaincu = calculHumeurVaincu(fois,humeurVaincu);
            assertEquals( humeurVaincu, vaincu.humeur());
        }
        assertEquals(new Integer(fois), vainqueur.tableauDeChasse().get(vaincu));
        assertTrue(vainqueur.vaincus().contains( vaincu));
        assertTrue(vainqueur.vaincus().size() == 1);
        assertTrue( vaincu.tableauDeChasse().isEmpty());
        String premiereLettreDuNomDeVaincu = vaincu.nom().substring(0, 1);
        assertEquals(vainqueur.vaincus(), vainqueur.vaincusTelsQue( new Filtre<Creature> () {
            @Override
            public boolean accepte(Creature c) {
                return c.nom().startsWith( premiereLettreDuNomDeVaincu);
            }}));
    }


	/* ************************************************************
	 * Methodes de test
	 * ************************************************************/

    /**
     * Methode de test de {@link Creature#Creature(int, boolean)}.
     * Il ne peut pas y avoir plusieurs creatures de meme nom que le chef Jubilus.
     * @throws ExceptionChefNonCree
     * @throws ExceptionCreatureDejaCreee
     */
    @Test(expected=ExceptionCreatureDejaCreee.class)
    public void testCreature1() throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee {
        creerCreature(0,true);
    }

    /**
     * Methode de test de {@link Creature#Creature(int, boolean)}.
     * Il ne peut pas y avoir plusieurs creatures de meme nom que le chef Taciturnus.
     * @throws ExceptionChefNonCree
     * @throws ExceptionCreatureDejaCreee
     */
    @Test(expected=ExceptionCreatureDejaCreee.class)
    public void testCreature2() throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee {
        creerCreature(0,false);
    }

    /**
     * Methode de test de {@link Creature#nom()}.
     * Le nom d'une Creature est defini dans l'encyclopedie.
     */
    @Test
    public void testNom() {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertEquals(noms[indiceEncyclopedieTristus][indice], creatures[indiceEncyclopedieTristus][indice].nom());
        indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertEquals(noms[indiceEncyclopedieRigolus][indice], creatures[indiceEncyclopedieRigolus][indice].nom());
    }


    /**
     * Methode de test de {@link Creature#humeur()}.
     * A la creation, l'humeur d'une Creature est definie dans l'encyclopedie.
     */
    @Test
    public void testHumeur() {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertEquals(humeurs[indiceEncyclopedieTristus][indice], creatures[indiceEncyclopedieTristus][indice].humeur());
        indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertEquals(humeurs[indiceEncyclopedieRigolus][indice], creatures[indiceEncyclopedieRigolus][indice].humeur());
    }

    /**
     * Methode de test de {@link Creature#dernierJeuDeMot()} )}.
     * A la creation, le type du dernier jeu de mot utilise par une Creature est defini dans l'encyclopedie.
     */
    @Test
    public void testDernierJeuDeMot1() {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertEquals(indice % 3, creatures[indiceEncyclopedieRigolus][indice].dernierJeuDeMot().ordinal());
        indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertEquals(indice % 3, creatures[indiceEncyclopedieTristus][indice].dernierJeuDeMot().ordinal());
    }

    /**
     * Methode de test de {@link Creature#dernierJeuDeMot()}.
     * A chaque affrontement entre deux creatures avec des types de jeux de mots differents,
     * le type du dernier jeu de mot utilise par les Creatures change.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testDernierJeuDeMot2() throws ExceptionJouteImpossible {
        Creature unRigolus, unTristus, unAutreTristus;
        int indice = indiceAleatoireEntre( 0, Math.min(effectifInitialRigolus, effectifInitialTristus));
        unRigolus = creatures[indiceEncyclopedieRigolus][indice];
        unTristus = creatures[indiceEncyclopedieTristus][indice];
        assertSame( unRigolus.dernierJeuDeMot(), unTristus.dernierJeuDeMot());
        assertSame(indice % 3, unRigolus.dernierJeuDeMot().ordinal());
        unRigolus.affronter( unTristus);
        assertSame( unRigolus.dernierJeuDeMot(), unTristus.dernierJeuDeMot());
        assertSame((indice + 1) % 3, unRigolus.dernierJeuDeMot().ordinal());
        assertSame((indice + 1) % 3, unTristus.dernierJeuDeMot().ordinal());

        unAutreTristus = creatures[indiceEncyclopedieTristus][(indice + 2) % effectifInitialTristus];
        assertNotSame( unRigolus.dernierJeuDeMot(), unAutreTristus.dernierJeuDeMot());
        unRigolus.affronter( unAutreTristus);
        assertSame((indice + 2) % 3, unRigolus.dernierJeuDeMot().ordinal());
        assertEquals(indice % 3, unAutreTristus.dernierJeuDeMot().ordinal());
    }

    /**
     * Methode de test de {@link Creature#estUnRigolus()}.
     * A la creation, les creatures de l'encyclopedie des Rigolus sont toutes des Rigolus, et
     * celles de l'encyclopedie des Tristus n'en sont pas.
     */
    @Test
    public void testEstUnRigolus() {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertFalse(creatures[indiceEncyclopedieTristus][indice].estUnRigolus());
        indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertTrue(creatures[indiceEncyclopedieRigolus][indice].estUnRigolus());
    }

    /**
     * Methode de test de {@link Creature#estUnTristus()}.
     * A la creation, les creatures de l'encyclopedie des Rigolus ne sont pas des Tristus,
     * celles de l'encyclopedie des Tristus oui.
     */
    @Test
    public void testEstUnTristus() {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertTrue(creatures[indiceEncyclopedieTristus][indice].estUnTristus());
        indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertFalse(creatures[indiceEncyclopedieRigolus][indice].estUnTristus());
    }

    /**
     * Methode de test de {@link Creature#toString()}.
     * La description textuelle d'une Creature est de la forme :
     * <Chantatutetus :) H=75 C=FEUILLE> pour une Creature Rigolus ou
     * <Melancolicus :( H=80 C=PIERRE> pour une Creature Tristus ou
     * <Chantatutetus :D H=75 C=FEUILLE> pour une Creature Equanime Rigolus ou
     * <Melancolicus :[ H=80 C=PIERRE> pour une Creature Equanime Tristus
     */
    @Test
    public void testToString() {
        String attendue;
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        attendue = "<" + noms[indiceEncyclopedieRigolus][indice]
                + " " + emoticoneRigolus() + " H=" + humeurs[indiceEncyclopedieRigolus][indice]
                + " C=" + dernierCoup( jeuxDeMots[indiceEncyclopedieRigolus][indice] ) +">";
        assertEquals( attendue, creatures[indiceEncyclopedieRigolus][indice].toString());
        indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        attendue = "<" + noms[indiceEncyclopedieTristus][indice]
                + " " + emoticoneTristus() + " H=" + humeurs[indiceEncyclopedieTristus][indice]
                + " C=" + dernierCoup( jeuxDeMots[indiceEncyclopedieTristus][indice] ) +">";
        assertEquals( attendue, creatures[indiceEncyclopedieTristus][indice].toString());
    }

    /**
     * Methode de test de {@link Creature#nombreDeJoutes()}.
     * A sa creation, une Creature comptabilise zero joutes.
     */
    @Test
    public void testNombreDeJoutes1() {
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        assertEquals(0, creatures[indiceEncyclopedieRigolus][indice].nombreDeJoutes());
        indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        assertEquals(0, creatures[indiceEncyclopedieTristus][indice].nombreDeJoutes());
    }

    /**
     * Methode de test de {@link Creature#nombreDeJoutes()}.
     * Apres l'affrontement contre 7 Rigolus, un Tristus compte 7 joutes.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testNombreDeJoutes2() throws ExceptionJouteImpossible  {
        Creature tristus = affrontementDUnTristusContreDesRigolus(7);
        assertEquals(7, tristus.nombreDeJoutes());
    }

    /**
     * Methode de test de {@link Creature#nombreDeJoutes()}.
     * Apres l'affrontement contre tous les (18) Rigolus, un Tristus compte 18 joutes
     * et chaque Rigolus 1 joute.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testNombreDeJoutes3() throws ExceptionJouteImpossible  {
        Creature tristus = affrontementDUnTristusContreDesRigolus(effectifInitialRigolus);
        assertEquals(effectifInitialRigolus, tristus.nombreDeJoutes());
        for (Creature c : creatures[indiceEncyclopedieRigolus])
            assertEquals(1, c.nombreDeJoutes());
    }


    /**
     * Methode de test de {@link Creature#tableauDeChasse()}.
     * A sa creation, une creature a un tableau de chasse vide.
     */
    @Test
    public void testTableauDeChasse() {
        Map<Creature,Integer> resultat;
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        resultat = creatures[indiceEncyclopedieRigolus][indice].tableauDeChasse();
        assertTrue(resultat.isEmpty());
        resultat.put(creatures[indiceEncyclopedieRigolus][0], 1);
        assertFalse(resultat.isEmpty());
        assertTrue( creatures[indiceEncyclopedieRigolus][indice].tableauDeChasse().isEmpty());

        indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        resultat = creatures[indiceEncyclopedieTristus][indice].tableauDeChasse();
        assertTrue(resultat.isEmpty());
        resultat.put(creatures[indiceEncyclopedieTristus][0], 1);
        assertFalse(resultat.isEmpty());
        assertTrue( creatures[indiceEncyclopedieTristus][indice].tableauDeChasse().isEmpty());
    }

    /**
     * Methode de test de {@link Creature#vaincus()}.
     * A sa creation, une creature n'a vaincu aucune autre creature.
     */
    @Test
    public void testVaincus() {
        Set<Creature> resultat;
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        resultat = creatures[indiceEncyclopedieRigolus][indice].vaincus();
        assertTrue(resultat.isEmpty());
        resultat.add( creatures[indiceEncyclopedieTristus][10]);
        assertFalse(resultat.isEmpty());
        assertTrue(creatures[indiceEncyclopedieRigolus][indice].vaincus().isEmpty());

        indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        resultat = creatures[indiceEncyclopedieTristus][indice].vaincus();
        assertTrue(resultat.isEmpty());
        resultat.add( creatures[indiceEncyclopedieRigolus][10]);
        assertFalse(resultat.isEmpty());
        assertTrue(creatures[indiceEncyclopedieTristus][indice].vaincus().isEmpty());
    }


    /**
     * Methode de test de {@link Creature#vaincusTelsQue(Filtre)}.
     * * A sa creation, une creature n'a vaincu aucune autre creature.
     */
    @Test
    public void testVaincusTelsQue1() {
        Set<Creature> resultat;
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        resultat = creatures[indiceEncyclopedieRigolus][indice].vaincusTelsQue(toujoursVrai);
        assertTrue(resultat.isEmpty());
        resultat.add(creatures[indiceEncyclopedieRigolus][0]);
        assertFalse(resultat.isEmpty());
        assertTrue(creatures[indiceEncyclopedieRigolus][indice].vaincusTelsQue(toujoursVrai).isEmpty());

        indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        resultat = creatures[indiceEncyclopedieTristus][indice].vaincusTelsQue(toujoursVrai);
        assertTrue(resultat.isEmpty());
        resultat.add(creatures[indiceEncyclopedieTristus][0]);
        assertFalse(resultat.isEmpty());
        assertTrue(creatures[indiceEncyclopedieTristus][indice].vaincusTelsQue(toujoursVrai).isEmpty());
    }

    /**
     * Methode de test de {@link Creature#vaincusTelsQue(Filtre)}.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testVaincusTelsQue2() throws ExceptionJouteImpossible {
        affrontementDUnTristusContreDesRigolus( effectifInitialRigolus);
        for (int i = 0; i < effectifInitialRigolus; i++) {
            assertEquals(creatures[indiceEncyclopedieRigolus][i].vaincus(),
                    creatures[indiceEncyclopedieRigolus][i].vaincusTelsQue(toujoursVrai));
            assertTrue(creatures[indiceEncyclopedieRigolus][i].vaincusTelsQue(toujoursFaux).isEmpty());
        }

        for (int i = 0; i < effectifInitialTristus; i++) {
            assertEquals(creatures[indiceEncyclopedieTristus][i].vaincus(),
                    creatures[indiceEncyclopedieTristus][i].vaincusTelsQue(toujoursVrai));
            assertTrue(creatures[indiceEncyclopedieTristus][i].vaincusTelsQue(toujoursFaux).isEmpty());
        }
    }

    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * Une Creature Tristus ne peut pas s'affronter elle-même.
     * @throws ExceptionJouteImpossible
     */
    @Test(expected=ExceptionJouteImpossible.class)
    public void testAffronter1() throws ExceptionJouteImpossible {
        int indice = indiceAleatoireEntre( 0, effectifInitialTristus);
        creatures[indiceEncyclopedieTristus][indice].affronter(
                creatures[indiceEncyclopedieTristus][indice] );
    }

    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * Une Creature Rigolus ne peut pas s'affronter elle-même.
     * @throws ExceptionJouteImpossible
     */
    @Test(expected=ExceptionJouteImpossible.class)
    public void testAffronter2() throws ExceptionJouteImpossible {
        int indice = indiceAleatoireEntre( 0, effectifInitialRigolus);
        creatures[indiceEncyclopedieRigolus][indice].affronter(
                creatures[indiceEncyclopedieRigolus][indice] );
    }

    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * L'affrontement entre deux creatures du meme camp (Rigolus ou Tristus)
     * ne fait rien.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testAffronter3() throws ExceptionJouteImpossible {
        int min = Math.min( effectifInitialTristus, effectifInitialRigolus);
        int indice1 = indiceAleatoireEntre( 0, min);
        int indice2;
        do {
            indice2 = indiceAleatoireEntre( 0, min);
        } while (indice1 == indice2 );
        Creature creature1, creature2;
        String stringAvant1, stringAvant2;
        int nbJoutesAvant1, nbJoutesAvant2;

        // affrontement entre deux Tristus
        creature1 = creatures[indiceEncyclopedieTristus][indice1];
        creature2 = creatures[indiceEncyclopedieTristus][indice2];
        stringAvant1 = creature1.toString();
        nbJoutesAvant1 = creature1.nombreDeJoutes();
        stringAvant2 = creature2.toString();
        nbJoutesAvant2 = creature2.nombreDeJoutes();
        creature1.affronter(creature2);
        assertEquals( stringAvant1, creature1.toString());
        assertEquals( nbJoutesAvant1, creature1.nombreDeJoutes());
        assertTrue( creature1.tableauDeChasse().isEmpty());
        assertTrue( creature1.vaincus().isEmpty());
        assertEquals( stringAvant2, creature2.toString());
        assertEquals( nbJoutesAvant2, creature2.nombreDeJoutes());
        assertTrue( creature2.vaincus().isEmpty());

        // affrontement entre deux Rigolus
        creature1 = creatures[indiceEncyclopedieRigolus][indice1];
        creature2 = creatures[indiceEncyclopedieRigolus][indice2];
        stringAvant1 = creature1.toString();
        nbJoutesAvant1 = creature1.nombreDeJoutes();
        stringAvant2 = creature2.toString();
        nbJoutesAvant2 = creature2.nombreDeJoutes();
        creature1.affronter(creature2);
        assertEquals( stringAvant1, creature1.toString());
        assertEquals( nbJoutesAvant1, creature1.nombreDeJoutes());
        assertTrue( creature1.tableauDeChasse().isEmpty());
        assertTrue( creature1.vaincus().isEmpty());
        assertEquals( stringAvant2, creature2.toString());
        assertEquals( nbJoutesAvant2, creature2.nombreDeJoutes());
        assertEquals( nbJoutesAvant2, creature2.nombreDeJoutes());
        assertTrue( creature2.vaincus().isEmpty());
    }



    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * Affrontements entre un Rigolus et un Tristus utilisant des types de jeux de mot differents.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testAffronter4() throws ExceptionJouteImpossible {
        int indiceRigolus;
        int indiceTristus = indiceAleatoireEntre(0, effectifInitialTristus);
        Creature rigolus;
        Creature tristus = creatures[indiceEncyclopedieTristus][indiceTristus];
        do {
            indiceRigolus = indiceAleatoireEntre(0, effectifInitialRigolus);
            rigolus = creatures[indiceEncyclopedieRigolus][indiceRigolus];
        } while ( compare(tristus,rigolus) == 0 );
        affrontementsSuccessifsEntreUnTristusEtUnRigolus(tristus, rigolus);
    }

    /**
     * Methode de test de {@link Creature#affronter(Creature)}.
     * Affrontements entre Morfondus et Fourirus, puis entre Morfondus et Joyus.
     * @throws ExceptionJouteImpossible
     */
    @Test
    public void testAffronter5() throws ExceptionJouteImpossible {
        Creature morfondus = creatures[indiceEncyclopedieTristus][1];
        Creature fourirus = creatures[indiceEncyclopedieRigolus][9];
        affrontementsSuccessifsEntreUnTristusEtUnRigolus( fourirus, morfondus);
        Creature joyus = creatures[indiceEncyclopedieRigolus][4];
        morfondus.affronter( joyus);
        assertTrue( compare(morfondus,joyus) > 0);
        assertEquals(new Integer(1), morfondus.tableauDeChasse().get(joyus));
        assertTrue(morfondus.vaincus().contains( joyus));
        assertTrue(morfondus.vaincus().size() == 2);
        assertTrue( joyus.tableauDeChasse().isEmpty());
    }
}
