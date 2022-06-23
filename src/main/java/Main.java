import entity.ClientEntity;
import entity.CommandeEntity;
import entity.ProduitsEntity;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            String nomClientInsert = "Frenea";
            String prenomClientInsert = "Marylene";
            String telephoneClientInsert = "0708453669";
            String nomProduit = "Pain au chocolat";
            float prixProduit = 1.20f;
            String nomProduitCommande = "Pain au chocolat";
            String nomClientCommande = "Frenea";

            ProduitsEntity produitCommande = getProduct(nomProduitCommande, session);
            ClientEntity clientCommande = getClient(nomClientCommande, session);

            ClientEntity client = new ClientEntity();
            client.setNom(nomClientInsert);
            client.setPrenom(prenomClientInsert);
            client.setTelephone(telephoneClientInsert);
            session.persist(client);
            session.getTransaction().commit();

            String selectLastCommand = "SELECT co.date, co.produit FROM " + CommandeEntity.class.getName() + " co, " + ClientEntity.class.getName() + " cl, WHERE cl.nom = " + nomClient + " AND co.client = cl.id ORDER BY date DESC LIMIT 1";
            String selectAllCommands = "SELECT co.date, co.produit FROM " + CommandeEntity.class.getName() + " co, " + ClientEntity.class.getName() + " cl, WHERE cl.nom = " + nomClient + "";

            Query<CommandeEntity> querySelectLastCommand = session.createQuery(selectLastCommand);
            Query<CommandeEntity> querySelectAllCommands = session.createQuery(selectAllCommands);

            List<CommandeEntity> lastCommands = querySelectLastCommand.getResultList();
            List<CommandeEntity> allCommands = querySelectAllCommands.getResultList();

            System.out.println(lastCommands.get(0));
            System.out.println(allCommands.get(0));
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
        }
    }

    public static ClientEntity getClient(String nomClient, Session session) {
        String sql = "SELECT c FROM " + ClientEntity.class.getName() + " c WHERE c.nom = :nomClient";
        Query<ClientEntity> query = session.createQuery(sql);
        query.setParameter("nomClient", nomClient);
        return (ClientEntity) query.getSingleResult();
    }

    public static ProduitsEntity getProduct(String nomProduit, Session session) {
        String sql = "SELECT p FROM " + ProduitsEntity.class.getName() + " p WHERE p.nom = :nomProduit";
        Query<ProduitsEntity> query = session.createQuery(sql);
        query.setParameter("nomProduit", nomProduit);
        return (ProduitsEntity) query.getSingleResult();
    }

}
