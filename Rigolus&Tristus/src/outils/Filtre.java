package outils;

public interface Filtre<E>
{
    boolean accepte(E element);
}
