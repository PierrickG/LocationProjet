/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Film;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u")
public class User implements Serializable {
    public final static String FIND_ALL = "User.findAll";
    public final static String Del_SOM = "User.delete";
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="email")
    private String email;
    @Transient
    private Boolean selected;

    public User()
    {
        
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSelected() {
        return selected;
    }
    
    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}