/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import Film.Film;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Neko
 */

@Entity
@NamedQuery(name = Location.FIND_ALL, query = "SELECT l FROM Location l")

public class Location {
    public final static String FIND_ALL = "Location.findAll";
    public final static String Del_SOM = "Location.delete";
    
    
    public Location(){
        
    }
     
    
    @Id
    @GeneratedValue
    private Long numero;
    private Long client;
    private Long film;
    private String dateDebut="";
    private String dateFin="";
    private String etat="";

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getFilm() {
        return film;
    }

    public void setFilm(Long film) {
        this.film = film;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "-  " + this.film + " du "+this.dateDebut+" au "+this.dateFin;
    }
    
    
}
