import java.util.*;
import java.io.*;
public class Libro implements Comparable, Serializable
{
    private ArrayList<String> autori;
    private int cod;
    private String titolo;
    private int npagine;
    private String genere;
    public Libro(int k, String titolo, int npagine, String genere)
    {
        autori = new ArrayList();
        this.cod = k;
        this.titolo = titolo;
        this.npagine = npagine;
        this.genere = genere;
    }
    public String getTitolo()
    {
        return titolo;
    }
    public int getCod()
    {
        return cod;
    }
    public String getGenere()
    {
        return genere;
    }
    public void addAutore(String autore)
    {
        autori.add(autore);
    }
    public int compareTo(Object l)
    {
        return cod - ((Libro)l).getCod();
    }
    public String toString()
    {
        String s = autori + " - " +titolo + " ("+genere+" - p. "+npagine+")";
        return s;
    }
}

