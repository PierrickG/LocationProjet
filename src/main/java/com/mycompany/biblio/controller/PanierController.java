/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biblio.controller;

import Film.Film;
import Location.Location;
import Location.Panier;
import com.mycompany.biblio.business.PanierEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Neko
 */
@ManagedBean
@SessionScoped
public class PanierController {
    @EJB
    private PanierEJB panierEJB;
    private Location location = new Location();
    private int nombreDeLoaction = 0;
    private String identifiant ="22";
    private Panier panier= new Panier();

    public ListDataModel getLocationList() {
        updateLocationList();
        return locationList;
    }

    public void setLocationList(ListDataModel locationList) {
        this.locationList = locationList;
    }
    private ListDataModel locationList=new ListDataModel();

    public int getNombreDeLoaction() {
        return nombreDeLoaction;
    }

    public void setNombreDeLoaction(int nombreDeLoaction) {
        this.nombreDeLoaction = nombreDeLoaction;
    }
    //
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public String DoLocation(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long Id = Long.parseLong(req.getParameter("Id"));
        location.setFilm(Id);
        return "location.xhtml";
    }
    public String obtenirTitreFilm(){
        return titreFilm(location);
    }
    public String doCreate() {
        location.setClient(Long.parseLong(identifiant));
        location.setEtat("panier");
        Query query = em.createNamedQuery(Panier.FIND_ALL);
        List<Panier> panierList =  query.getResultList();
        panier = new Panier(location.getClient(),panierList);
        panier.add(location);
        int i=0;
        boolean continu = true;
        while(panierList.size()>i &&  continu){
            if(panierList.get(i).getClient()!=location.getClient())
                i++;
            else
                continu = false;
        }
        if((i<panierList.size()) ){
            if(panierList.get(i).getClient().equals(location.getClient())){
                panier = panierEJB.update(panier);
            }else{
            panier = panierEJB.create(panier);}
        }else{
            panier = panierEJB.create(panier);
        }
        nombreDeLoaction = panier.getTab_Location().size();
        return "index.xhtml";
    }
    public String nbInPanier()
    {
        location.setClient(Long.parseLong(identifiant));
        Query query = em.createNamedQuery(Panier.FIND_ALL);
        List<Panier> panierList =  query.getResultList();
        panier = new Panier(location.getClient(),panierList);
        return "" + panier.getTab_Location().size();
    } 
    public String voirPanier()
    {
        return "panier.xhtml";
    }
    private void updateLocationList() {
        location.setClient(Long.parseLong(identifiant));
        Query query = em.createNamedQuery(Panier.FIND_ALL);
        List<Panier> panierList =  query.getResultList();
        panier = new Panier(location.getClient(),panierList);
        locationList = new ListDataModel(panier.getTab_Location());
    }
    public String titreFilm(Location l){
        Query query = em.createNamedQuery(Film.FIND_ALL);
        List<Film> filmList =  query.getResultList();
        int i=0;
        while(!(filmList.get(i).getIdFilm().equals(l.getFilm())) && i<filmList.size())i++;
        if(filmList.get(i).getIdFilm().equals(l.getFilm()) && filmList.size()>0)
            return filmList.get(i).getNom();
        else
            return "Erreur!";
    }
    public void doAnnuler(Location l){
        panier.supp(l);
        panierEJB.update(panier);
    }
            
    
}
