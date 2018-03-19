package main.planete;

import exception.PlaneteDeserteException;
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
     * @throws PlaneteDeserteException
     */
    @Override
    public String toString() throws PlaneteDeserteException
    {
        String result;
        if(Creature.getNbHabitants() == 0)
            result = this.nomPlanete + " population=0";
        else
            result = this.nomPlanete + " population=" + Creature.getNbHabitants() + " IBB=" + this.indiceDeBonheurBrut();
        return result;
    }

    /**
     * Calcul de l'indice de bonheur brut de la liste des créatures
     * Ce calcul se base sur la somme de tout les points d'humeur / nombre de créatures
     * @return indice de bonheur brut
     * @throws PlaneteDeserteException
     */
    public float indiceDeBonheurBrut() throws PlaneteDeserteException {
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

    public Iterator<Creature> iterateurDesRigolus()
    {
        return this.creature.getRangsDesRigolusCreees().iterator();
    }

    public Iterator<Creature> iterateurDesTristus()
    {
        return this.creature.getRangsDesTristusCreees().iterator();
    }

    public Map<Object, Object> rigolusEnDangerEnDessousDe(int i)
    {

    }

    public List<String> listeLesNomsDesCreaturesParHumeurCroissante()
    {

    }

    public List<String> listeLesNomsDesCreaturesLesPlusFortes()
    {

    }
}
