package exception;

public class ExceptionJouteImpossible extends Exception
{
    public ExceptionJouteImpossible()
    {
        super("La créature essaye de s'affronter elle-même");
    }
}
