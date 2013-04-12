package com.mycompany.biblio.business;


import Film.*;
import java.util.List;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @author Antonio Goncalves
 *         APress User - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
public class UserEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<User> findAll() {
        Query query = em.createNamedQuery(User.FIND_ALL);
        return query.getResultList();
    }

    public User create(User user) {
        em.persist(user);
        return user;
    }

    public User update(User user) {
        return em.merge(user);
    }

    public void delete(List<User> list) {
        for (User user : list) {
            delete(user);
        }
    }

    public void delete(User user) {
        em.remove(em.merge(user));
    }
}
