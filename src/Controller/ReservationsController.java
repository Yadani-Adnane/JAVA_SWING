package Controller;

import Model.Bd;
import View.Reservations;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ReservationsController {

    Reservations view;
    Bd model;
    int rowIndex;
    public ReservationsController(Reservations reservations, Bd model) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.view = reservations;
        this.model = model;
        chargerReservation();
        view.addtableactionlestner(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (view.table.getSelectedRow() >-1){
                        view.annuler.setVisible(true);
                        view.annuler.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                view.table.clearSelection();
                                view.id.setText("");
                                view.id1.setText("");
                                view.prix.setText("");
                                view.chercher1.requestFocus();
                                view.annuler.setVisible(false);
                            }
                        });
                        view.form1.revalidate();
                        view.form1.repaint();
                        rowIndex = view.table.getSelectedRow();
                        view.id.setText(view.table.getValueAt(rowIndex, 2).toString());
                        view.id1.setText(view.table.getValueAt(rowIndex, 3).toString());
                        view.prix.setText(view.table.getValueAt(rowIndex, 4).toString());
                    }
                }
            }
        });
        view.addpayeractionlestner(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (view.id.getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Selectionnez une réservation!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Reservation r = new Reservation(Integer.parseInt(view.table.getValueAt(rowIndex, 0).toString()),view.table.getValueAt(rowIndex, 1).toString(),view.table.getValueAt(rowIndex, 2).toString(),view.table.getValueAt(rowIndex, 3).toString(),Integer.parseInt(view.table.getValueAt(rowIndex, 5).toString()),"",Double.parseDouble(view.table.getValueAt(rowIndex, 4).toString()));

                    String f = view.table.getValueAt(rowIndex, 0).toString()+".txt";
                    File file = new File(f);
                    try {
                        // Créer le fichier
                        if (file.createNewFile()) {
                            System.out.println("Le fichier a été créé !");
                        } else {
                            System.out.println("Le fichier existe déjà.");
                        }
                    } catch (IOException e) {
                        System.out.println("Une erreur s'est produite lors de la création du fichier.");
                        e.printStackTrace();}
                    try (FileWriter writer = new FileWriter(f)) {
                        // Écrire le contenu dans le fichier
                        writer.write("Résident : "+r.getId_residant()+"\n"+"Chambre :"+r.getId_chambre()+"\n"+"Date :"+r.getDate_reservation()+"\n"+"Prix :"+r.getFrais_de_reservation());
                        System.out.println("Le contenu a été écrit avec succès dans le fichier.");
                    } catch (IOException e) {
                        System.out.println("Une erreur s'est produite lors de l'écriture dans le fichier.");
                        e.printStackTrace();
                    }
                    if (Desktop.isDesktopSupported() && file.exists()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            // Ouvrir le fichier avec l'application par défaut
                            desktop.open(file);
                        } catch (IOException e) {
                            System.out.println("Une erreur s'est produite lors de l'ouverture du fichier.");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Le bureau n'est pas pris en charge ou le fichier n'existe pas.");
                    }
                    try {
                        r.payer(f);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    view.table.clearSelection();
                    view.id.setText("");
                    view.id1.setText("");
                    view.prix.setText("");
                    view.chercher1.requestFocus();
                    view.annuler.setVisible(false);
                    view.form1.revalidate();
                    view.form1.repaint();
                    try {
                        chargerReservation();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        view.addchercheractionlestner(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(view.chercher1.getText().equals("") || view.chercher1.getText().equals("Chercher par ID")) {
                        JOptionPane.showMessageDialog(view, "Entrez un ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else {

                            view.annuler.setVisible(true);
                            view.annuler.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    view.table.clearSelection();
                                    view.id.setText("");
                                    view.id1.setText("");
                                    view.prix.setText("");
                                    view.chercher1.requestFocus();
                                    view.annuler.setVisible(false);
                                    try {
                                        chargerReservation();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            view.annuler.setVisible(true);
                            view.form1.revalidate();
                            view.form1.repaint();
                            try {
                                view.model.setRowCount(0);
                                int n = (int) model.selectFun("COUNT","reservation","*","id_residant = '"+view.chercher1.getText().toUpperCase()+"' and payee = 0");
                                System.out.println(n);
                                if(n <=0){
                                    JOptionPane.showMessageDialog(view, "Le résidant "+view.chercher1.getText()+" à payé toutes ses réservations", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                for (int i = 0; i < n; i++) {
                                    Reservation r = (Reservation) model.select("*","Reservation","id_residant = '"+view.chercher1.getText().toUpperCase()+"' and payee = 0").get(i);
                                    Object[] o= {r.getId_reservation(),r.getDate_reservation(),r.getId_residant(),r.getId_chambre(),r.getFrais_de_reservation(),r.getPayee()};
                                    view.model.addRow(o);
                                }
                            } catch (Exception e) {

                            }
                    }


            }
        });
    }
    public void chargerReservation() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        view.model.setRowCount(0);
        view.id.setEditable(false);
        view.id1.setEditable(false);
        view.prix.setEditable(false);
        List<Object> donnees=model.select("*","Reservation","payee = 0");
        for (int i = 0; i < donnees.size(); i++) {
            Object[] o= {((Reservation) donnees.get(i)).getId_reservation(),((Reservation) donnees.get(i)).getDate_reservation(),((Reservation) donnees.get(i)).getId_residant(),((Reservation) donnees.get(i)).getId_chambre(),((Reservation) donnees.get(i)).getFrais_de_reservation(),((Reservation) donnees.get(i)).getPayee()};
            view.model.addRow(o);
        }
    }
}
