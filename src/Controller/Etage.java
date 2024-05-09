package Controller;

import Model.Bd;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Etage {
    private String id_etage;
    private int etat;
    private String id_batiment;
    private int num;
    private List<Chambre> chambres = new ArrayList<>();

    public Etage(String id_etage, int etat, String id_batiment, int num) {
        this.id_etage = id_etage;
        this.etat = etat;
        this.id_batiment = id_batiment;
        this.num = num;
    }

    public void ajouter(Bd bd){

        bd.insert(this);
    }

    public void supprimer(Bd bd) throws IllegalAccessException {

        bd.delete(this);
    }

    public void modifier(Bd bd,String id_etage) throws IllegalAccessException {

        bd.update(this,id_etage);
    }

    public static Etage chercher(String id_etage,Bd bd ) throws IllegalAccessException, SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        if( bd.select("*","Etage","id_etage='"+id_etage+"';").size()>0){
            return (Etage) bd.select("*","Etage","id_etage='"+id_etage+"';").get(0);
        }else {
            return null;
        }

    }

    public void activer(){
        this.etat=1;
    }

    public void desactiver(){
        this.etat=0;
    }

    public void ajouterChambre(Chambre chambre){
        this.chambres.add(chambre);
    }

    public void supprimerChambre(Chambre chambre){
        this.chambres.remove(chambre);
    }

    public String getId_etage() {
        return id_etage;
    }

    public int getEtat() {
        return etat;
    }

    public String getId_batiment() {
        return id_batiment;
    }

    public int getNum() {
        return num;
    }
    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setId_etage(String id_etage) {
        this.id_etage = id_etage;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setId_batiment(String id_batiment) {
        this.id_batiment = id_batiment;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    @Override
    public String toString() {
        return "Etage{" +
                "id_etage='" + id_etage + '\'' +
                ", etat=" + etat +
                ", id_batiment='" + id_batiment + '\'' +
                ", num=" + num +
                ", chambres=" + chambres +
                '}';
    }
}
