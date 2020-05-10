import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Frame extends JFrame implements ActionListener
{
    private Biblioteca b;
    private JLabel titolo;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JPanel p7;
    private JPanel p8;
    private JButton ins;
    private JButton nuovoGen;
    private JButton stmpTit;
    private JButton canc;
    private JButton salva;
    private JComboBox gen;
    private JTextArea elenco;
    private JScrollPane sp;
    public Frame(Biblioteca b) throws Exception
    {
        this.setTitle("Biblioteca");
        
        titolo = new JLabel("Biblioteca", JLabel.CENTER);
        titolo.setFont(new Font("Verdana", Font.PLAIN, 40));
        this.b = b;
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        ins = new JButton("Inserisci libro");
        nuovoGen = new JButton("Inserisci nuovo genere");
        stmpTit = new JButton("Stampa per titolo");
        canc = new JButton("Cancella");
        salva = new JButton("Salva biblioteca");
        gen = new JComboBox();
        elenco = new JTextArea();
        sp = new JScrollPane(elenco);
        
        gen.addItem("Tutti");
        letturaGeneri();
        
        this.setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(2,0));
        p2.setLayout(new GridLayout(2,2));
        p3.add(ins);
        p4.add(stmpTit);
        p5.add(nuovoGen);
        p6.setLayout(new FlowLayout(FlowLayout.CENTER));
        p6.add(new JLabel("Stampa per genere: "));
        p6.add(gen);
        p2.add(p3);
        p2.add(p4);
        p2.add(p5);
        p2.add(p6);
        elenco.setEditable(false);
        p1.add(p2);
        p1.add(sp);
        p7.setLayout(new GridLayout(0,2,5,5));
        p7.add(canc);
        p7.add(salva);
        p8.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p8.add(p7);
        
        ins.addActionListener(this);
        gen.addItemListener(new StampaGenere());
        nuovoGen.addActionListener(this);
        stmpTit.addActionListener(new StampaTitolo());
        canc.addActionListener(new Cancella());
        salva.addActionListener(new Salvataggio());
        
        
        this.add(titolo, BorderLayout.NORTH);
        this.add(p1, BorderLayout.CENTER);
        this.add(p8, BorderLayout.SOUTH);        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450,350);
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
        if(e.getSource() == ins)
        {
            this.setVisible(false);
            Inserimento i = new Inserimento(b);
        }
        if(e.getSource() == nuovoGen){
            this.setVisible(false);
            InserimentoGenere ig = new InserimentoGenere(b);
        }
    }
    class Salvataggio implements ActionListener{
        public void actionPerformed(ActionEvent e){
            b.salva();
        }
    }
    class StampaGenere implements ItemListener{
        public void itemStateChanged(ItemEvent e){
                b.stampa((String)gen.getSelectedItem(), elenco);
        }
    }
    class StampaTitolo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            b.stampaPerTitolo(elenco);
        }
    }
    class Cancella implements ActionListener{
        public void actionPerformed(ActionEvent e){
            elenco.setText("");
        }
    }
}

