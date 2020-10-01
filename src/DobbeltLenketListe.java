
/////////// DobbeltLenketListe ////////////////////////////////////

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    // instansvariabler
    private T verdi;
    private Node<T> forrige, neste;

    private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
    {
      this.verdi = verdi;
      this.forrige = forrige;
      this.neste = neste;
    }

    protected Node(T verdi)  // konstruktør
    {
      this(verdi, null, null);
    }

  } // Node

  // instansvariabler
  private Node<T> hode;          // peker til den første i listen
  private Node<T> hale;          // peker til den siste i listen
  private int antall;            // antall noder i listen
  private int endringer;   // antall endringer i listen

  // hjelpemetode
  private Node<T> finnNode(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  // konstruktør
  public DobbeltLenketListe()
  {
    hode = hale = null;
    antall = 0;
    endringer = 0;
  }

  // konstruktør
  public DobbeltLenketListe(T[] a)
  {
    if(a == null)
      throw new NullPointerException("Tabellen a er null");

    if(a.length == 0){
      return;
    }

    for(int i = 0; i < a.length; i++){
      T currentValue = a[i];

      if(currentValue == null)
        continue;

      if(hode == null){
        Node<T> firstNode = new Node<T>(currentValue, null, null);
        hode = new Node<T>(null, null, firstNode);
        hale = new Node<T>(null, firstNode, null);
        antall++;
        endringer++;
      }else{
        Node<T> lastNode = hale.forrige;
        lastNode.neste = new Node<T>(currentValue, lastNode, null);
        hale.forrige = lastNode.neste;
        antall++;
        endringer++;
      }

    }
  }
  // subliste
  public Liste<T> subliste(int fra, int til)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public int antall()
  {
    return antall;
  }

  @Override
  public boolean tom()
  {
    if(antall == 0)
      return true;
    else
      return false;
  }
  @Override
  public boolean leggInn(T verdi)
  {
    Objects.requireNonNull(verdi);

    Node<T> newNode = new Node<T>(verdi, null, null);

    if(antall == 0){
      hode = new Node<T>(null, null, newNode);
      hale = new Node<T>(null, newNode, null);
    }else{
      Node<T> lastNode = hale.forrige;

      lastNode.neste = newNode;
      hale.forrige = newNode;
    }

    antall++;
    endringer++;

    return true;

  }

  @Override
  public void leggInn(int indeks, T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public boolean inneholder(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T hent(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public int indeksTil(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T oppdater(int indeks, T nyverdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T fjern(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public String toString()
  {
    if(antall == 0)
      return "[]";

    StringBuilder strBuilder = new StringBuilder("[");
    Node<T> currentNode = hode.neste;

    strBuilder.append(currentNode.verdi.toString());
    currentNode = currentNode.neste;

    for(int i = 1; i < antall; i++){
      strBuilder.append(", " + currentNode.verdi.toString());

      currentNode = currentNode.neste;
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }

  public String omvendtString()
  {
    if(antall == 0)
      return "[]";

    StringBuilder strBuilder = new StringBuilder("[");
    Node<T> currentNode = hale.forrige;

    strBuilder.append(currentNode.verdi.toString());
    currentNode = currentNode.forrige;

    for(int i = 1; i < antall; i++){
      strBuilder.append(", " + currentNode.verdi.toString());

      currentNode = currentNode.forrige;
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }

  public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public Iterator<T> iterator()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  public Iterator<T> iterator(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  private class DobbeltLenketListeIterator implements Iterator<T>
  {
    private Node<T> denne;
    private boolean fjernOK;
    private int iteratorendringer;

    private DobbeltLenketListeIterator()
    {
      denne = hode;     // denne starter på den første i listen
      fjernOK = false;  // blir sann når next() kalles
      iteratorendringer = endringer;  // teller endringer
    }

    private DobbeltLenketListeIterator(int indeks)
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public boolean hasNext()
    {
      return denne != null;  // denne koden skal ikke endres!
    }

    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

  } // DobbeltLenketListeIterator  

} // DobbeltLenketListe  

