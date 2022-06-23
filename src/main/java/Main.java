import entity.CommandeEntity;
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
            String selectLastCommand = "SELECT date, produit FROM " + CommandeEntity.class.getName() + " WHERE client = 1 ORDER BY date DESC LIMIT 1";
            String selectAllCommands = "SELECT date, produit FROM " + CommandeEntity.class.getName() + " WHERE client = 1";

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

}
