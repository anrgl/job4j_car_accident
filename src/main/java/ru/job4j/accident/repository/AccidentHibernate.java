package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
        }
    }

    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(
                            "select distinct a from Accident a join fetch a.rules order by a.id",
                            Accident.class)
                    .list();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Accident where id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
}
