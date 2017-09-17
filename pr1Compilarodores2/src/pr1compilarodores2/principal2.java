/*
  To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;

import gramatica_xml.graxml;
import Acciones.tablasimbolos;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author James_PC
 */
public class principal2 extends javax.swing.JFrame {

    public String EnderecoArquivo,EnderecoTemporario,bufferIn;
    public static Nodo master;
    public static Nodo usuarios;
    public static Nodo paquete;
    public static String usua;
    public static String db;
    public static String texfun;
    public static String textopaquete = "";
    public static String ruta_master;
    public static String enviar;
    public static int port = 2018;
    public static Thread hilo = new clase_Socket("proceso 1"); 
    
    public static String  Num_aleatorio;
    public static String dj_ejecucion = "@# #@";
    public static String dj_mensaje = "@# #@";
    public static String dj_datos = "@# #@";
    public static JTextArea tx_resultado;
    JFrame frmprincipal;  
    JTextArea txtarea;  
    JScrollPane scroll; 
    
    public principal2() {
        principal2.usua = "bolo";
        principal2.ruta_master = "C:\\Users\\James_PC\\Documents\\compi2javaccpr1\\DB";
        db = "db2";
        
        frmprincipal = new JFrame();      
        frmprincipal.setSize(100,70);  
        frmprincipal.setTitle("Servidor de Base de Datos");
        frmprincipal.setLayout(null);                   
        principal2.tx_resultado = new javax.swing.JTextArea();
        principal2.tx_resultado.setColumns(20);
        principal2.tx_resultado.setRows(5);
        scroll = new JScrollPane( principal2.tx_resultado);    
        scroll.setBounds(new Rectangle(5,5,980,650));                                                    
        frmprincipal.add(scroll);                   
        frmprincipal.show(true); 
        
        principal2.hilo.start();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tx_entrada = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tx_salida = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tx_consola = new javax.swing.JTextArea();
        Botongenerar = new javax.swing.JButton();
        BtGraficar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jb_arbol = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tx_entrada.setColumns(20);
        tx_entrada.setRows(5);
        tx_entrada.setText("[\n\"validar\": 1500,\n\"paquete\": \"usql\",\n\"Instruccion\": %\n\nBORRAR EN TABLA estudiante DONDE nombre == \"José Luis Martínez\"\n&& trabaja == 0;\n\nBORRAR EN TABLA estudiante;\n\n %,\n]");
        jScrollPane1.setViewportView(tx_entrada);

        jLabel1.setText("Entrada");

        tx_salida.setColumns(20);
        tx_salida.setRows(5);
        jScrollPane2.setViewportView(tx_salida);

        jLabel2.setText("Salida");

        jLabel3.setText("Consola");

        tx_consola.setColumns(20);
        tx_consola.setRows(5);
        jScrollPane3.setViewportView(tx_consola);

        Botongenerar.setText("Generar");
        Botongenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotongenerarActionPerformed(evt);
            }
        });

        BtGraficar.setText("Graficar");
        BtGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtGraficarActionPerformed(evt);
            }
        });

        jButton1.setText("pruebas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jb_arbol.setText("Arbol");
        jb_arbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_arbolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Botongenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtGraficar, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jb_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(BtGraficar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                    .addComponent(Botongenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_arbol)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void iniciar(String mensaje){
        String tep;
        if( principal2.usuarios == null){
            principal2.usuarios = CrearArbol.tryusuario(usuarios); }
        if(principal2.master == null){
            principal2.master = CrearArbol.trymaster(master); }
        if(mensaje.equalsIgnoreCase("")==false){
            principal2.paquete = CrearArbol.trypaquete(paquete, mensaje);
            Acciones.accpaquete.tipopaquete(principal2.paquete, principal2.usuarios, principal2.master);
            tx_resultado.setText(principal2.textopaquete);
        }
        
    }
    
    public void cambiar(){
        tx_salida.setText(principal2.textopaquete);
    }
    
    private void BotongenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotongenerarActionPerformed
        String tep;
        if(  principal2.usuarios == null){
            principal2.usuarios = CrearArbol.tryusuario(usuarios);
        }
        if(principal2.master == null){
            principal2.master = CrearArbol.trymaster(master);
        }
        tep = tx_entrada.getText();
        if(tep.equals("a") == false){
//             System.out.println(tx_entrada.getText());
               principal2.paquete = CrearArbol.trypaquete(paquete, tep);
               Acciones.accpaquete.tipopaquete(principal2.paquete, principal2.usuarios, principal2.master);
            // tx_salida.setText(principal2.textopaquete);
         }
    }//GEN-LAST:event_BotongenerarActionPerformed

    private void BtGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtGraficarActionPerformed
        int valor;
        String cadena = "";
        if(principal2.usuarios != null){
                graficar(principal2.usuarios,"usu");
                graficar(principal2.master,"master");
                cadena = "Se ha graficado con exito \n";
                tx_consola.setText(cadena);
        }
        if(principal2.paquete != null){
            graficar(principal2.paquete,"paquete");
            cadena = cadena + "Se ha graficado con exito \n";
            tx_consola.setText(cadena);
        }
    }//GEN-LAST:event_BtGraficarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        principal2.enviar = "mundo feliz";
        principal2.tx_resultado.setText("Hola Mundo");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jb_arbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_arbolActionPerformed
        // TODO add your handling code here:
        Nodo mast = principal2.master;
        String cadena = "";
         if(principal2.master != null){
           for(Nodo recorrer : mast.getHijos()){
               cadena = cadena + "<li><a>"+recorrer.getNombre()+"</a><ul> \n";
               int contador = 0;
               for(Nodo arbol : recorrer.getHijos()){
                   if(arbol.getNombre().equalsIgnoreCase("objeto")){
                        cadena = cadena + "<li><a>Objeto</a><ol> \n ";
                        for(Nodo primo : arbol.getHijos()){
                            cadena  = cadena + "<li>"+primo.getNombre()+"</li> \n";
                        }
                        cadena = cadena + "</ol> \n";
                    }else if(arbol.getNombre().equalsIgnoreCase("procedure")){
                        cadena = cadena + "<li><a>procedure</a><ol> \n ";
                        for(Nodo primo : arbol.getHijos()){
                            cadena  = cadena + "<li>"+primo.getNombre()+"</li> \n";
                        }
                        cadena = cadena + "</ol> \n";
                    }else{
                        if(contador==0){
                            cadena = cadena + "<li><a>tabla</a><ol> \n";
                            cadena  = cadena + "<li>"+arbol.getNombre()+"</li> \n";
                            contador = 1;
                        }else{
                            cadena  = cadena + "<li>"+arbol.getNombre()+"</li> \n";
                        }
                    }
               }
               if(contador>0){
                   cadena = cadena + "</ol> \n";
               }
               cadena = cadena + "</ul></li> \n";
           }
        }
        System.out.println(cadena);
    }//GEN-LAST:event_jb_arbolActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal2().setVisible(true);
            }
        });
    }
    
      
     public void graficar(Nodo raiz, String texto){
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);
        
        try{
            archivo = new FileWriter(texto+".dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        }catch (Exception e) {
            System.out.println(e +" 1");
        }
        
        try {
            String cmd = "dot -Tpng " + texto + ".dot  -o " + texto + ".png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe +" 2");
        }
        
    }
    
    public String graficarNodo(Nodo nodo){
        String cadena = "";
        for(Nodo hijos : nodo.getHijos())
        {
            String nombre = nodo.getNombre().replaceAll("\"", "");
            String hijo =   hijos.getNombre().replaceAll("\"", "");     
            cadena += "\"" + nodo.getNumNodo() + "_" + nombre + "\"->\"" + hijos.getNumNodo() + "_" + hijo  + "\"";
            cadena += graficarNodo(hijos);
        }
        return cadena;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botongenerar;
    private javax.swing.JButton BtGraficar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jb_arbol;
    private javax.swing.JTextArea tx_consola;
    private javax.swing.JTextArea tx_entrada;
    private javax.swing.JTextArea tx_salida;
    // End of variables declaration//GEN-END:variables
}
