package webservicetwo.webservicetwo;

import org.hibernate.Session;

public class HibernateDao {

    public static void add(Object o) {
        try {
            System.err.println("%% Adding "+o.getClass().getSimpleName()+" in DB: " + o.toString());
            Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(Object o) {
        System.err.println("%% Deleting " + o.getClass().getSimpleName() + "in DB: " + o.toString());
        Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
    }

//    public static Object retrieve(String bean, String id) {
//       try {
//           BeanFactory context = new ClassPathXmlApplicationContext(IHibernate.appContext);
//           Object className = context.getBean(bean);
//
//           org.hibernate.classic.Session session = HibernateSessionHelper.getSessionFactory().getCurrentSession();
//           session.beginTransaction();
//
//           Object obj = session.get(className.getClass(), id);
//
//           session.getTransaction().commit();
//           return obj;
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//
//       return null;
//    }

}