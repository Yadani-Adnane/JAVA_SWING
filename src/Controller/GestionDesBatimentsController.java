package Controller;

import Model.Bd;
import Model.Residant;
import View.GestionDesBatimentsView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GestionDesBatimentsController {
    GestionDesBatimentsView view ;
    Bd model;
    int rowIndex = 0;
    public GestionDesBatimentsController( GestionDesBatimentsView myview, Bd mymodel ) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
         model = mymodel;
         view = myview;
         view.addgenrelistlistner(new java.awt.event.ActionListener(){
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 try {
                     IdBatiment();
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                     /*throw new RuntimeException(e);*/
                 }
             }
         });
         view.addajouterlistner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 JButton sourceButton = (JButton) evt.getSource();
                 String buttonText = sourceButton.getText();
                 if(buttonText.equalsIgnoreCase("Ajouter")) {
                     ajouterBat();
                     view.id_tf.setText("");
                 }else {
                     view.table.clearSelection();
                     view.ajouter.setText("Ajouter");
                     view.chercher.setText("Chercher");

                     view.cherche_tf.requestFocus();
                     view.annuler1.setVisible(false);
                     try {
                         modifierBat();
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                     try {
                         chargerEt();
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                     view.id_tf.setText("");
                 }

             }
         });
         view.addajouter2listner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 JButton sourceButton = (JButton) evt.getSource();
                 String buttonText = sourceButton.getText();
                 if(buttonText.equalsIgnoreCase("Ajouter")) {
                     ajouterEt();
                 }else {
                     try {
                         modifierEt();
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                     view.table2.clearSelection();
                     view.ajouter2.setText("Ajouter");
                     view.chercher2.setText("Chercher");
                     view.cherche_tf2.requestFocus();
                     view.annuler2.setVisible(false);
                 }

             }
         });
         view.addtablelestner(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {

                     if (view.table.getSelectedRow() >-1){
                         view.ajouter.setText("Modifier");
                         view.chercher.setText("Supprimer");
                         view.annuler1.setVisible(true);
                         view.annuler1.addActionListener(new java.awt.event.ActionListener() {
                             public void actionPerformed(java.awt.event.ActionEvent evt) {
                                 view.table.clearSelection();
                                 view.ajouter.setText("Ajouter");
                                 view.chercher.setText("Chercher");
                                 view.id_tf.setText("");
                                 view.cherche_tf.requestFocus();
                                 view.annuler1.setVisible(false);
                             }
                         });
                         view.ongle1.add(view.annuler1);
                         view.ongle1.revalidate();
                         view.ongle1.repaint();
                         rowIndex = view.table.getSelectedRow();
                         if(view.table.getValueAt(rowIndex, 1).toString().equals("F")){
                             view.genre_list.setSelectedIndex(0);
                         }else view.genre_list.setSelectedIndex(1);
                         if(view.table.getValueAt(rowIndex, 2).toString().equals("0")){
                             view.etat_list.setSelectedIndex(0);
                         }else view.etat_list.setSelectedIndex(1);
                         view.id_tf.setText(view.table.getValueAt(rowIndex, 0).toString());
                     }
                 }
             }
         });
         view.addchercherlestner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 JButton sourceButton = (JButton) evt.getSource();
                 String buttonText = sourceButton.getText();
                 if(buttonText.equalsIgnoreCase("Chercher")) {
                     if(view.cherche_tf.getText().equals("") || view.cherche_tf.getText().equals("Chercher par ID")) {
                         JOptionPane.showMessageDialog(view, "Entrez un ID!", "Error", JOptionPane.ERROR_MESSAGE);
                     }else {
                         System.out.println(view.table.getSelectedRow());
                         if(!view.cherche_tf.getText().equals("Chercher par ID")){
                             view.annuler1.setVisible(true);
                             view.annuler1.addActionListener(new java.awt.event.ActionListener() {
                                 public void actionPerformed(java.awt.event.ActionEvent evt) {
                                     view.table.clearSelection();
                                     view.id_tf.setText("");
                                     view.cherche_tf.requestFocus();
                                     view.annuler1.setVisible(false);
                                     try {
                                         chargerBat();
                                     } catch (Exception e) {
                                         throw new RuntimeException(e);
                                     }
                                 }
                             });
                             view.ongle1.add(view.annuler1);
                             view.ongle1.revalidate();
                             view.ongle1.repaint();
                             try {
                                 chrcherbat();
                             } catch (Exception e) {

                             }
                         }
                     }
                 }else {
                     try {
                         supprimerBat();
                         view.ajouter.setText("Ajouter");
                         view.chercher.setText("Chercher");
                         view.table.clearSelection();
                         view.id_tf.setText("");
                         view.cherche_tf.requestFocus();
                         view.annuler1.setVisible(false);
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                 }

             }
         });
         view.addidbat1listner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 try {
                     IdEtage();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
         });
         view.addtable2lestner(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     if (view.table2.getSelectedRow() >-1){
                         view.ajouter2.setText("Modifier");
                         view.chercher2.setText("Supprimer");
                         view.annuler2.setVisible(true);
                         view.annuler2.addActionListener(new java.awt.event.ActionListener() {
                             public void actionPerformed(java.awt.event.ActionEvent evt) {
                                 view.table2.clearSelection();
                                 view.ajouter2.setText("Ajouter");
                                 view.chercher2.setText("Chercher");
                                 view.id_bat1.setEnabled(true);
                                 view.id_etage_tf.setText("");
                                 view.numetagetf.setText("");
                                 view.cherche_tf2.requestFocus();
                                 view.annuler2.setVisible(false);
                             }
                         });
                         view.ongle2.add(view.annuler2);
                         view.ongle2.revalidate();
                         view.ongle2.repaint();
                         rowIndex = view.table2.getSelectedRow();
                         view.id_bat1.setEnabled(true);
                         view.id_bat1.setSelectedItem(view.table2.getValueAt(rowIndex, 0).toString());
                         view.id_etage_tf.setText(view.table2.getValueAt(rowIndex, 1).toString());
                         view.numetagetf.setText(view.table2.getValueAt(rowIndex, 2).toString());
                         view.id_bat1.setEnabled(false);
                         if(view.table2.getValueAt(rowIndex, 3).toString().equals("0")){
                             view.etat_list2.setSelectedIndex(0);
                         }else view.etat_list2.setSelectedIndex(1);
                         Batiment b = null;
                         try {
                             b = (Batiment) model.select("*","Batiment","id_batiment ='"+view.table2.getValueAt(rowIndex, 0).toString()+"'").get(0);
                         } catch (Exception ex) {
                             throw new RuntimeException(ex);
                         }
                         if(b.getEtat()==0){
                             view.etat_list2.setEnabled(false);
                         }
                     }
                 }
             }
         });
         view.addchercher2lestner(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton sourceButton = (JButton) evt.getSource();
                String buttonText = sourceButton.getText();
                if(buttonText.equalsIgnoreCase("Chercher")) {
                    if(view.cherche_tf2.getText().equals("") || view.cherche_tf2.getText().equals("Chercher par ID")) {
                        JOptionPane.showMessageDialog(view, "Entrez un ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else {
                        System.out.println(view.table2.getSelectedRow());
                        if(!view.cherche_tf2.getText().equals("Chercher par ID")){
                            view.annuler2.setVisible(true);
                            view.annuler2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    view.table2.clearSelection();
                                    view.cherche_tf2.requestFocus();
                                    view.annuler2.setVisible(false);
                                    try {
                                        chargerEt();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            view.ongle2.add(view.annuler2);
                            view.ongle2.revalidate();
                            view.ongle2.repaint();
                            try {
                                chrcherEt();
                            } catch (Exception e) {

                            }
                        }
                    }
                }else {
                    try {
                        supprimerEt();
                        view.ajouter2.setText("Ajouter");
                        view.chercher2.setText("Chercher");
                        view.table2.clearSelection();
                        view.cherche_tf2.requestFocus();
                        view.annuler2.setVisible(false);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
         view.addidtf3listner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 try {
                     IdChambre();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
         });
         view.addtable3lestner(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (view.table3.getSelectedRow() >-1){
                        view.ajouter3.setText("Modifier");
                        view.chercher3.setText("Supprimer");
                        view.annuler3.setVisible(true);
                        view.annuler3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                view.table3.clearSelection();
                                view.ajouter3.setText("Ajouter");
                                view.chercher3.setText("Chercher");
                                view.id_chmabre_tf.setText("");
                                view.reservation_list.setEnabled(true);
                                view.id_tf3.setEnabled(true);
                                view.id_etage_tf1.setEnabled(true);
                                view.id_tf3.setSelectedIndex(-1);
                                view.id_etage_tf1.setSelectedIndex(-1);
                                view.chambre_num_tf.setText("");
                                view.cherche_tf3.requestFocus();
                                view.annuler3.setVisible(false);
                            }
                        });
                        view.ongle3.add(view.annuler3);
                        view.ongle3.revalidate();
                        view.ongle3.repaint();
                        rowIndex = view.table3.getSelectedRow();
                        view.id_tf3.setEnabled(true);
                        view.id_etage_tf1.setEnabled(true);
                        view.etat_list3.setEnabled(true);
                        view.reservation_list.setEnabled(true);
                        view.id_tf3.setSelectedItem(view.table3.getValueAt(rowIndex, 0).toString());
                        view.id_etage_tf1.setSelectedItem(view.table3.getValueAt(rowIndex, 1).toString());
                        view.id_chmabre_tf.setText(view.table3.getValueAt(rowIndex, 2).toString());
                        view.chambre_num_tf.setText(view.table3.getValueAt(rowIndex, 3).toString());
                        view.etat_list3.setSelectedIndex(Integer.parseInt( view.table3.getValueAt(rowIndex, 4).toString()));
                        view.reservation_list.setSelectedIndex(Integer.parseInt( view.table3.getValueAt(rowIndex,5).toString()));
                        view.id_tf3.setEnabled(false);
                        view.id_etage_tf1.setEnabled(false);
                        if (view.table3.getValueAt(rowIndex, 4).toString().equals("0")){
                            view.etat_list3.setEnabled(false);
                        }else view.id_etage_tf1.setEnabled(true);
                        view.reservation_list.setEnabled(false);
                        Etage et = null;
                        try {
                            et = (Etage) model.select("*","Etage","id_etage ='"+view.id_etage_tf1.getSelectedItem().toString()+"'").get(0);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        if(et.getEtat()==0){
                            view.etat_list2.setEnabled(false);
                        }
                    }
                }
            }
        });
         view.addajouter3listner(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 JButton sourceButton = (JButton) evt.getSource();
                 String buttonText = sourceButton.getText();
                 if(buttonText.equalsIgnoreCase("Ajouter")) {
                     ajouterCh();
                 }else {
                     try {
                         modifierch();
                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }
                     view.table2.clearSelection();
                     view.ajouter2.setText("Ajouter");
                     view.chercher2.setText("Chercher");
                     view.cherche_tf2.requestFocus();
                     view.annuler2.setVisible(false);
                 }

             }
         });
         view.addchercher3lestner(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton sourceButton = (JButton) evt.getSource();
                String buttonText = sourceButton.getText();
                if(buttonText.equalsIgnoreCase("Chercher")) {
                    if(view.cherche_tf3.getText().equals("") || view.cherche_tf3.getText().equals("Chercher par ID")) {
                        JOptionPane.showMessageDialog(view, "Entrez un ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else {
                        if(!view.cherche_tf3.getText().equals("Chercher par ID")){
                            view.annuler3.setVisible(true);
                            view.annuler3.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    view.table3.clearSelection();
                                    view.cherche_tf3.requestFocus();
                                    view.annuler3.setVisible(false);
                                    try {
                                        chargerCh();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            view.ongle3.add(view.annuler3);
                            view.ongle3.revalidate();
                            view.ongle3.repaint();
                            try {
                                chrcherCh();
                            } catch (Exception e) {

                            }
                        }
                    }
                }else {
                    try {
                        supprimerCh();
                        view.ajouter3.setText("Ajouter");
                        view.chercher3.setText("Chercher");
                        view.table3.clearSelection();
                        view.cherche_tf3.requestFocus();
                        view.annuler3.setVisible(false);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        chargerBat();
    }
    public void chrcherbat() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.model.setRowCount(0);
        Batiment b= Batiment.chercher(view.cherche_tf.getText().toUpperCase(),model);
        if(b == null){
            JOptionPane.showMessageDialog(view, "Batiment introuvable!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Object[] o= {b.getId_batiment(),b.getGenre(),b.getEtat(),0,0};
            view.model.addRow(o);
        }

    }
    public void chrcherEt() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.modell.setRowCount(0);
        Etage e= Etage.chercher(view.cherche_tf2.getText().toUpperCase(),model);
        int nbch =0;
        List<Object> ch = model.select("*","Chambre","id_etage = '"+view.cherche_tf2.getText().toUpperCase()+"'");
        nbch += ch.size();
        if(e == null){
            JOptionPane.showMessageDialog(view, "Etage introuvable!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Object[] o= {e.getId_batiment(),e.getId_etage(),e.getNum(),e.getEtat(),nbch};
            view.modell.addRow(o);
        }

    }
    public void chrcherCh() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.modelll.setRowCount(0);
        Chambre c = Chambre.chercher(view.cherche_tf3.getText().toUpperCase(),model);
        if(c == null){
            JOptionPane.showMessageDialog(view, "Chambre introuvable!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Etage e = (Etage)model.select("*","Etage","id_etage = '"+c.getId_etage().toUpperCase()+"'").get(0);
            Object[] o= {e.getId_batiment().toUpperCase(),c.getId_etage().toUpperCase(),c.getId_chambre().toUpperCase(),e.getNum(),c.getEtat(),c.getReservee()};
            view.modelll.addRow(o);
        }

    }
    public void IdBatiment() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int n = ((int) model.selectFun("COUNT","batiment","*","genre = '"+view.genre_list.getSelectedItem().toString()+"'"));
        if(n == 0){
            n++;
        }
        String genre = view.genre_list.getSelectedItem().toString();
        if(model.select("*","Batiment","id_batiment ='"+(genre+n).toUpperCase()+"'").size()>0){
            Batiment b = (Batiment) model.select("*","Batiment","id_batiment ='"+(genre+n).toUpperCase()+"'").get(0);
            while (!b.getId_batiment().equals("")){
                n++;
                if(model.select("*","Batiment","id_batiment ='"+(genre+n).toUpperCase()+"'").size()>0){
                    b = (Batiment) model.select("*","Batiment","id_batiment ='"+(genre+n).toUpperCase()+"'").get(0);
                }else {
                    break;
                }
            }
            view.id_tf.setText((genre+n).toUpperCase());
        }else {
            view.id_tf.setText((genre+1).toUpperCase());
        }


    }
    public void IdEtage() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.etat_list2.setEnabled(true);
        if(view.table2.getSelectedRow()==-1){
            if(view.id_bat1.getSelectedItem() != null){
                String id = view.id_bat1.getSelectedItem().toString();
                Batiment b = (Batiment) model.select("*","Batiment","id_batiment ='"+id.toUpperCase()+"'").get(0);
                view.etat_list2.setSelectedIndex(b.getEtat());
                if(b.getEtat()==0){
                    view.etat_list2.setEnabled(false);
                }
                Etage e=new Etage("",0,"",0);
                if(model.select("*","Etage","id_batiment='"+id.toUpperCase()+"'").size()>0){
                    int n = ((int) model.selectFun("COUNT","etage","*","id_batiment = '"+id.toUpperCase()+"'"));

                    if(model.select("*","Etage","id_etage ='"+(id+"e"+n).toUpperCase()+"'").size()>0){
                        e = (Etage) model.select("*","Etage","id_etage ='"+(id+"e"+n).toUpperCase()+"'").get(0);
                    }
                    while (!e.getId_etage().equals("")){
                        n++;
                        if(model.select("*","Etage","id_etage ='"+(id+"e"+n).toUpperCase()+"'").size()>0){
                            e = (Etage) model.select("*","Etgae","id_Etgae ='"+(id+n).toUpperCase()+"'").get(0);
                        }else {
                            id +="E"+n;
                            break;
                        }
                    }
                    view.id_etage_tf.setText(id.toUpperCase());
                    view.numetagetf.setText(String.valueOf(n));
                    if (b.getEtat() == 0){
                        view.etat_list2.setSelectedIndex(0);
                    }else{
                        view.etat_list2.setSelectedIndex(1);
                    }
                }else {
                    view.id_etage_tf.setText((id+"E"+1).toUpperCase());
                    view.numetagetf.setText(String.valueOf(1));
                }
            }
        }
    }
    public void IdChambre() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.etat_list3.setEnabled(true);
            if(view.id_tf3.getSelectedItem() != null){
                List<Object> et = model.select("*","Etage","id_batiment = '"+view.id_tf3.getSelectedItem().toString().toUpperCase()+"'");
                view.id_etage_tf1.removeAllItems();
                for (int i = 0; i < et.size(); i++) {
                    view.id_etage_tf1.setSelectedIndex(-1);
                    view.id_etage_tf1.addItem(((Etage)et.get(i)).getId_etage().toUpperCase());
                }
            }
            view.id_etage_tf1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(view.id_etage_tf1.getSelectedIndex() > -1){
                        try {
                            Etage e = (Etage) model.select("*","Etage","id_etage ='"+view.id_etage_tf1.getSelectedItem().toString().toUpperCase()+"'").get(0);
                            view.etat_list3.setSelectedIndex(e.getEtat());
                            if(e.getEtat() == 0){
                                view.etat_list3.setEnabled(false);
                            }else view.etat_list3.setEnabled(true);
                            int n = (int) model.selectFun("COUNT","chambre","*","id_etage = '"+view.id_etage_tf1.getSelectedItem().toString().toUpperCase()+"'");
                            if(model.select("*","Chambre","id_chambre ='"+(view.id_etage_tf1.getSelectedItem().toString()+"C"+n).toUpperCase()+"'").size()>0){
                                Chambre ch = (Chambre) model.select("*","Chambre","id_chambre ='"+(view.id_etage_tf1.getSelectedItem().toString()+"C"+n).toUpperCase()+"'").get(0);
                                while (!ch.getId_chambre().equals("")){
                                    n++;
                                    if(model.select("*","Chambre","id_chambre ='"+(view.id_etage_tf1.getSelectedItem().toString()+"C"+n).toUpperCase()+"'").size()>0){
                                        ch = (Chambre) model.select("*","Chambre","id_chambre ='"+(view.id_etage_tf1.getSelectedItem().toString()+"C"+n).toUpperCase()+"'").get(0);
                                    }else {
                                        break;
                                    }
                                }
                                view.id_chmabre_tf.setText((view.id_etage_tf1.getSelectedItem().toString()+"C"+n).toUpperCase());
                                view.chambre_num_tf.setText(Integer.toString(n));
                            }else {
                                view.id_chmabre_tf.setText((view.id_etage_tf1.getSelectedItem().toString()+"C"+1).toUpperCase());
                                view.chambre_num_tf.setText(Integer.toString(1));
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }

    public void ajouterBat(){
        if(view.id_tf.getText().equals("")){
            JOptionPane.showMessageDialog(view, "Remplir les donneés de nouveau batiment!", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            new Batiment(view.id_tf.getText().toUpperCase(),view.genre_list.getSelectedItem().toString(),view.etat_list.getSelectedIndex()).ajouter(model);
            Object[] o={view.id_tf.getText().toUpperCase(),view.genre_list.getSelectedItem().toString(),view.etat_list.getSelectedIndex(),0,0};
            view.id_tf.setText("");
            try {
                chargerBat();
                chargerEt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ajouterEt(){
        if(view.id_etage_tf.getText().equals("")){
            JOptionPane.showMessageDialog(view, "Remplir les donneés de nouveau étage!", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            int etat =1;
            if (view.etat_list2.isEnabled()==false){
                etat =0;
            }
            new Etage(view.id_etage_tf.getText().toString(), etat, view.id_bat1.getSelectedItem().toString().toUpperCase(), Integer.parseInt(view.numetagetf.getText().toString())).ajouter(model);
            view.id_etage_tf.setText("");
            try {
                chargerBat();
                chargerEt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ajouterCh(){
        if(view.id_chmabre_tf.getText().equals("")){
            JOptionPane.showMessageDialog(view, "Remplir les donneés de la nouvelle chmabre!", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            int etat=1;
            if (view.etat_list3.isEnabled()==false)
                etat=0;
            new Chambre(view.id_chmabre_tf.getText().toUpperCase(),Integer.parseInt(view.chambre_num_tf.getText()),etat,view.id_etage_tf1.getSelectedItem().toString().toUpperCase(),view.reservation_list.getSelectedIndex()).ajouter(model);
            view.id_chmabre_tf.setText("");
            try {
                chargerBat();
                chargerEt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void supprimerBat() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        new Batiment(view.id_tf.getText().toUpperCase(),view.genre_list.getSelectedItem().toString(),view.etat_list.getSelectedIndex()).supprimer(model);
        Object[] o={view.id_tf.getText(),view.genre_list.getSelectedItem().toString(),view.etat_list.getSelectedIndex(),0,0};
        view.model.removeRow(rowIndex);
        view.id_bat1.removeItem(view.id_tf.getText().toUpperCase());
        view.id_tf3.removeItem(view.id_tf.getText().toUpperCase());
        chargerEt();
    }
    public void supprimerEt() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        int etat ;
        if(view.etat_list2.isEditable()== false){
            etat=0;
        }else etat=1;
        new Etage(view.id_etage_tf.getText().toUpperCase(),etat,view.id_bat1.getSelectedItem().toString().toUpperCase(),Integer.parseInt(view.numetagetf.getText())).supprimer(model);
        view.modell.removeRow(rowIndex);
        view.id_bat1.setEnabled(true);
        chargerBat();
    }
    public void supprimerCh() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        view.id_etage_tf1.setEnabled(true);
        view.etat_list3.setEnabled(true);
        view.reservation_list.setEnabled(true);
        new Chambre(view.id_chmabre_tf.getText().toUpperCase(),Integer.parseInt(view.chambre_num_tf.getText()),view.etat_list3.getSelectedIndex(),view.id_etage_tf1.getSelectedItem().toString().toUpperCase(),view.reservation_list.getSelectedIndex()).supprimer(model);
        chargerBat();
    }
    public void modifierBat() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        new Batiment(view.id_tf.getText().toUpperCase(),view.genre_list.getSelectedItem().toString(),view.etat_list.getSelectedIndex()).modifier(model,view.table.getValueAt(rowIndex, 0).toString());
        int nbch =0;
        int nbchdispo =0;
        List<Object> et = model.select("*","Etage","id_batiment = '"+view.id_tf.getText().toUpperCase()+"'");
        for (int j = 0; j < et.size(); j++) {
            List<Object> ch = model.select("*","Chambre","id_etage = '"+((Etage) et.get(j)).getId_etage().toUpperCase()+"'");
            nbch += ch.size();
            List<Object> chdispo = model.select("*","Chambre","id_etage = '"+((Etage) et.get(j)).getId_etage().toUpperCase()+"' and reservee = 1");
            nbchdispo += chdispo.size();
        }
        chargerBat();
    }
    public void modifierEt() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        int etat ;
        if(view.etat_list2.isEnabled()== false){
            etat=0;
        }else etat=1;
        new Etage(view.id_etage_tf.getText().toUpperCase(),etat,view.id_bat1.getSelectedItem().toString().toUpperCase(),Integer.parseInt(view.numetagetf.getText())).modifier(model,view.table2.getValueAt(rowIndex, 0).toString());
        List<Object> ch = model.select("*","Chambre","id_etage = '"+view.id_etage_tf.getText().toUpperCase()+"'");
        int nbch = ch.size();
        List<Object> chdispo = model.select("*","Chambre","id_etage = '"+view.id_etage_tf.getText().toUpperCase()+"' and reservee = 0 and etat = 1");
        int nbchdispo = chdispo.size();
        chargerBat();
    }
    public void modifierch() throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        int etat ;
        int reserv;
        if(view.etat_list3.isEnabled()== false){
            etat=0;
        }else etat=view.etat_list3.getSelectedIndex();
        if(view.reservation_list.isEnabled()==false){
            reserv =0;
        }else reserv =view.reservation_list.getSelectedIndex();
        new Chambre(view.id_chmabre_tf.getText(),Integer.parseInt(view.chambre_num_tf.getText()),etat,view.id_etage_tf1.getSelectedItem().toString().toUpperCase(),reserv).modifier(model,view.table3.getValueAt(rowIndex, 2).toString());
        chargerBat();
    }


    public void chargerBat() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.model.setRowCount(0);
        List<Object> donnees=model.select("*","Batiment","1=1");
        if(view.id_bat1.getItemCount()>0){
            view.id_bat1.removeAllItems();
        }
        if(view.id_tf3.getItemCount()>0){
            view.id_tf3.removeAllItems();
        }
        for (int i = 0; i < donnees.size(); i++) {
            view.id_bat1.addItem(((Batiment) donnees.get(i)).getId_batiment().toUpperCase());

            view.id_tf3.addItem(((Batiment) donnees.get(i)).getId_batiment().toUpperCase());

        }
        view.id_bat1.setSelectedIndex(-1);
        view.id_tf3.setSelectedIndex(-1);
        for (int i = 0; i < donnees.size(); i++) {
            int nbch =0;
            int nbchdispo =0;
            List<Object> et = model.select("*","Etage","id_batiment = '"+((Batiment) donnees.get(i)).getId_batiment().toUpperCase()+"'");
            for (int j = 0; j < et.size(); j++) {
                List<Object> ch = model.select("*","Chambre","id_etage = '"+((Etage) et.get(j)).getId_etage().toUpperCase()+"'");
                List<Object> chdispo = model.select("*","Chambre","id_etage = '"+((Etage) et.get(j)).getId_etage().toUpperCase()+"' and reservee = 0 and etat =1");
                nbchdispo += chdispo.size();
                nbch += ch.size();
            }
            Object[] o= {((Batiment) donnees.get(i)).getId_batiment().toUpperCase(),((Batiment) donnees.get(i)).getGenre(),(((Batiment) donnees.get(i)).getEtat()),et.size(),nbch,nbchdispo};
            view.model.addRow(o);
        }
        chargerEt();
    }
    public void chargerEt() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.modell.setRowCount(0);
        view.id_etage_tf.setText("");
        view.numetagetf.setText("");
        List<Object> donnees=model.select("*","Etage","1=1");
        if (view.id_etage_tf1.getItemCount()>0){
            view.id_etage_tf1.removeAllItems();
        }
        int nbch =0;
        int nbchdispo =0;
        for (int i = 0; i < donnees.size(); i++) {
            List<Object> ch = model.select("*","Chambre","id_etage = '"+((Etage) donnees.get(i)).getId_etage().toUpperCase()+"'");
            nbch += ch.size();
            List<Object> chdispo = model.select("*","Chambre","id_etage = '"+((Etage) donnees.get(i)).getId_etage().toUpperCase()+"' and reservee = 0 and etat =1");
            nbchdispo += chdispo.size();
            Object[] o= {((Etage) donnees.get(i)).getId_batiment().toUpperCase(),((Etage) donnees.get(i)).getId_etage().toUpperCase(),(((Etage) donnees.get(i)).getNum()),(((Etage) donnees.get(i)).getEtat()),nbch,nbchdispo};
            view.modell.addRow(o);
        }
        chargerCh();
    }
    public void chargerCh() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.modelll.setRowCount(0);
        view.id_etage_tf1.setSelectedIndex(-1);
        view.chambre_num_tf.setText("");
        List<Object> donnees=model.select("*","Chambre","1=1");
        for (int i = 0; i < donnees.size(); i++) {
            Etage et = (Etage) model.select("*","Etage","id_etage ='"+((Chambre) donnees.get(i)).getId_etage().toUpperCase()+"'").get(0);
            Reservation r;
            String resi;
            if(model.select("*","Reservation", "id_chambre ='"+((Chambre) donnees.get(i)).getId_chambre().toUpperCase()+"'").size()>0){
                r =(Reservation) model.select("*","Reservation", "id_chambre ='"+((Chambre) donnees.get(i)).getId_chambre().toUpperCase()+"'").get(0);
                resi =r.getId_residant();;
            }else resi ="";
            Object[] o= {et.getId_batiment().toUpperCase(),((Chambre) donnees.get(i)).getId_etage().toUpperCase(),((Chambre) donnees.get(i)).getId_chambre().toUpperCase(),((Chambre) donnees.get(i)).getNum(),((Chambre) donnees.get(i)).getEtat(),((Chambre) donnees.get(i)).getReservee(),resi};
            view.modelll.addRow(o);
        }

    }


}
