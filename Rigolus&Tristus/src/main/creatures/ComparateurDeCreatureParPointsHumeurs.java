package main.creatures;

import java.util.Comparator;

public class ComparateurDeCreatureParPointsHumeurs implements Comparator<Creature>
{
    public ComparateurDeCreatureParPointsHumeurs()
    {

    }

    /**
     * Compare les points d'humeurs de deux créatures
     * On trie par ordre croissant
     * @param creature1
     * @param creature2
     * @return la différence entre les points d'humeurs
     */
    @Override
    public int compare(Creature creature1, Creature creature2)
    {
        return creature1.humeur() - creature2.humeur();
    }
}
