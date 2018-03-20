package TypeJeuMot;

public enum TypeDeJeuDeMot
{
    PIERRE(0), FEUILLE(1), CISEAUX(2);

    private int indice;

    /**
     * Constructeur de l'énumération
     * @param indice
     */
    TypeDeJeuDeMot(int indice)
    {
        this.indice = indice;
    }

    /**
     * Récupère l'indice associé à l'énumération
     * @return numéro de l'énumération
     */
    public int getIndice()
    {
        return this.indice;
    }
}
