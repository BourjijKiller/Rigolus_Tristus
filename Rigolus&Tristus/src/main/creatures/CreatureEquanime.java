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
}
