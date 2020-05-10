import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Inserimento extends JFrame implements ActionListener
{
    private ArrayList<String> autori;
    private Biblioteca b;
    private JLabel titolo;
    private JLabel errore;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JPanel p7;
    private JPanel p8;
    private JPanel p9;
    private JPanel p10;
    private JPanel p11;
    private JPanel p12;
    private JPanel p13;
    private JPanel p14;
    private JTextField tit;
    private JComboBox gen;
    private JTextField pag;
    private JTextField aut;
    private JButton invAut;
    private JButton invio;
    private JButton indietro;
    private JTextArea elencoAut;
    private JScrollPane sp;
    public Inserimento(Biblioteca b)
    {
        this.b = b;
        
        this.setTitle("Biblioteca - inserimento libro");
        
        autori = new ArrayList();
        titolo = new JLabel("Nuovo Libro", JLabel.CENTER);
        errore = new JLabel("");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        p9 = new JPanel();
        p10 = new JPanel();
        p11 = new JPanel();
        p12 = new JPanel();
        p13 = new JPanel();
        p14 = new JPanel();
        tit = new JTextField(15);
        gen = new JComboBox();
        pag = new JTextField(15);
        aut = new JTextField(15);
        invAut = new JButton("Inserisci");
        invio = new JButton("Inserisci libro");
        indietro = new JButton("Indietro");
        elencoAut = new JTextArea();
        sp = new JScrollPane(elencoAut);
        
        letturaGeneri();
        
        titolo.setFont(new Font("Verdana", Font.PLAIN, 40));
        p1.setLayout(new GridLayout(5,3));
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p3.setLayout(new FlowLayout(FlowLayout.CENTER));
        p4.setLayout(new FlowLayout(FlowLayout.CENTER));
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        p6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p7.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p8.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p9.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p10.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p11.setLayout(new FlowLayout(FlowLayout.CENTER));
        p12.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p13.setLayout(new GridLayout(0,2,5,5));
        p14.setLayout(new FlowLayout(FlowLayout.RIGHT));
        elencoAut.setEditable(false);
        
        p6.add(new JLabel("Titolo:      "));
        p1.add(p6);
        p2.add(tit);
        p1.add(p2);
        p7.add(new JLabel(""));
        p1.add(p7);
        p8.add(new JLabel("Genere:      "));
        p1.add(p8);
        p3.add(gen);
        p1.add(p3);
        p1.add(new JLabel(""));
        p9.add(new JLabel("N° pagine:       "));
        p1.add(p9);
        p4.add(pag);
        p1.add(p4);
        p1.add(new JLabel(""));
        p10.add(new JLabel("Aggiungi autore:     "));
        p1.add(p10);
        p5.add(aut);
        p1.add(p5);
        p11.add(invAut);
        p1.add(p11);
        p12.add(new JLabel("Autori:     "));
        p1.add(p12);
        p1.add(sp);
        p13.add(indietro);
        p13.add(invio);
        p14.add(errore);
        p14.add(p13);
        
        invAut.addActionListener(this);
        invio.addActionListener(this);
        indietro.addActionListener(this);
        
        this.add(titolo, BorderLayout.NORTH);
        this.add(p1, BorderLayout.CENTER);
        this.add(p14, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void letturaGeneri(){
        try{
            FileInputStream f = new FileInputStream("Generi.dat");
            DataInputStream dis = new DataInputStream(f);
            
            String s = dis.readUTF();
            while(s != null){
                gen.addItem(s);
                s = dis.readUTF();
            }
            
            f.close();
        }
        catch(FileNotFoundException fnfe){
            try{
                FileOutputStream f = new FileOutputStream("Generi.dat");
                DataOutputStream dos = new DataOutputStream(f);
                
                dos.writeUTF("Biografia");
                dos.writeUTF("Romanzo");
                dos.writeUTF("Fantasy");
                dos.writeUTF("Giallo");
                
                dos.flush();
                f.close();
                
                gen.addItem("Biografia");
                gen.addItem("Romanzo");
                gen.addItem("Fantasy");
                gen.addItem("Giallo");
            }
            catch(IOException ioe) {}
        }
        catch(IOException ioe) {}
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == indietro){
            this.setVisible(false);
            try{
                Frame f = new Frame(b);
            }
            catch(Exception exc){}
        }
        if(e.getSource() == invAut)
        {
            if(!aut.getText().equals(""))
            {
                autori.add(aut.getText());
                elencoAut.append(aut.getText() + "\n");
                aut.setText("");
                aut.requestFocus();
            }
        }
        else{
            errore.setText("");
            boolean vuoto = false;
            if(tit.getText().equals(""))
                vuoto = true;
            if(pag.getText().equals(""))
                vuoto = true;
            if(elencoAut.getText().equals(""))
                vuoto = true;
            if(vuoto)
                errore.setText("ERRORE: riempire tutti i campi.");
            int npag;
            try{
                npag = Integer.parseInt(pag.getText());
            }
            catch(NumberFormatException nfe){
                npag = 0;
            }
            if(npag <= 0)
                errore.setText("ERRORE: in 'N° pag' inserire un num>0.");
            if(errore.getText().equals(""))
            {
                Libro l = new Libro(b.getSize(),tit.getText(),npag,(String)gen.getSelectedItem());
                for(int i=0;i<autori.size();i++)
                    l.addAutore(autori.get(i));
                b.add(l);
                errore.setText("Libro inserito correttamente.");
            }
            tit.setText("");
            gen.setSelectedItem((String)"Biografia");
            pag.setText("");
            aut.setText("");
            elencoAut.setText("");
        }
    }
}