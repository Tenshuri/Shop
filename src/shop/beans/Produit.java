package shop.beans;

import java.io.Serializable;

public class Produit implements Serializable {

    private int num;
    private String nom;
    private int prix;
    private String photo;

    public Produit() {}

    public Produit(int num, String nom, int prix, String photo) {
        this.num = num;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
}
