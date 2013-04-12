package Film;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe Genre (implemente l'interface Comparateur).
 * D�finit les differentes m�thodes associ�es au genre d'un Film et ses constructeurs
 * 
 * @author Pierrick GUINET & Fran�ois LOZE
 * @version 7.0
 */
@Entity
public class Genre implements Comparateur <Genre>{
	
	/** Intitul� du genre **/
    @Id
    @GeneratedValue
    private int idGenre;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    private String nom;
    
    /**
     * Constructeur par d�faut de la classe Genre
     */
    public Genre()
    {
        
    }
    
    /**
     * Constructeur par initialisation de la classe Genre
     * @param nom Nom du genre
     */
    public Genre(String nom)
    {
        this.nom = nom;
    }
    
    /**
     * Constructeur par recopie de la classe Genre
     * @param g Genre � recopier
     */
    public Genre(Genre g)
    {
        this.nom = g.nom;
    }

    // Ensemble d'accesseurs et de modifieurs de la classe Genre
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
   
    /**
     * M�thode de comparaison de deux genres.
     * Utilis�e dans la classe Film pour comparer les genres de deux films
     * @param obj Le genre � comparer au courant
     * @return Le pourcentage de similarit�
     */
    public float comparer(Genre obj)
    {
        float pourcentage = 0;
        
        if(this.nom.equals(obj.nom)) pourcentage = 100;	// si le nom des genres est identique, pourcentage de 100
        else pourcentage = 0;	//sinon, pourcentage de 0
        
        return pourcentage;
    }
    
	/**
	 * M�thode de surcharge de la fonction toString pour la classe Genre.
	 * La chaine est alors compos�e du nom du genre
	 */
    @Override
   	public String toString()
    {
   		return this.nom;
   	}
}