package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(int series, String name) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where series =:series AND name = :name");
      query.setParameter("series", series);
      query.setParameter("name", name);
      Car car = query.getSingleResult();
      return car.getUser();
   }

   @Override
   public void deleteAllUsers() {
      sessionFactory.getCurrentSession().createQuery("DELETE FROM User");
   }

   @Override
   public User getUser(String firstName, String lastName) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where firstName =:name AND lastName = :last_name");
      query.setParameter("firstName", firstName);
      query.setParameter("lastName", lastName);
      User user = query.getSingleResult();
      return user;
   }

}
