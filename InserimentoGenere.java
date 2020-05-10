    import java.io.*;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    public class InserimentoGenere extends JFrame implements ActionListener
    {
        private Biblioteca b;
        private JPanel p1;
        private JPanel p2;
        private JPanel p3;
        private JPanel p4;
        private JPanel p5;
        private JLabel titolo;
        private JLabel errore;
        private JTextField genere;
        private JButton ins;
        private JButton indietro;
        public InserimentoGenere(Biblioteca b)
        {
            this.b = b;
            
            this.setTitle("Biblioteca - Inserimento genere");
            
            titolo = new JLabel("Inserimento genere", JLabel.CENTER);
            titolo.setFont(new Font("Verdana", Font.PLAIN, 20));
            
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            ins = new JButton("Inserisci");
            indietro = new JButton("Indietro");
            errore= new JLabel("", JLabel.CENTER);
            genere = new JTextField(10);
            
            p1.setLayout(new GridLayout(2,0));
            p2.setLayout(new FlowLayout(FlowLayout.CENTER));
            p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
            p4.setLayout(new GridLayout(0,2,5,5));
            p5.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            p2.add(new JLabel("Genere: "));
            p2.add(genere);
            p1.add(p2);
            p1.add(errore);
            p4.add(ins);
            p4.add(indietro);
            p3.add(p4);
            p5.add(p1);
            
            ins.addActionListener(this);
            indietro.addActionListener(this);
            
            this.add(titolo, BorderLayout.NORTH);
            this.add(p5, BorderLayout.CENTER);
            this.add(p3, BorderLayout.SOUTH);
            
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setSize(400,200);
            this.setResizable(false);
            this.setVisible(true);
        }
        public void scritturaGeneri(){
            
        }
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == ins){
                errore.setText("");
                if(genere.getText().equals(""))
                    errore.setText("ERRORE: inserire il genere.");
                else{
                    boolean copia = false;
                    try{
                        FileInputStream f = new FileInputStream("Generi.dat");
                        DataInputStream dis = new DataInputStream(f);
                        
                        String s = dis.readUTF();
                        while(s != null){
                            if(s.equals(genere.getText()))
                                copia = true;
                            s = dis.readUTF();
                        }
                        
                        f.close();
                    }
                    catch(FileNotFoundException fnfe) {}
                    catch(IOException ioe) {}
                    if(copia)
                        errore.setText("ERRORE: genere già esistente.");
                    else{
                        try{
                            FileOutputStream f = new FileOutputStream("Generi.dat",true);
                            DataOutputStream dos = new DataOutputStream(f);
                            
                            dos.writeUTF(genere.getText());
                            
                            dos.flush();
                            f.close();
                        }
                        catch(IOException ioe) {}
                        errore.setText("Genere inserito con successo.");
                    }
                }
                genere.setText("");
            }
            else{
                this.setVisible(false);
                try{
                    Frame f = new Frame(b);
                }
                catch(Exception exc) {}
            }
    }
}
