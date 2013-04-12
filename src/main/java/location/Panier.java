/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;

/**
 *
 * @author Neko
 */
@Entity
@NamedQuery(name = Panier.FIND_ALL, query = "SELECT p FROM Panier p")
public class Panier {
    public final static String FIND_ALL = "Panier.findAll";
    public final static String Del_SOM = "Panier.delete";
    
    public Panier(Long client,List<Panier> panierList){
        this.client = client;     
        ArrayList <Location> liste_Location = new ArrayList <Location>();
        int i=0;
        while(panierList.size()>i && client != panierList.get(i).getClient())i++;
        if(panierList.size()!=i && panierList.size()>0){
            Panier p = panierList.get(i);
            this.tab_Location = p.tab_Location;
        }else{
            this.tab_Location=liste_Location;
        }        
    }
    public Panier(){
        
    }    
        
        
    @Id
    private Long client;

    public void add(Location l){
        tab_Location.add(l);
    }
    public void supp(Location l){
        int i=0;
        while(tab_Location.size()>i && !(l.getNumero().equals(tab_Location.get(i).getNumero())))i++;
        if(i<tab_Location.size())
            if(l.getNumero().equals(tab_Location.get(i).getNumero()))
                tab_Location.remove(i);
    }
    
    
    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public List<Location> getTab_Location() {
        return tab_Location;
    }

    public void setTab_Location(List<Location> tab_Location) {
        this.tab_Location = tab_Location;
    }
    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> tab_Location;
}
