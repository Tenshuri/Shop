package shop.beans;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class Produits implements Serializable {

    private List<Produit> produits;

    public Produits() {}

    public Produits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void addProduit(Produit p) {
        produits.add(p);
    }

    // Sérialiser un ensemble de produits en string (Pour le panier)
    public static String toString(Serializable produits) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(ba);
        os.writeObject(produits);
        os.close();
        return Base64.getEncoder().encodeToString(ba.toByteArray());
    }

    // Désérialiser un ensemble de produits
    public static Produits fromString(String s) throws IOException , ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o  = ois.readObject();
        ois.close();
        return (Produits) o;
    }
}
