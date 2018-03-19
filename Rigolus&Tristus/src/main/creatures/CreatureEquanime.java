package main.creatures;

import exception.ExceptionChefDejaCreee;
import exception.ExceptionChefNonCree;
import exception.ExceptionCreatureDejaCreee;

public class CreatureEquanime extends Creature
{
    /**
     * Indique l'encyclopédie à utiliser selon le paramètre b
     * @param indiceEncyclopedie rang dans l'encyclopédie de la créature à créer
     * @param b Indique l'encyclopédie a utiliser pour créer la créature
     * @throws ExceptionCreatureDejaCreee
     * @throws ExceptionChefNonCree
     * @throws ExceptionChefDejaCreee
     */
    public CreatureEquanime(int indiceEncyclopedie, boolean b) throws ExceptionCreatureDejaCreee, ExceptionChefNonCree, ExceptionChefDejaCreee
    {
        super(indiceEncyclopedie, b);
    }

    /**
     * Description textuelle d'une créature équanime
     * @return chaine de caractères représentant la créature
     */
    @Override
    public String toString()
    {
        String result;
        if(this.estUnRigolus())
            result = "<" + this.nom() + " :D H=" + this.humeur() + " C=" + this.stringDernierJeuDeMot() + ">";
        else
            result = "<" + this.nom() + " :[ H=" + this.humeur() + " C=" + this.stringDernierJeuDeMot() + ">";
        return result;
    }
}
