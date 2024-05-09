package Controller;

import Model.Bd;

public class Reservation {
    private int id_reservation;
    private String date_reservation;
    private String id_residant;
    private String id_chambre;
    private int payee;
    private String recu;
    private double frais_de_reservation;

    public Reservation(int id_reservation, String date_reservation, String id_residant, String id_chambre, int payee, String recu, double frais_de_reservation) {
        this.id_reservation=id_reservation;
        this.date_reservation=date_reservation;
        this.id_residant = id_residant;
        this.id_chambre=id_chambre;
        this.payee=payee;
        this.recu=recu;
        this.frais_de_reservation=frais_de_reservation;
    }

    public void payer() throws IllegalAccessException {
        this.payee=1;
        Bd b = new Bd();
        b.delete(this);
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public String getId_residant() {
        return id_residant;
    }

    public String getId_chambre() {
        return id_chambre;
    }

    public int getPayee() {
        return payee;
    }

    public String getRecu() {
        return recu;
    }

    public double getFrais_de_reservation() {
        return frais_de_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setId_residant(String id_residant) {
        this.id_residant = id_residant;
    }

    public void setId_chambre(String id_chambre) {
        this.id_chambre = id_chambre;
    }

    public void setPayee(int payee) {
        this.payee = payee;
    }

    public void setRecu(String recu) {
        this.recu = recu;
    }

    public void setFrais_de_reservation(double frais_de_reservation) {
        this.frais_de_reservation = frais_de_reservation;
    }
}
