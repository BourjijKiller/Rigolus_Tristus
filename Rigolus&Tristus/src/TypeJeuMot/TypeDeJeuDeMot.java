package TypeJeuMot;

public enum TypeDeJeuDeMot
{
    PIERRE(0), FEUILLE(1), CISEAUX(2);

    private int indice;

    TypeDeJeuDeMot(int indice)
    {
        this.indice = indice;
    }

    public int getIndice()
    {
        return this.indice;
    }
}
