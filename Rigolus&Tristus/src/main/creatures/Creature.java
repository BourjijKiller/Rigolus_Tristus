package main.creatures;

import TypeJeuMot.TypeDeJeuDeMot;
import exception.ExceptionChefDejaCreee;
import exception.ExceptionChefNonCree;
import exception.ExceptionCreatureDejaCreee;
import exception.ExceptionJouteImpossible;
import main.planete.Planete;
import outils.Filtre;

import java.util.*;

/**
 * Création de la classe Creature, composée des Rigolus et des Tristus
 */
public class Creature implements Comparable<Creature>
{
    // Caractéristique d'une créature
    private String nom;
    private int ptsHumeurs;
    private TypeDeJeuDeMot dernierJeuxMots;

    // Récupération du nombre d'instance créé qui servira dans les Encyclopédies des Tristus et des Rigolus
    private static int nbInstanceRigolus = 0;
    private static int nbInstanceTristus = 0;

    // Booleen vérifiant la création du chef
    private boolean chefRigolusCree = false;
    private boolean chefTristusCree = false;

    // Listes des Encyclopédie pour les Rigolus
    private List<String> listNomRigolus = new ArrayList<String>();
    private List<Integer> listPtsHumeursRigolus = new ArrayList<Integer>();
    private List<Integer> listDernierJeuxMotsRigolus = new ArrayList<Integer>();

    // Listes des Encyclopédie pour les Tristus
    private List<String> listNomTristus = new ArrayList<String>();
    private List<Integer> listPtsHumeursTristus = new ArrayList<Integer>();
    private List<Integer> listDernierJeuxMotsTristus = new ArrayList<Integer>();

    // Tableau de nom des Rigolus
    private String [] tabNomRigolus = new String [] { "Jubilus", "Petitmalinus", "Hilarus",
        "Divertitus", "Joyus", "Jovialus", "Pouffus", "Bouffus", "Risettus",
        "Fourirus", "Allegrus", "Plaisantinus", "Blagus", "Humourus", "Sourirus",
        "Esclaffus", "Chantatutetus", "Toutgaitus" };

    // Tableau de nom des Tristus
    private String [] tabNomTristus = new String[] { "Taciturnus", "Morfondus", "Deplorus",
            "Bilus", "Aplatus", "Deprimus", "Morosus", "Pleurus", "Renfrognus",
            "Lamentatus", "Abattus", "Sinistrus", "Mornus", "Sombrus", "Chagrinus",
            "Melancolicus", "Bougonus", "Colerus" };

    // Tableau des humeurs des Tristus
    private int [] tabHumeursTristus = new int [] {-60, -51, -75, -56, -105, -40, -60, -53, -65, -60, -66, -48, -95, -51, -130, -80, -82, -92};

    // Tableau des humeurs des Rigolus
    private int [] tabHumeursRigolus = new int [] {60, 92, 82, 80, 130, 51, 95, 48, 66, 225, 65, 53, 60, 40, 105, 56, 75, 51};

    // Tableau des jeux de mots des Tristus
    private int [] tabJeuxRigolus = new int [] {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};

    // Tableau des jeux des Rigolus
    private int [] tabJeuxTristus = new int [] {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};

    // Liste à 3 dimension des Rigolus déjà créé
    private ArrayList<ArrayList<Object>> rangsDesRigolusCreees = new ArrayList<ArrayList<Object>>();

    // Liste à 3 dimension des Tristus déja créé
    private ArrayList<ArrayList<Object>> rangsDesTristusCreees = new ArrayList<ArrayList<Object>>();

    // Type de jeu de mots pour le jeu PIERRE-FEUILLE-PAPIER entre les créatures
    private TypeDeJeuDeMot pierre = TypeDeJeuDeMot.PIERRE;
    private TypeDeJeuDeMot feuille = TypeDeJeuDeMot.FEUILLE;
    private TypeDeJeuDeMot ciseaux = TypeDeJeuDeMot.CISEAUX;

    // Variable stockant le nombre d'affrontements de la créature
    private int nbAffrontements = 0;

    // Variable stockant le nombre de défaites de la créature
    private int nbDefaite = 0;

    // Table de hachage permettant de stocker la créature ainsi que le nombre de fois ou elle a été vaincue
    HashMap<Creature, Integer> vaincus = new HashMap<Creature, Integer>();

    // Ensemble contenant toute les créatures vaincues
    HashSet<Creature> creatureVaincus = new HashSet<Creature>();

    /**
     * Indique l'encyclopédie à utiliser selon le paramètre b
     * @param indiceEncyclopedie rang dans l'encyclopédie de la créature à créer
     * @param b Indique l'encyclopédie a utiliser pour créer la créature
     * @throws ExceptionCreatureDejaCreee
     * @throws ExceptionChefNonCree
     * @throws ExceptionChefDejaCreee
     */
    public Creature(int indiceEncyclopedie, boolean b) throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee
    {
        // Remplissage des listes en fonctions des tableaux déclarés ci-dessus
        this.listNomRigolus = Arrays.asList(this.tabNomRigolus);
        this.listNomTristus = Arrays.asList(this.tabNomTristus);
        for (int i = 0; i < this.tabHumeursRigolus.length; i++)
            this.listPtsHumeursRigolus.add(this.tabHumeursRigolus[i]);
        for(int j = 0; j < this.tabHumeursTristus.length; j++)
            this.listPtsHumeursTristus.add(this.tabHumeursTristus[j]);
        for(int k = 0; k < this.tabJeuxRigolus.length; k++)
            this.listDernierJeuxMotsRigolus.add(this.tabJeuxRigolus[k]);
        for(int l = 0; l < this.tabJeuxTristus.length; l++)
            this.listDernierJeuxMotsTristus.add(this.tabJeuxTristus[l]);

        // Structure de l'ArrayList à 3 dimensions
        for(int i = 0; i < 2; i++)
            this.rangsDesRigolusCreees.add(new ArrayList<Object>());
        for(int j = 0; j < 2; j++)
            this.rangsDesTristusCreees.add(new ArrayList<Object>());

        // si b ==  true, on créé un Rigolus
        if(b) {
            /* On créé un chef, si celui-ci n'est pas déjà présent dans la liste des Rigolus déjà créé
            On ajoute ensuite le chef dans la liste des Rigolus créé pour bloquer la création d'un deuxième chef
             */
            if(indiceEncyclopedie == 0) {
                if(this.rangsDesRigolusCreees.get(0).isEmpty() && this.rangsDesRigolusCreees.get(1).isEmpty() && this.rangsDesRigolusCreees.get(2).isEmpty()) {
                    // Création du chef des Rigolus
                    nbInstanceRigolus++;
                    this.rangsDesRigolusCreees.get(0).add(this.listNomRigolus.get(indiceEncyclopedie));
                    this.rangsDesRigolusCreees.get(1).add(this.listPtsHumeursRigolus.get(indiceEncyclopedie));
                    this.rangsDesRigolusCreees.get(2).add(this.listDernierJeuxMotsRigolus.get(indiceEncyclopedie));
                    this.rangsDesRigolusCreees.get(3).add(this.nombreDeJoutes());
                    this.rangsDesRigolusCreees.get(4).add(this.getNbDefaite());
                    this.nom = (String)this.rangsDesRigolusCreees.get(0).get(0);
                    this.ptsHumeurs = (Integer)this.rangsDesRigolusCreees.get(1).get(0);
                    this.dernierJeuxMots = (TypeDeJeuDeMot) this.rangsDesRigolusCreees.get(2).get(0);
                    this.nbAffrontements = (Integer)this.rangsDesRigolusCreees.get(3).get(0);
                    this.nbDefaite = (Integer)this.rangsDesRigolusCreees.get(4).get(0);
                    this.chefRigolusCree = true;
                }
                else
                    throw new ExceptionChefDejaCreee();
            }
            /*
            On créé la créature, si celle-ci n'est pas déjà présente dans la liste des Rigolus créé
            On ajoute ensuite cette créature dans la liste des Rigolus créé pour bloquer la création d'une deuxième créature identique
            Vérification si le chef est bien créé avant la création d'autres créatures
             */
            else {
                /*
                On vérifie si le chef a bien été créé avant de créer d'autres créatures
                 */
                if(this.chefRigolusCree) {
                    if(this.rangsDesRigolusCreees.get(0).contains(this.listNomRigolus.get(indiceEncyclopedie))) {
                        if(this.rangsDesRigolusCreees.get(1).contains(this.listPtsHumeursRigolus.get(indiceEncyclopedie))) {
                            if(this.rangsDesRigolusCreees.get(2).contains(this.listDernierJeuxMotsRigolus.get(indiceEncyclopedie)))
                                throw new ExceptionCreatureDejaCreee();
                        }
                    }
                    else {
                        // Création d'un Rigolus
                        nbInstanceRigolus++;
                        this.rangsDesRigolusCreees.get(0).add(this.listNomRigolus.get(indiceEncyclopedie));
                        this.rangsDesRigolusCreees.get(1).add(this.listPtsHumeursRigolus.get(indiceEncyclopedie));
                        this.rangsDesRigolusCreees.get(2).add(this.listDernierJeuxMotsRigolus.get(indiceEncyclopedie));
                        this.rangsDesRigolusCreees.get(3).add(this.nombreDeJoutes());
                        this.rangsDesRigolusCreees.get(4).add(this.getNbDefaite());
                        this.nom = (String)this.rangsDesRigolusCreees.get(0).get(nbInstanceRigolus);
                        this.ptsHumeurs = (Integer)this.rangsDesRigolusCreees.get(1).get(nbInstanceRigolus);
                        this.dernierJeuxMots = (TypeDeJeuDeMot) this.rangsDesRigolusCreees.get(2).get(nbInstanceRigolus);
                        this.nbAffrontements = (Integer)this.rangsDesRigolusCreees.get(3).get(nbInstanceRigolus);
                        this.nbDefaite = (Integer)this.rangsDesRigolusCreees.get(4).get(nbInstanceRigolus);
                    }
                }
                else
                    throw new ExceptionChefNonCree();
            }
        }
        // si b == false, on créé un Tristus
        else {
            /* On créé un chef, si celui-ci n'est pas déjà présent dans la liste des Tristus déjà créé
            On ajoute ensuite le chef dans la liste des Tristus créée pour bloquer la création d'un deuxième chef
             */
            if(indiceEncyclopedie == 0) {
                if(this.rangsDesTristusCreees.get(0).isEmpty() && this.rangsDesTristusCreees.get(1).isEmpty() && this.rangsDesTristusCreees.get(2).isEmpty()) {
                    // Création du chef des Tristus
                    nbInstanceTristus++;
                    this.rangsDesTristusCreees.get(0).add(this.listNomTristus.get(indiceEncyclopedie));
                    this.rangsDesTristusCreees.get(1).add(this.listPtsHumeursTristus.get(indiceEncyclopedie));
                    this.rangsDesTristusCreees.get(2).add(this.listDernierJeuxMotsTristus.get(indiceEncyclopedie));
                    this.rangsDesTristusCreees.get(3).add(this.nombreDeJoutes());
                    this.rangsDesTristusCreees.get(4).add(this.getNbDefaite());
                    this.nom = (String)this.rangsDesTristusCreees.get(0).get(0);
                    this.ptsHumeurs = (Integer)this.rangsDesTristusCreees.get(1).get(0);
                    this.dernierJeuxMots = (TypeDeJeuDeMot) this.rangsDesTristusCreees.get(2).get(0);
                    this.nbAffrontements = (Integer)this.rangsDesTristusCreees.get(3).get(0);
                    this.nbDefaite = (Integer)this.rangsDesTristusCreees.get(4).get(0);
                    this.chefTristusCree = true;
                }
                else
                    throw new ExceptionChefDejaCreee();
            }
            /*
            On créé la créature, si celle-ci n'est pas déjà présente dans la liste des Tristus créé
            On ajoute ensuite cette créature dans la liste des Tristus créée pour bloquer la création d'une deuxième créature identique
             */
            else {
                /*
                On vérifie que le chef a bien été créé avant de créer les autres créatures
                 */
                if(this.chefTristusCree) {
                    if(this.rangsDesTristusCreees.get(0).contains(this.listNomTristus.get(indiceEncyclopedie))) {
                        if(this.rangsDesTristusCreees.get(1).contains(this.listPtsHumeursTristus.get(indiceEncyclopedie))) {
                            if(this.rangsDesTristusCreees.get(2).contains(this.listDernierJeuxMotsTristus.get(indiceEncyclopedie)))
                                throw new ExceptionCreatureDejaCreee();
                        }
                    }
                    else {
                        // Création d'un tristus
                        nbInstanceTristus++;
                        this.rangsDesTristusCreees.get(0).add(this.listNomTristus.get(indiceEncyclopedie));
                        this.rangsDesTristusCreees.get(1).add(this.listPtsHumeursTristus.get(indiceEncyclopedie));
                        this.rangsDesTristusCreees.get(2).add(this.listDernierJeuxMotsTristus.get(indiceEncyclopedie));
                        this.rangsDesTristusCreees.get(3).add(this.nombreDeJoutes());
                        this.rangsDesTristusCreees.get(4).add(this.getNbDefaite());
                        this.nom = (String)this.rangsDesTristusCreees.get(0).get(nbInstanceTristus);
                        this.ptsHumeurs = (Integer)this.rangsDesTristusCreees.get(1).get(nbInstanceTristus);
                        this.dernierJeuxMots = (TypeDeJeuDeMot) this.rangsDesTristusCreees.get(2).get(nbInstanceTristus);
                        this.nbAffrontements = (Integer)this.rangsDesTristusCreees.get(3).get(nbInstanceTristus);
                        this.nbDefaite = (Integer)this.rangsDesTristusCreees.get(4).get(nbInstanceTristus);
                    }
                }
                else {
                    throw new ExceptionChefNonCree();
                }
            }
        }
    }

    /**
     * Description textuelle d'une créature
     * @return chaine de caractères représentant la créature
     */
    @Override
    public String toString()
    {
        String result;
        if(this.estUnRigolus())
            result = "<" + this.nom() + " :) H=" + this.humeur() + " C=" + this.stringDernierJeuDeMot() + ">";
        else
            result = "<" + this.nom() + " :( H=" + this.humeur() + " C=" + this.stringDernierJeuDeMot() + ">";
        return result;
    }

    /**
     * Détermine le dernier jeu de mot pour la méthode toString(), en fonction de l'entier associé
     * Encapsulation en protected afin d'utiliser la méthode dans la classe CreatureEquanime
     * @return mot
     */
    protected String stringDernierJeuDeMot()
    {
        String mot = "";
        switch (this.dernierJeuDeMot().getIndice()) {
            case 0:
                mot = "PIERRE";
                break;
            case 1:
                mot = "FEUILLE";
                break;
            case 2:
                mot = "CISEAUX";
                break;
        }
        return mot;
    }

    /**
     * Détermine si la créature est un Rigolus
     * @return vrai si la créature est un rigolus, faux sinon
     */
    public boolean estUnRigolus()
    {
       return this.humeur() >= 0;
    }

    /**
     * Détermine si la créature est un Tristus
     * @return vrai si la créature est un tristus, faux sinon
     */
    public boolean estUnTristus()
    {
        return this.humeur() < 0;
    }

    /**
     * Récupère le nom de la créature courante
     * @return nom de la créature
     */
    public String nom()
    {
        return this.nom;
    }

    /**
     * Récupère le nombre de points d'humeurs de la créature courante
     * @return points d'humeurs
     */
    public int humeur()
    {
       return this.ptsHumeurs;
    }

    /**
     * Récupère le dernier jeux de mots de la créature courante
     * @return jeux de mots sous forme d'un entier
     */
    public TypeDeJeuDeMot dernierJeuDeMot()
    {
        return this.dernierJeuxMots;
    }

    /**
     * Récupère le nombre d'affrontements de la créature courante
     * @return nombre d'affrontements
     */
    public int nombreDeJoutes()
    {
        return this.nbAffrontements;
    }

    /**
     * Récupère le nombre de défaite de la créature courante
     * @return nombre de défaite
     */
    public int getNbDefaite()
    {
        return this.nbDefaite;
    }

    /**
     * Soumet deux créatures au jeu du PIERRE-FEUILLE-CISEAUX et détermine le gagnant et le perdant
     * @param creature
     * @throws ExceptionJouteImpossible
     */
    public void affronter(Creature creature) throws ExceptionJouteImpossible
    {
        if((this.estUnRigolus() && creature.estUnTristus()) || (this.estUnTristus() && creature.estUnRigolus())) {
            // Incrémentation du nombre d'affrontements
            this.nbAffrontements += 1;
            creature.nbAffrontements += 1;

            int status = this.compareTo(creature);
            switch (status) {
                // La créature courante a perdu
                case -1:
                    // Pour un rigolus, perte de points d'humeurs
                    if(this.estUnRigolus()) {
                        /*
                        Si le nombre de défaite est de 10, 20, 30 40, etc...
                        La pénalité est augmentée de 1
                         */
                        if(creature.nombreDeJoutes()%10 == 0) {
                            // La créature gagnante (en paramètre) possède modulo 10 joutes, et la créature perdante (courante) possède plus de 3 défaites
                            if(this.plusDeTroisDefaites())
                                this.ptsHumeurs = this.ptsHumeurs - 12;
                            else
                                this.ptsHumeurs = this.ptsHumeurs - 6;
                        }
                        else {
                            // La créature perdante (courante) possède plus de 3 défaites
                            if(this.plusDeTroisDefaites())
                                this.ptsHumeurs = this.ptsHumeurs - 10;
                            else
                                this.ptsHumeurs = this.ptsHumeurs - 5;
                        }
                    }
                    // Pour un Tristus, gains de points d'humeurs
                    else {
                        if(creature.nombreDeJoutes()%10 == 0) {
                            if(this.plusDeTroisDefaites())
                                this.ptsHumeurs = this.ptsHumeurs + 12;
                            else
                                this.ptsHumeurs = this.ptsHumeurs + 6;
                        }
                        else {
                            if(this.plusDeTroisDefaites())
                                this.ptsHumeurs = this.ptsHumeurs +10;
                            else
                                this.ptsHumeurs = this.ptsHumeurs + 5;
                        }
                    }
                    this.nbDefaite += 1;
                    // Ajout de la créature perdante dans la table de hachage des perdants
                    this.vaincus.put(this, this.getNbDefaite());
                    break;
                // Egalité entre les deux créatures
                case 0:
                    break;
                // La créature courante a gagnée
                case 1:
                    // Ppur un Rigolus, perte de points d'humeurs
                    if(this.estUnRigolus()) {
                        if(this.nombreDeJoutes()%10 == 0) {
                            // La créature gagnante (courante) possède un nombre de jouts modulo 10, et la créature perdante (en paramètre) a perdu plus de 3 fois
                            if(creature.plusDeTroisDefaites())
                                creature.ptsHumeurs = creature.ptsHumeurs - 12;
                            else
                                creature.ptsHumeurs = creature.ptsHumeurs - 6;
                        }
                        else {
                            // La créature perdante (en paramètre) possède plus de 3 défaites
                            if(creature.plusDeTroisDefaites())
                                creature.ptsHumeurs = creature.ptsHumeurs - 10;
                            else
                                creature.ptsHumeurs = creature.ptsHumeurs - 5;
                        }
                    }
                    // Pour un Tristus, gains de points d'humeurs
                    else {
                        if(this.nombreDeJoutes()%10 == 0) {
                            if(creature.plusDeTroisDefaites())
                                creature.ptsHumeurs = creature.ptsHumeurs + 12;
                            else
                                creature.ptsHumeurs = creature.ptsHumeurs + 6;
                        }
                        else {
                            if(creature.plusDeTroisDefaites())
                                creature.ptsHumeurs = creature.ptsHumeurs + 10;
                            else
                                creature.ptsHumeurs = creature.ptsHumeurs + 5;
                        }
                    }
                    creature.nbDefaite += 1;
                    // Ajout de la créature perdante dans la table de hachage des perdants
                    this.vaincus.put(creature, creature.getNbDefaite());
                    break;
            }
        }
        else
            throw new ExceptionJouteImpossible();
    }

    /**
     * Permet de connaître les créatures vaincues ainsi que son nombre de défaites
     * @return table de hachage (Map) de la forme (vaincu, nombre de fois vaincu)
     */
    public HashMap<Creature, Integer> tableauDeChasse()
    {
        return this.vaincus;
    }

    /**
     * Permet de connaître les vaincues de la méthode {@link Creature#affronter(Creature)}
     * Pour éviter la redondance de code, on extrait juste les clés de la HashMap ci-dessus
     * @return ensemble des créatures vaincues
     */
    public HashSet<Creature> vaincus()
    {
        this.creatureVaincus.addAll(this.tableauDeChasse().keySet());
        return this.creatureVaincus;
    }

    /**
     * Permet de comparer le dernier jeux de mots entre la créature courante et la créature en paramètre et renvoie un statut gagnant ou perdant
     * gagnant = 1 / perdant = -1
     * Méthode utilisé dans {@link Creature#affronter(Creature)} pour déterminer le gagnant et le perdant
     * @param creature
     * @return statut de la créature courante
     */
    @Override
    public int compareTo(Creature creature) {
        switch (this.dernierJeuDeMot()) {
            // PIERRE
            case PIERRE:
                return statutPoints(creature, creature.dernierJeuDeMot().getIndice() == feuille.getIndice(), -1, 1);
            // FEUILLE
            case FEUILLE:
                return statutPoints(creature, creature.dernierJeuDeMot().getIndice() == pierre.getIndice(), 1, -1);
            // CISEAUX
            case CISEAUX:
                if(creature.dernierJeuDeMot().getIndice() == pierre.getIndice())
                    return -1;
                else if(creature.dernierJeuDeMot().getIndice() == feuille.getIndice())
                    return 1;
                else
                    return 0;
        }
        return 0;
    }

    /**
     * Evite la redondance de code afin de déterminer la victoire ou la défaite au jeu de la créature courante
     * Méthode utilisé dans {@link Creature#compareTo(Creature)}
     * @param creature
     * @param b
     * @param indice entier
     * @param indice2 entier
     * @return statut gagnant ou perdant
     */
    private int statutPoints(Creature creature, boolean b, int indice, int indice2)
    {
        if(b)
            return indice;
        else if(creature.dernierJeuDeMot().getIndice() == ciseaux.getIndice())
            return indice2;
        else
            return 0;
    }

    /**
     * Test si la créature courante possède un nombre de défaites > 3
     * @return vrai ou faux
     */
    private boolean plusDeTroisDefaites()
    {
        return this.getNbDefaite() > 3;
    }

    /**
     * Permet de ne créer qu'une seule planète lors de l'exécution du programme
     * @return la planète
     */
    public static Planete laPlanete()
    {
        if(Planete.laPlanete == null)
            Planete.laPlanete = new Planete("Rigolus-Tristus");
        return Planete.laPlanete;
    }

    /**
     * Permet de connaitre le nombre de victoires de la créature courante
     * @return nombre de victoire
     */
    public int nombreDeVictoires()
    {
        return this.nombreDeJoutes() - this.getNbDefaite();
    }

    /**
     * Récupère le nombre de créature créées nécessaire à la classe Planete
     * @return nombre de créature créées
     */
    public static int getNbHabitants()
    {
        return nbInstanceRigolus + nbInstanceTristus;
    }

    /**
     * Récupère la liste des Rigolus créés
     * @return liste des Rigolus
     */
    public ArrayList<Creature> getRangsDesRigolusCreees()
    {
        ArrayList<Creature> rigolus = new ArrayList<Creature>();
        for(Object o : this.rangsDesRigolusCreees)
            rigolus.add((Creature) o);
        return rigolus;
    }

    /**
     * Récupère la liste des Tristus créés
     * @return liste des Tristus
     */
    public ArrayList<Creature> getRangsDesTristusCreees()
    {
        ArrayList<Creature> tristus = new ArrayList<Creature>();
        for(Object o : this.rangsDesTristusCreees)
            tristus.add((Creature)o);
        return tristus;
    }

    public Set<Creature> vaincusTelsQue(Filtre<Creature> toujoursVrai)
    {
        return null;
    }
}
