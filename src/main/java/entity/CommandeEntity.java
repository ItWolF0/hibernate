package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commande", schema = "tp_hibernate", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class CommandeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Timestamp date;
    @Column(name = "produit")
    private ProduitsEntity produit;
    @Column(name = "client")
    private ClientEntity client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public ProduitsEntity getProduit() {
        return produit;
    }

    public void setProduit(ProduitsEntity produit) {
        this.produit = produit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandeEntity that = (CommandeEntity) o;
        return id == that.id && produit == that.produit && client == that.client && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, produit, client);
    }
}
