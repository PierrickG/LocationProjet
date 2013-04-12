package Film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe Tag (impl�mente l'interface Comparateur).
 * D�finit les diff�rentes m�thodes associ�es au tag d'un Film et ses constructeurs
 * 
 * @author Pierrick GUINET & Fran�ois LOZE
 * @version 7.0
 */
@Entity
public class Tag implements Comparateur <Tag>{
	
    
    @Id
    @GeneratedValue
    private Long idTag;

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }
	/** Intitul� du tag **/
    private String nom;

    /**
     * Constructeur par d�faut de la classe Tag
     */
    public Tag()
    {
    }
    
    /**
     * Constructeur par initialisation de la classe Genre
     * @param nom Nom du tag
     */
    public Tag(String nom)
    {
        this.nom = nom;
    }
    
    /**
     * Constructeur par recopie de la classe Genre
     * @param y Tag � recopier
     */
    public Tag(Tag t)
    {
        this.nom = t.nom;
    }
    
    // Ensemble d'accesseurs et de modifieurs de la classe Tag
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * M�thode de comparaison de deux tags.
     * Utilis�e dans la classe Film pour comparer les tags de deux films
     * @param obj Le genre � comparer au courant
     * @return Le pourcentage de similarit�
     */
    public float comparer(Tag obj)
    {
        float pourcentage = 0;
        
        if(this.nom.toLowerCase().equals(obj.nom.toLowerCase())) pourcentage = 100;	// si le nom des tags est identique, pourcentage de 100
        else pourcentage = 0;		//sinon, pourcentage de 0
        
        return pourcentage;
    }
    
	/**
	 * M�thode de surcharge de la fonction toString pour la classe Tag.
	 * La chaine est alors compos�e du nom du tag
	 */
        @Override
	public String toString() 
        {
	return this.nom;
	}
}
