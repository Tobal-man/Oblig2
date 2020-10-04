import java.util.*;

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

  private Node<T> finnNode(int indeks) {
    int k = antall / 2;
    int i = 0;

    Node<T> node = hode;

    if (indeks <= k)
      while (i++ != indeks) node = node.neste;
    else {
      node = hale;
      i = antall - 1;
      while (i-- != indeks) node = node.forrige;
    }

    return node;
  }

  public DobbeltLenketListe()
  {
    hode = hale = null;
    antall = 0;
    endringer = 0;
  }

  public DobbeltLenketListe(T[] a) {
    Objects.requireNonNull(a, "Tabellen a er null!");

    Node<T> node = null;

    for (T obj : a) {
      if (obj != null) {
        node = new Node<T>(obj, node, null);

        if (node.forrige != null)
          node.forrige.neste = node;
        else
          hode = node;

        antall++;
      }
    }

    hale = node;
  }

  private void fratilKontroll(int antall, int fra, int til) {
    if (fra < 0)
      throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");

    if (til > antall)
      throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");

    if (fra > til)
      throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
  }
  
  public Liste<T> subliste(int fra, int til)
  {
    fratilKontroll(antall, fra, til);

    DobbeltLenketListe<T> nyListe = new DobbeltLenketListe<>();
    Node<T> node = finnNode(fra);

    for(int i = fra; i < til; i++) {
      nyListe.leggInn(node.verdi);
      node = node.neste;
    }

    nyListe.endringer = 0;

    return nyListe;
  }

  @Override
  public int antall() {
    return antall;
  }


  @Override
  public boolean tom() {
    return antall == 0;
  }

  @Override
  public boolean leggInn(T verdi) {
    Objects.requireNonNull(verdi, "verdi == null!");

    if (tom())
      hode = hale = new Node<T>(verdi, null, null);
    else
      hale = hale.neste = new Node<T>(verdi, hale, null);
    antall++;
    endringer++;

    return true;
  }

  @Override
  public void leggInn(int indeks, T verdi) {
    Objects.requireNonNull(verdi, "verdi er null!");
    indeksKontroll(indeks, true);

    if(tom())
      hode = hale = new Node<T>(verdi, null, null);
    else if(indeks == 0)
      hode = hode.forrige = new Node<T>(verdi, null, hode);
    else if (indeks < antall) {
      Node<T> node = finnNode(indeks);

      Node<T> nyNode = new Node<T>(verdi, node.forrige, node);
      node.forrige.neste = nyNode;
      node.forrige = nyNode;
    } else
      hale = hale.neste = new Node<T>(verdi, hale, null);

    antall++;
    endringer++;
  }

  @Override
  public boolean inneholder(T verdi) {
    return indeksTil(verdi) != -1;
  }

  @Override
  public T hent(int indeks) {
    indeksKontroll(indeks, false);

    return finnNode(indeks).verdi;
  }

  @Override
  public int indeksTil(T verdi) {
    if (verdi == null) return -1;

    Node<T> node = hode;
    int i = 0;

    while (i < antall && !node.verdi.equals(verdi)) {
      node = node.neste;
      i++;
    }

    return (i == antall ? -1 : i);
  }

  @Override
  public T oppdater(int indeks, T nyverdi) {
    indeksKontroll(indeks, false);
    Objects.requireNonNull(nyverdi, "nyverdi er null!");

    Node<T> node = finnNode(indeks);
    T gammelVerdi = node.verdi;
    node.verdi = nyverdi;

    endringer++;

    return gammelVerdi;
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
  public String toString() {
    StringJoiner s = new StringJoiner(", ", "[", "]");

    Node<T> node = hode;

    while (node != null) {
      s.add(node.verdi.toString());
      node = node.neste;
    }

    return s.toString();
  }

  public String omvendtString() {
    StringJoiner s = new StringJoiner(", ", "[", "]");

    Node<T> node = hale;

    while (node != null) {
      s.add(node.verdi.toString());
      node = node.forrige;
    }

    return s.toString();
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
