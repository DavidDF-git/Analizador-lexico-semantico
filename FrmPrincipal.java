/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package codigo;

import static codigo.Tokens.ERROR;
import static codigo.Tokens.Identificador;
import static codigo.Tokens.Numero;
import static codigo.Tokens.P_coma;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;

/**
 *
 * @author 13062
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void analizarLexico() throws IOException{
        int cont = 1;

    String expr = (String) txtLectura.getText();
    Lexer lexer = new Lexer(new StringReader(expr));
    String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";

    while (true) {
        Tokens token = lexer.yylex();
        if (token == null) {
            txtResultado.setText(resultado);
            return;
        }
        switch (token) {
            case Linea:
                cont++;
                resultado += "LINEA " + cont + "\n";
                break;
            case Comillas:
                resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                break;
            case Cadena:
                resultado += "  <Cadena>\t\t" + lexer.lexeme + "\n";
                break;
            case T_dato:
                resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                break;
            case If:
                resultado += "  <Reservada if>\t" + lexer.lexeme + "\n";
                break;
            case Else:
                resultado += "  <Reservada else>\t" + lexer.lexeme + "\n";
                break;
            case Do:
                resultado += "  <Reservada do>\t" + lexer.lexeme + "\n";
                break;
            case While:
                resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                break;
            case For:
                resultado += "  <Reservada for>\t" + lexer.lexeme + "\n";
                break;
            case Main:
                resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                break;
            case Igual:
                resultado += "  <Operador igual>\t" + lexer.lexeme + "\n";
                break;
            case Suma:
                resultado += "  <Operador suma>\t" + lexer.lexeme + "\n";
                break;
            case Resta:
                resultado += "  <Operador resta>\t" + lexer.lexeme + "\n";
                break;
            case Multiplicacion:
                resultado += "  <Operador multiplicación>\t" + lexer.lexeme + "\n";
                break;
            case Division:
                resultado += "  <Operador división>\t" + lexer.lexeme + "\n";
                break;
            case Op_logico:
                resultado += "  <Operador lógico>\t" + lexer.lexeme + "\n";
                break;
            case Op_incremento:
                resultado += "  <Operador incremento>\t" + lexer.lexeme + "\n";
                break;
            case Op_relacional:
                resultado += "  <Operador relacional>\t" + lexer.lexeme + "\n";
                break;
            case Op_atribucion:
                resultado += "  <Operador atribución>\t" + lexer.lexeme + "\n";
                break;
            case Op_booleano:
                resultado += "  <Operador booleano>\t" + lexer.lexeme + "\n";
                break;
            case Parentesis_a:
                resultado += "  <Paréntesis de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Parentesis_c:
                resultado += "  <Paréntesis de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Llave_a:
                resultado += "  <Llave de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Llave_c:
                resultado += "  <Llave de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Corchete_a:
                resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Corchete_c:
                resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                break;
            case P_coma:
                resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                break;
            case Identificador:
                resultado += "  <Identificador>\t" + lexer.lexeme + "\n";
                break;
            case Numero:
                resultado += "  <Número>\t\t" + lexer.lexeme + "\n";
                break;
            case Interrogacion_c:
                resultado += "  <Interrogación de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Scanf:
                resultado += "  <Reservada scanf>\t" + lexer.lexeme + "\n";
                break;
            case Printf:
                resultado += "  <Reservada printf>\t" + lexer.lexeme + "\n";
                break;
            case Modulo:
                resultado += "  <Módulo>\t\t" + lexer.lexeme + "\n";
                break;
            case Backslash:
                resultado += "  <Diagonal inversa>\t" + lexer.lexeme + "\n";
                break;
            case Dos_puntos:
                resultado += "  <Dos puntos>\t\t" + lexer.lexeme + "\n";
                break;
            case Exclamacion_a:
                resultado += "  <Exclamación de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Interrogacion_a:
                resultado += "  <Interrogación de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Include:
                resultado += "  <Palabra reservada include>" + lexer.lexeme + "\n";
                break;
            case Numeral:
                resultado += "  <Hashtag o numeral>\t" + lexer.lexeme + "\n";
                break;
            case Punto:
                resultado += "  <Punto>\t\t" + lexer.lexeme + "\n";
                break;
            case Dolar:
                resultado += "  <Símbolo de dólar>\t" + lexer.lexeme + "\n";
                break;
            case ERROR:
                resultado += "  <Símbolo no definido>\t" + lexer.lexeme + "\n";
                break;
            default:
                resultado += "  <" + lexer.lexeme + ">\n";
                break;
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultadoS = new javax.swing.JTextArea();
        btnAnalizarS = new javax.swing.JButton();
        btnLimpiarS = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAnalizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLectura = new javax.swing.JTextArea();
        btnAbrir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizador Sintáctico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        txtResultadoS.setColumns(20);
        txtResultadoS.setRows(5);
        jScrollPane2.setViewportView(txtResultadoS);

        btnAnalizarS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAnalizarS.setText("Analizar");
        btnAnalizarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSActionPerformed(evt);
            }
        });

        btnLimpiarS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLimpiarS.setText("Limpiar");
        btnLimpiarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAnalizarS, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiarS, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizarS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizador Léxico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        btnAnalizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        txtLectura.setColumns(20);
        txtLectura.setRows(5);
        jScrollPane3.setViewportView(txtLectura);

        btnAbrir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAbrir.setText("Abrir archivo ");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAbrir)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnalizar)
                        .addGap(115, 115, 115)
                        .addComponent(btnLimpiar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        try {
            // TODO add your handling code here:
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtLectura.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnAnalizarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSActionPerformed
        // TODO add your handling code here:
        String ST = txtLectura.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        try {
            s.parse();
            txtResultadoS.setText("Análisis realizado correctamente");
            txtResultadoS.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            if (sym != null) {
                txtResultadoS.setText(
                    "Error de sintaxis. Línea: " + (sym.left + 1) + 
                    " columna: " + (sym.right + 1) + 
                    ", Texto: \"" + sym.value + "\""
                );
            } else {
                txtResultadoS.setText("Error de sintaxis. No se pudo identificar el token con error.");
            }
            txtResultadoS.setForeground(Color.red);
        }
        
        
        
    }//GEN-LAST:event_btnAnalizarSActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        txtResultado.setText(null); 
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSActionPerformed
        // TODO add your handling code here:
        txtResultadoS.setText(null); 
    }//GEN-LAST:event_btnLimpiarSActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnAnalizarS;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtLectura;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtResultadoS;
    // End of variables declaration//GEN-END:variables
}
