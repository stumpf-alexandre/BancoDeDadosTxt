package BancoDeDadosTxt;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BancoDeDados extends JFrame implements ActionListener{
    JLabel lblid;
    JLabel lblnome;
    JButton btngrava;
    JButton btnlista;
    JButton btnfecha;
    JTextField txtid;
    JTextField txtnome;
    PrintWriter arq = new PrintWriter("Projeto_BD.txt");

    public BancoDeDados() throws FileNotFoundException {
        setTitle("Gravando BD em txt");
        setBounds(250,50,400,160);
        setBackground(new Color(150,150,150));
        lblid = new JLabel("ID:");
        lblnome = new JLabel("NOME:");
        btngrava = new JButton("GRAVAR");
        btngrava.addActionListener(this);
        btnlista = new JButton("LISTAR");
        btnlista.addActionListener(this);
        btnfecha = new JButton("FECHAR");
        btnfecha.addActionListener(this);
        txtid = new JTextField();
        txtnome = new JTextField();
        setLayout(null);
        lblid.setBounds(10,15,40,20);
        lblnome.setBounds(10,40,45,20);
        btngrava.setBounds(105,100,85,20);
        btnlista.setBounds(10,100,85,20);
        btnfecha.setBounds(200,100,85,20);
        txtid.setBounds(60,15,75,20);
        txtnome.setBounds(60,40,255,20);
        add(lblid);
        add(lblnome);
        add(btngrava);
        add(btnlista);
        add(btnfecha);
        add(txtid);
        add(txtnome);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btngrava){
            if (txtid.getText().trim().equals("")||txtnome.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Preenchimento dos campos id e nome obrigatorio");
                txtid.setText("");
                txtnome.setText("");
            }
            else {
                try {

                    arq.println(txtid.getText());
                    arq.println(txtnome.getText());
                    arq.close();
                    txtid.setText("");
                    txtnome.setText("");
                    JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Arquivo não foi salvo. Erro: " + erro.getMessage());
                }
            }
        }
        if (e.getSource() == btnlista){
            try {
                BufferedReader ar = new BufferedReader(new FileReader("Projeto_BD.txt"));
                txtid.setText(ar.readLine());
                txtnome.setText(ar.readLine());
                ar.close();
            }catch (Exception erro){
                JOptionPane.showMessageDialog(null,"Erro: "+erro.getMessage());
            }
        }
        if (e.getSource() == btnfecha){
            System.exit(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame box = new BancoDeDados();
        box.setUndecorated(true);
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        box.setVisible(true);
    }
}