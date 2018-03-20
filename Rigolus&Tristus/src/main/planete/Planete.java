package main.planete;

import exception.PlaneteDeserteException;
import main.creatures.ComparateurDeCreatureParPointsHumeurs;
import main.creatures.Creature;

import java.util.*;

public class Planete
{
    private String nomPlanete;
    private Creature creature;

    // Variable static permettant de savoir si une planète est déjà créée ou pas
    // Obligation de mettre la variable en public, pour y avoir accès directement dans la classe Creature
    public static Planete laPlanete = null;

    /**
     * Créé une planète avec un nom en paramètre
     * @param nomPlanete nom de la planète
     */
    public Planete(String nomPlanete)
    {
        this.nomPlanete = nomPlanete;
    }

    /**
     * Description de la planète
     * @return chaine de caractères représentant la planète
     */
    @Override
    public String toString()
    {
        String result = null;
        if(Creature.getNbHabitants() == 0)
            result = this.nomPlanete + " population=0";
        else {
            try {
                result = this.nomPlanete + " population=" + Creature.getNbHabitants() + " IBB=" + this.indiceDeBonheurBrut();
            } catch (PlaneteDeserteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Calcul de l'indice de bonheur brut de la liste des créatures
     * Ce calcul se base sur la somme de tout les points d'humeur / nombre de créatures
     * @return indice de bonheur brut
     * @throws PlaneteDeserteException
     */
    public float indiceDeBonheurBrut() throws PlaneteDeserteException
    {
        if(creature.getRangsDesRigolusCreees().isEmpty() && creature.getRangsDesTristusCreees().isEmpty())
            throw new PlaneteDeserteException();

        float result = 0.0F;
        Iterator<Creature> iteratorRigolus = this.iterateurDesRigolus();
        Iterator<Creature> iteratorTristus = this.iterateurDesTristus();
        while (iteratorRigolus.hasNext()) {
            result += iteratorRigolus.next().humeur();
        }

        while (iteratorTristus.hasNext()) {
            result += iteratorTristus.next().humeur();
        }

        return result / (creature.getRangsDesTristusCreees().size() + creature.getRangsDesRigolusCreees().size());
    }

    /**
     * Permet de créer un pointeur sur les éléments de la liste des Rigolus
     * @return iterateur
     */
    public Iterator<Creature> iterateurDesRigolus()
    {
        return this.creature.getRangsDesRigolusCreees().iterator();
    }

    /**
     * Permet de créer un pointeur sur les éléments de la liste des Tristus
     * @return iterateur
     */
    public Iterator<Creature> iterateurDesTristus()
    {
        return this.creature.getRangsDesTristusCreees().iterator();
    }

    /**
     * Permet de créer une liste de créatures contenant uniquement des Rigolus dont les points d'humeurs sont inférieur à i
     * @param i borne supérieur des points d'humeurs
     * @return liste des Rigolus respectant la consigne
     */
    public List<Creature> rigolusEnDangerEnDessousDe(int i)
    {
        List<Creature> listRigolusEnDanger = fusionner();
        for(Creature creature : listRigolusEnDanger) {
            if(creature.estUnRigolus() && creature.humeur() < i)
                listRigolusEnDanger.add(creature);
        }
        return listRigolusEnDanger;
    }

    /**
     * Permet de créer une liste de créatures triées par ordre d'humeur croissante
     * @return nouvelle liste triée de créatures
     */
    public List<String> listeLesNomsDesCreaturesParHumeurCroissante()
    {
        List<Creature> listTemp = fusionner();
        List<String> listCreaturesTriees = new ArrayList<String>();
        listTemp.sort(new ComparateurDeCreatureParPointsHumeurs());

        for(Creature creature : listTemp)
            listCreaturesTriees.add(creature.nom());
        return listCreaturesTriees;
    }

    /**
     * Permet de stocker dans une liste les noms des créatures ayant le nombre de victoires le plus élevés
     * Utilise la méthode {@link Planete#creatureLaPlusForte()} pour repérer d'autres créatures ayant le même nombre de victoire que la créature la plus forte
     * @return liste de noms des plus forts
     */
    public List<Creature> listeLesNomsDesCreaturesLesPlusFortes()
    {
        List<Creature> listCreature = fusionner();
        List<Creature> listCreaturesKings = new ArrayList<Creature>();
        Creature creatureKing = this.creatureLaPlusForte();

        for(Creature creature : listCreature) {
            if(creature.nombreDeVictoires() == creatureKing.nombreDeVictoires())
                listCreaturesKings.add(creature);
        }

        listCreaturesKings.add(creatureKing);

        return listCreaturesKings;
    }

    /**
     * Permet de déterminer la créature la plus forte, en fonction de son nombre de victoire
     * @return créature la plus forte
     */
    private Creature creatureLaPlusForte()
    {
        List<Creature> listCreature = fusionner();
        Creature creatureKing = listCreature.get(0);

        for(Creature creature : listCreature) {
            if(creature.nombreDeVictoires() > creatureKing.nombreDeVictoires())
                creatureKing = creature;
        }

        return creatureKing;
    }

    /**
     * Permet de fusionner la liste des Tristus et la liste des Rigolus dans une nouvelle liste
     * @return nouvelle liste composée des Tristus et Rigolus
     */
    private List<Creature> fusionner()
    {
        List<Creature> listRigolusTristus = new ArrayList<Creature>();
        while (this.iterateurDesRigolus().hasNext() && this.iterateurDesTristus().hasNext()) {
            listRigolusTristus.add(iterateurDesRigolus().next());
            listRigolusTristus.add(iterateurDesTristus().next());
        }

        while(this.iterateurDesRigolus().hasNext()) {
            listRigolusTristus.add(iterateurDesRigolus().next());
        }

        while(this.iterateurDesTristus().hasNext()) {
            listRigolusTristus.add(iterateurDesTristus().next());
        }
        return listRigolusTristus;
    }
}
