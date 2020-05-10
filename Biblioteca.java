import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Biblioteca
{
    private LinkedList<Libro> biblio = new LinkedList();
    public Biblioteca() throws Exception
    {
        carica();
    }
    private void caricaElenco() throws Exception
    {
        File f = new File("ElencoLibri.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int n;
        n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++)
        {
            int npagine,nautori;
            String titolo, genere;
            titolo = br.readLine();
            genere = br.readLine();
            npagine = Integer.parseInt(br.readLine());
            nautori = Integer.parseInt(br.readLine());
            Libro l = new Libro(getSize(),titolo,npagine,genere);
            for(int j=0;j<nautori;j++)
                l.addAutore(br.readLine());
            biblio.add(l);
        }
    }
    public int getSize()
    {
        return biblio.size();
    }

    public void add(Libro l)
    {
        biblio.add(l);
    }
    public void stampa(String genere, JTextArea t)
    {
        t.setText("");
        if(genere.equals("Tutti"))for(Object b : biblio)
            t.append(((Libro)b).toString() + "\n");
            else
                for(Object b : biblio)
                    if(((Libro)b).getGenere().equals(genere))
                        t.append(b.toString() + "\n");
    }
    public void stampaPerTitolo(JTextArea t)
    {
        t.setText("");
        biblio.sort(new PerTitolo()); 
        for (Object b : biblio)
            t.append(((Libro)b).toString() + "\n");
            Collections.sort(biblio);
    }
    public void salva()
    {
        try{
            FileOutputStream f = new FileOutputStream("Libri.dat");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            
            for(Object b:biblio)
                oos.writeObject(b);
            oos.flush();
            f.close();
        }
        catch(FileNotFoundException fnfe) {}
        catch(IOException ioe) {}
    }
    public void carica()
    {
        try{
            FileInputStream f = new FileInputStream("Libri.dat");
            ObjectInputStream ois = new ObjectInputStream(f);
            
            Libro l = (Libro)ois.readObject();
            while(l!=null)
            {
                biblio.add(l);
                l = (Libro)ois.readObject();
            }
            f.close();
        }
        catch(FileNotFoundException fnfe) {}
        catch(ClassNotFoundException cnfe) {}
        catch(IOException ioe) {}
    }
    public void trasforma() throws Exception
    {
        FileOutputStream f = new FileOutputStream("Libri.dat");
        ObjectOutputStream oos = new ObjectOutputStream(f);
        BufferedReader br = new BufferedReader(new FileReader(new File("ElencoLibri.txt")));
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++)
        {
            String titolo, genere;
            int npagine, nautori;
            titolo = br.readLine();
            genere = br.readLine();
            npagine = Integer.parseInt(br.readLine());
            nautori = Integer.parseInt(br.readLine());
            Libro l = new Libro(i+1,titolo,npagine,genere);
            for(int j=0;j<nautori;j++)
                l.addAutore(br.readLine());
            oos.writeObject(l);
        }
        f.flush();
        f.close();
    }
}

