/*
  To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;

import gramatica_xml.graxml;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author James_PC
 */
public class principal2 extends javax.swing.JFrame {

    public String EnderecoArquivo,EnderecoTemporario,bufferIn;
    public static Nodo master;
    public static Nodo usuarios;
    
    public principal2() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tx_entrada.setColumns(20);
        tx_entrada.setRows(5);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Botongenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(BtGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Botongenerar)
                    .addComponent(BtGraficar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotongenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotongenerarActionPerformed
        if(  principal2.usuarios == null){
            principal2.usuarios = CrearArbol.tryusuario(usuarios);
        }
        if(principal2.master == null){
            principal2.master = CrearArbol.trymaster(master);
        }else{
        
        }
       /* try 
       {
            Analise();
       } catch (IOException ex)
       {
            Logger.getLogger(principal2.class.getName()).log(Level.SEVERE, null, ex);
       }
        */
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
    }//GEN-LAST:event_BtGraficarActionPerformed

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
    
    private void Analise() throws IOException
    {
        EnderecoTemporario =  System.getProperty("java.io.tmpdir")+"temp.txt";
        File arquivo = new File(EnderecoTemporario);  
        FileWriter fw = new FileWriter(arquivo);  
        BufferedWriter bw = new BufferedWriter(fw);      
        bw.write(tx_entrada.getText());  
        bw.flush();  
        bw.close(); 
      
                 
        String nomeArq = new String (EnderecoTemporario);
        try
	{
			gramatica_paquete.grapq analizador = new gramatica_paquete.grapq(new FileInputStream(nomeArq)) ;
			analizador.inicio(); 
                        System.out.println("Analizador: correcto.");
	}
	catch(gramatica_paquete.ParseException e)
	{
			System.out.println(e.getMessage());
			System.out.println("Analizador: Se han encontrado errores en el analisis.");
       }       
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
            cadena += "\"" + nodo.getNumNodo() + "_" + nodo.getNombre() + "\"->\"" + hijos.getNumNodo() + "_" + hijos.getNombre()  + "\"";
            cadena += graficarNodo(hijos);
        }
        return cadena;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botongenerar;
    private javax.swing.JButton BtGraficar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea tx_consola;
    private javax.swing.JTextArea tx_entrada;
    private javax.swing.JTextArea tx_salida;
    // End of variables declaration//GEN-END:variables
}
