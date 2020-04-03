package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager applicationManager) {
    final StandardServiceRegistry regidtry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(regidtry).buildMetadata().buildSessionFactory();
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where not username = 'administrator'").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

}
