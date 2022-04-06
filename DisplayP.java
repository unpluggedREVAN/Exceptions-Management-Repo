// Tarea manejo de excepciones - Incluyendo interfaz
// Jose Pablo Agüero Mora (2021126372) - Grupo 2
// Clase DisplayP con Swing

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DisplayP implements ActionListener{
    int presionados = 0;
    boolean continuar = true;
    boolean estado1 = false;
    boolean estado2 = false;
    boolean estado3 = false;

    JFrame ventana;
    JPanel panelBotones;

    JButton botonPrimero;
    JButton botonSegundo;
    JButton botonTercero;

    Proceso proc = new Proceso();

    public DisplayP(){
        ventana = new JFrame("Tarea corta excepciones");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarComponentes();
        ventana.setPreferredSize(new Dimension(500, 500));
        ventana.pack();
        ventana.setVisible(true);
    }

    private void agregarComponentes(){

        botonSegundo = new JButton("Presionar Segundo");
        botonSegundo.setActionCommand("ok"); // 
        botonSegundo.addActionListener(this);

        botonPrimero = new JButton("Presionar Primero");
        botonPrimero.setActionCommand("ok");
        botonPrimero.addActionListener(this);

        botonTercero = new JButton("Presionar Tercero");
        botonTercero.setActionCommand("ok");
        botonTercero.addActionListener(this); 

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1));

        panelBotones.add(botonSegundo);
        panelBotones.add(botonPrimero);
        panelBotones.add(botonTercero);
        ventana.add(panelBotones, BorderLayout.CENTER);

    }

    private void Comprobar(){
        if(presionados == 3){
            continuar = false;
        }
    }

    private void Orden(){
        if(presionados == 1){
            if(proc.DevuelveTotal() == 5){
                estado1 = true;
            }
            else estado1 = false;
        }
        if(presionados == 2){
            if((estado1 == true) && (proc.DevuelveTotal() == 55)){
                estado2 = true;
            }
            else estado2 = false;
        }
        if(presionados == 3){
            if((estado2 == true) && (proc.DevuelveTotal() == 555)){
                estado3 = true;
            }
            else estado3 = false;
        }
    }

    private void Termina() throws Exception{
        presionados = 0;
        continuar = true;
        throw new Exception();
    }

    private void Valida(){
        if(continuar == false){
            if(estado3 == true){
                JOptionPane.showMessageDialog(ventana, "Se presionaron en el orden CORRECTO");
                presionados = 0;
                continuar = true;
                proc.total = 0;
            }
            else {
                try {
                    Termina();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(ventana, "Se presionaron en el orden INCORRECTO");
                    //System.out.println("Generó la excepcion");
                    //System.out.println(proc.DevuelveTotal());
                    proc.total = 0;
                }
            }
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {   

        if(e.getSource().equals(botonSegundo)){
            //System.out.println("Se presionó el segundo botón");
            presionados += 1;
            proc.PresionarSegundo();
        }

        if(e.getSource().equals(botonPrimero)){
            //System.out.println("Se presionó el primer botón");
            presionados += 1;
            proc.PresionarPrimero();
        }

        if(e.getSource().equals(botonTercero)){
            //System.out.println("Se presionó el tercer botón");
            presionados += 1;
            proc.PresionarTercero();
        }

        if(e.getActionCommand().equals("ok")){
            Comprobar();
            Orden();
            Valida();
        } 
    }
}
