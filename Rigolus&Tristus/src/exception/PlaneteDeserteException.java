package exception;

public class PlaneteDeserteException extends Exception
{
    public PlaneteDeserteException()
    {
        super("La planète ne possède aucun habitant");
    }
}
