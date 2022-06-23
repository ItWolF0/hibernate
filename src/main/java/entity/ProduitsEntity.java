package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "produits", schema = "tp_hibernate", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class ProduitsEntity {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prix")
    private double prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitsEntity that = (ProduitsEntity) o;
        return id == that.id && Double.compare(that.prix, prix) == 0 && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prix);
    }
}
