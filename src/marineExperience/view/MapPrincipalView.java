/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import marineExperience.dao.Dao;
import marineExperience.dao.DataSource;
import marineExperience.model.Ais;
import marineExperience.uteis.MapPoints;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jxmapviewer.JXMapViewer;

/**
 *
 * @author Dell
 */
public class MapPrincipalView extends javax.swing.JFrame {
    private DefaultTableModel model;
    private List<Ais> aiss = new ArrayList<>();
    private final MapPoints mapPoints = new MapPoints();
    private String dataI="  /  /    "; 
    private String dataF="  /  /    ";
    
    public MapPrincipalView(){
        initComponents();
    }

    //metodo que atualiza o mapa
    public void iniciaMap(JXMapViewer mapViewer){
        this.setLayout(new BorderLayout());
        this.add(mapViewer);
        this.add(jp, BorderLayout.WEST);
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Marine Experience");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp = new javax.swing.JPanel();
        jLInfo1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListagem = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLInfo2 = new javax.swing.JLabel();
        jLDataInicial = new javax.swing.JLabel();
        jLDataFinal = new javax.swing.JLabel();
        jTFDataInicial = new javax.swing.JFormattedTextField();
        jTFDataFinal = new javax.swing.JFormattedTextField();
        jBLimpar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jLInicial = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jp.setBackground(new java.awt.Color(255, 255, 255));
        jp.setMinimumSize(new java.awt.Dimension(340, 380));

        jLInfo1.setText("No mapa, use o botão esquerdo do mouse");

        jTListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "AIS", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTListagem);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ℹ ");
        jLabel2.setToolTipText("Informação para o uso do mapa ao lado");

        jLInfo2.setText("para mover e o scroll para aplicar zoom!");

        jLDataInicial.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLDataInicial.setForeground(new java.awt.Color(0, 0, 255));
        jLDataInicial.setText("Data Inicial");

        jLDataFinal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLDataFinal.setForeground(new java.awt.Color(0, 0, 255));
        jLDataFinal.setText("Data Final");

        try {
            jTFDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFDataInicial.setText("24/07/2019");
        jTFDataInicial.setToolTipText("Informe a data inicial");

        try {
            jTFDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFDataFinal.setText("26/07/2019");
        jTFDataFinal.setToolTipText("Informe a data final");

        jBLimpar.setText("Limpar");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jBBuscar.setText("Buscar");
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(jpLayout);
        jpLayout.setHorizontalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jpLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLInfo2)
                            .addComponent(jLInfo1)
                            .addComponent(jLDataFinal)
                            .addComponent(jTFDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpLayout.createSequentialGroup()
                                    .addComponent(jBLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTFDataFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLDataInicial))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpLayout.setVerticalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLayout.createSequentialGroup()
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLInfo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLDataInicial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLDataFinal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBLimpar)
                    .addComponent(jBBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jp);
        jp.setBounds(0, 0, 280, 620);

        jLInicial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication31/assets/atencao.png"))); // NOI18N
        jLInicial.setText("    Busque com um intervalo válido de datas para começar!");
        jLInicial.setToolTipText("");
        getContentPane().add(jLInicial);
        jLInicial.setBounds(430, 250, 510, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents
        

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        jTFDataInicial.setText("");
        jTFDataFinal.setText("");
        model.setNumRows(0);
    }//GEN-LAST:event_jBLimparActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        DataSource dataSource = new DataSource();
        Dao dao = new Dao(dataSource);
        model.setNumRows(0);
       
        if(!jTFDataInicial.getText().equals("  /  /    ") && !jTFDataFinal.getText().equals("  /  /    ")){
            if(dataI.equals(jTFDataInicial.getText()) && dataF.equals(jTFDataFinal.getText())){
                JOptionPane.showMessageDialog(null, "Tente com datas diferentes!");
            }else{
                dataI = jTFDataInicial.getText(); 
                dataF = jTFDataFinal.getText();
                String[] data = dataI.split("/");
                String dataIni = data[2]+"-"+data[1]+"-"+data[0];
                data = dataF.split("/");
                String dataFin = data[2]+"-"+data[1]+"-"+data[0];
                //recebe uma lista de ais
                aiss = dao.findByDate(dataIni,dataFin);
                for (int x = 0; x < aiss.size(); x++) { //add na tabela os dados
                    model.addRow(new Object[]{aiss.get(x).getId(), aiss.get(x).getMsg(), aiss.get(x).getData()});
                }
                mapPoints.RetornaPoints(aiss,this);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Informe um intervalo de datas");
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //add o model no evento de abrir tela e centraliza
        model = (DefaultTableModel) jTListagem.getModel();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JLabel jLDataFinal;
    private javax.swing.JLabel jLDataInicial;
    private javax.swing.JLabel jLInfo1;
    private javax.swing.JLabel jLInfo2;
    private javax.swing.JLabel jLInicial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jTFDataFinal;
    private javax.swing.JFormattedTextField jTFDataInicial;
    private javax.swing.JTable jTListagem;
    private javax.swing.JPanel jp;
    // End of variables declaration//GEN-END:variables
}
