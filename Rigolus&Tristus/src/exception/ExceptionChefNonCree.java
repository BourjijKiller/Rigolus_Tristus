package exception;

public class ExceptionChefNonCree extends Exception
{
    public ExceptionChefNonCree()
    {
        super("Le chef doit d'abord être créé avant toutes autres créatures");
    }
}
