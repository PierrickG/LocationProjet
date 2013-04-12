package Film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe Personne (impl�mente l'interface Comparateur).
 * D�finit les diff�rentes m�thodes associ�es aux personnes (Acteur et Realisateur) d'un Film et ses constructeurs
 * 
 * @author Pierrick GUINET & Fran�ois LOZE
 * @version 7.0
 */
@Entity
public class Realisateur implements Comparateur <Realisateur>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /** Nom de la personne **/
    protected String nom;
    /** Pr�nom de la personne **/
    protected String prenom;
    
    /**
     * Constructeur par initialisation de la classe Personne
     * @param nom Nom de la Personne
     * @param prenom Pr�nom de la Personne
     */
    public Realisateur(String nom, String prenom)
    {
        this.nom = nom;
        this.prenom = prenom;             
    }
    
    /**
     * Constructeur par recopie de la classe Personne
     * @param p Personne � recopier
     */
    public Realisateur(Acteur p)
    {
        this(p.nom,p.prenom);	// appel au constructeur par recopie avec le nom et le prenom de p (le param�tre)
    }
    
    /**
     * Constructeur par d�faut de la classe Personne.
     * Sp�cifie un nom et un prenom par d�faut (vide)
     */
    public Realisateur()
    {
    	this.nom = "";
        this.prenom = "";
    }

    @Override
    public String toString() {
        
        String nom = this.nom;
        String prenom = this.prenom;
        
        char[] nom_array = nom.toCharArray();
        nom_array[0] = Character.toUpperCase(nom_array[0]);
        nom = new String(nom_array);
        
        char[] prenom_array = prenom.toCharArray();
        prenom_array[0] = Character.toUpperCase(prenom_array[0]);
        prenom = new String(prenom_array);
        
        return nom+" "+prenom;
    }
    
    // Ensemble d'accesseurs et de modifieurs de la classe Personne
    
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
    
    /**
     * M�thode de comparaison de deux personnes.
     * Utilis�e dans la classe Personnes pour comparer deux Personne (Acteur ou Realisateur)
     * @param obj La personne � comparer au courant
     * @return Le pourcentage de similarit�
     */
    public float comparer(Realisateur obj)
    {
        float pourcentage = 0;
        
        if(((Realisateur)obj).nom.toLowerCase().compareTo(this.nom.toLowerCase()) == 0) pourcentage = 100;		// si le nom cherch� est �gal au nom compar�, pourcentage de 100
        else if(((Realisateur)obj).nom.toLowerCase().contains(this.nom.toLowerCase())) pourcentage = 80;		// si le nom du film compar� contient le nom du film cherch�, pourcentage de 80
        
        else if(((Realisateur)obj).prenom.toLowerCase().compareTo(this.nom.toLowerCase()) == 0) pourcentage = 100;		// si le prenom cherch� est �gal au prenom compar�, pourcentage de 100
        else if(((Realisateur)obj).prenom.toLowerCase().contains(this.nom.toLowerCase())) pourcentage = 80;			// si le prenom du film compar� contient le prenom du film cherch�, pourcentage de 80
        
        else pourcentage = 0;	// sinon, pourcentage de 0
        
        return pourcentage;
    }
    public static Realisateur[] TransformeStringRealisateurNom(String[] split) 
	{
		assert(split.length != 0);	// test que la chaine � d�couper n'est pas �gale � 0
		
		Realisateur [] rel =  new Realisateur[split.length];
		
		for(int i = 0; i < rel.length; i++)
		{
			String tabS[] = split[i].split(", ");
			
			if(tabS.length > 1)
				rel[i] = new Realisateur(tabS[0],tabS[1]);
			else
				rel[i] = new Realisateur(split[i],"");
		}
		
		return rel;
	}
}