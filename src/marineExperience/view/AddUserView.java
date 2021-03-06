/*
 * Desenvolvido por: Jonatan Aguiar - m107334 e Lucas Altmann - m110021
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.view;

import marineExperience.dao.DataSource;
import marineExperience.dao.Dao;

/**
 *
 * @author jonat
 */
public class AddUserView extends javax.swing.JFrame {

    /**
     * Creates new form addUserView
     */
    public AddUserView() {
        super("Marine Experience - Cadastro");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBGAdm = new javax.swing.ButtonGroup();
        jTFNome = new javax.swing.JTextField();
        jBCadastrar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLNome = new javax.swing.JLabel();
        jLSenha = new javax.swing.JLabel();
        jPFSenha = new javax.swing.JPasswordField();
        jLMarine = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(348, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(jTFNome);
        jTFNome.setBounds(100, 80, 210, 20);

        jBCadastrar.setBackground(new java.awt.Color(51, 102, 255));
        jBCadastrar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jBCadastrar.setText("Cadastrar");
        jBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jBCadastrar);
        jBCadastrar.setBounds(50, 170, 120, 21);

        jBCancelar.setBackground(new java.awt.Color(51, 102, 255));
        jBCancelar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jBCancelar);
        jBCancelar.setBounds(180, 170, 120, 21);

        jLNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLNome.setForeground(new java.awt.Color(0, 0, 255));
        jLNome.setText("Nome");
        getContentPane().add(jLNome);
        jLNome.setBounds(50, 80, 40, 21);

        jLSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLSenha.setForeground(new java.awt.Color(0, 0, 255));
        jLSenha.setText("Senha");
        getContentPane().add(jLSenha);
        jLSenha.setBounds(50, 110, 40, 21);
        getContentPane().add(jPFSenha);
        jPFSenha.setBounds(100, 110, 210, 20);

        jLMarine.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLMarine.setForeground(new java.awt.Color(0, 0, 255));
        jLMarine.setText("Marine Experience");
        getContentPane().add(jLMarine);
        jLMarine.setBounds(20, 20, 150, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication31/assets/timao.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 350, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        DataSource dataSource = new DataSource();
        Dao dao = new Dao(dataSource);
        dao.salvar(jTFNome.getText(), jPFSenha.getText());
    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.ButtonGroup jBGAdm;
    private javax.swing.JLabel jLMarine;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPFSenha;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}
