/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biblio.business;

import Location.Panier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Neko
 */
@Stateless
public class PanierEJB {
    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Panier> findAll() {
        Query query = em.createNamedQuery(Panier.FIND_ALL);
        return query.getResultList();
    }

    public Panier create(Panier panier) {
        em.persist(panier);
        return panier;
    }

    public Panier update(Panier panier) {
        return em.merge(panier);
    }

    public void delete(List<Panier> list) {
        for (Panier panier : list) {
            delete(panier);
        }
    }

    public void delete(Panier panier) {
        em.remove(em.merge(panier));
    }
}
