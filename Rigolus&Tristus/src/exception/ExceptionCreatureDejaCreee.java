package exception;

public class ExceptionCreatureDejaCreee extends Exception
{
    public ExceptionCreatureDejaCreee()
    {
        super("Création de la créature impossible ! Elle existe déjà");
    }
}
