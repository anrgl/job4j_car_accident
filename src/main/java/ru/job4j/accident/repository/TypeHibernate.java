package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class TypeHibernate {
    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType", AccidentType.class).list();
        }
    }
}
