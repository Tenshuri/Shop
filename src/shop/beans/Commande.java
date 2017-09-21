package shop.beans;

import java.io.Serializable;

// Attention : il y a ici un montant total, mais pas dans la base
public class Commande implements Serializable {

    private int num;
    private int etat;
    private int mtTotal;
    private int numClient;

    public Commande() {}

    public Commande(int num, int etat, int mtTotal, int numClient) {
        this.num = num;
        this.etat = etat;
        this.mtTotal = mtTotal;
        this.numClient = numClient;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getMtTotal() {
        return mtTotal;
    }

    public void setMtTotal(int mtTotal) {
        this.mtTotal = mtTotal;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }
}
