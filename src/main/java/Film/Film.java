package Film;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * Classe Film (impl�mente l'interface Comparateur).
 * D�finit les diff�rentes m�thodes pour la comparaison de deux films et la construction d'un Film
 * 
 * @author Pierrick GUINET & Fran�ois LOZE
 * @version 7.0
 */
@Entity
@NamedQuery(name = Film.FIND_ALL, query = "SELECT f FROM Film f")

public class Film implements Comparateur <Film>{
    public final static String FIND_ALL = "Film.findAll";
    public final static String Del_SOM = "Film.delete";
	
    
    /**
     * Constructeur par initialisation de la classe Film.
	 * Construit l'objet Film via ses caract�ristiques pass�es en param�tre.
	 * Le film est construit par clonage des acteurs, genres, tags, et r�alisateur(s) et avec les attributs
     * @param idFilm ID du film (num�ro d'identification, issu de la BDD)
     * @param nom Titre du film
     * @param annee Ann�e de sortie du film
     * @param duree Dur�e du film
     * @param troisD Disponilibit� en 3D du film
     * @param tab_Acteur Tableau des acteurs jouant de le film
     * @param tab_Realisateur Tableau du ou des r�alisateur(s) ayant fait le film
     * @param tab_Genre Tableau des genres carac�risant le film
     * @param tab_Tag Tableau des tags d�crivant le film
     */
    public Film(Long idFilm, String nom, int annee, int duree, int troisD, ArrayList<Acteur> tab_Acteur, ArrayList<Realisateur> tab_Realisateur, ArrayList<Genre> tab_Genre, ArrayList<Tag> tab_Tag)
    {
    	this.idFilm = idFilm;
        this.nom = nom;
        this.annee = annee;
        this.duree = duree;
        this.troisD = troisD;
        if(tab_Acteur != null)
        	this.tab_Acteur = (List)tab_Acteur.clone();
        if(tab_Genre != null)
        	this.tab_Genre = (List)tab_Genre.clone();
        if(tab_Realisateur != null)
        	this.tab_Realisateur = (List)tab_Realisateur.clone();
        if(tab_Tag != null)
        	this.tab_Tag = (List)tab_Tag.clone();
    }
     public Film( String nom, int annee, int duree, int troisD, ArrayList<Acteur> tab_Acteur, ArrayList<Realisateur> tab_Realisateur, ArrayList<Genre> tab_Genre, ArrayList<Tag> tab_Tag)
    {
        this.nom = nom;
        this.annee = annee;
        this.duree = duree;
        this.troisD = troisD;
        this.tab_Acteur = tab_Acteur;
        this.tab_Genre = tab_Genre;
        this.tab_Realisateur = tab_Realisateur;
        this.tab_Tag = tab_Tag;
    }
    
    
    /**
     * Constructeur par initialisation de la classe Film.
	 * Construit l'objet Film via ses caract�ristiques pass�es en param�tre.
	 * Le film est construit par clonage des acteurs, genres, tags, et r�alisateur(s) et avec les attributs
     * @param nom Titre du film
     * @param annee Ann�e de sortie du film
     * @param duree Dur�e du film
     * @param troisD Disponilibit� en 3D du film
     * @param tab_Acteur Tableau des acteurs jouant de le film
     * @param tab_Realisateur Tableau du ou des r�alisateur(s) ayant fait le film
     * @param tab_Genre Tableau des genres carac�risant le film
     * @param tab_Tag Tableau des tags d�crivant le film
     */
    public Film(String nom, int annee, int duree, int troisD, ArrayList<Acteur> tab_Acteur, ArrayList<Realisateur> tab_Realisateur, ArrayList<Genre> tab_Genre)
    {
        this.nom = nom;
        this.annee = annee;
        this.duree = duree;
        this.troisD = troisD;
        
        if(tab_Acteur != null)
        	this.tab_Acteur = (List)tab_Acteur.clone();
        if(tab_Genre != null)
        	this.tab_Genre = (List)tab_Genre.clone();
        if(tab_Realisateur != null)
        	this.tab_Realisateur = (List)tab_Realisateur.clone();

    }
    
    /**
     * Constructeur par initialisation de la classe Film.
	 * Construit l'objet Film via ses caract�ristiques pass�es en param�tre (mis dans les attributs)
     * @param idFilm ID du film (num�ro d'identification, issu de la BDD)
     * @param nom Titre du film
     * @param annee Ann�e de sortie du film
     * @param duree Dur�e du film
     * @param troisD Disponilibit� en 3D du film
     */
    public Film(Long idFilm, String nom, int annee, int duree, int troisD)
    {
    	this.idFilm = idFilm;
        this.nom = nom;
        this.annee = annee;
        this.duree = duree;
        this.troisD = troisD;
    }
    
    /**
     * Constructeur par d�faut de la classe Film
     */
    public Film()
    {
    }
    
    // Ensemble d'accesseurs et de modifieurs de la classe Film
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isTroisD() {
        return (troisD==1);
    }

    public boolean getTroisD() {
        return (troisD==1);
    }

    public void setTroisD(int troisD) {
        this.troisD = troisD;
    }

    public List<Acteur> getTab_Acteur() {
        return tab_Acteur;
    }

    public void setTab_Acteur(ArrayList<Acteur> tab_Acteur) {
        this.tab_Acteur = (List)tab_Acteur;
    }

    public List<Realisateur> getTab_Realisateur() {
        return tab_Realisateur;
    }

    public void setTab_Realisateur(ArrayList<Realisateur> tab_Realisateur) {
        this.tab_Realisateur = (List)tab_Realisateur.clone();
    }

    public List<Tag> getTab_Tag() {
        return tab_Tag;
    }

    public void setTab_Tag(ArrayList<Tag> tab_Tag) {
        this.tab_Tag = (List)tab_Tag.clone();
    }

    public List<Genre> getTab_Genre() {
        return tab_Genre;
    }

    public void setTab_Genre(ArrayList<Genre> tab_Genre) {
        this.tab_Genre = tab_Genre;
    }
    
    public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
    
	public Long getIdFilm() {
		return idFilm;
	}
	
    public void setNbClick(int nbClick) {
		this.nbClick = nbClick;
	}
    
	public int getNbClick() {
		return nbClick;
	}
	
   
    /** ID du film (num�ro identifiant) **/
    @Id
    @GeneratedValue
    private Long idFilm;
    /** Titre du film **/
    private String nom;
    /** Ann�e de sortie du film **/
    private int annee;
    /** Dur�e du film **/
    private int duree;
    /** Disponilit� en 3D du film **/
    private int troisD;
    
    @Transient
    private boolean threeD;

    public boolean isThreeD() {
        return threeD;
    }

    public void setThreeD(boolean threeD) {
        this.threeD = threeD;
    }
    
    /** Tableau d'Acteur du film **/
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Acteur> tab_Acteur;
    /** Tableau de Realisateur du film **/
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Realisateur> tab_Realisateur;
    
     @Transient
    private String realisateur;

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }
    
    @Transient
    private String acteur;

    public String getActeur() {
        return acteur;
    }

    public void setActeur(String acteur) {
        this.acteur = acteur;
    }
    
    /** Tableau de Tag du film **/
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tab_Tag;
    /** Tableau de Genre du film **/
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Genre> tab_Genre;
    
    @Transient
    private List<String> genre = new ArrayList<String>();

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
    
    /** Nombre de clics obtenus par un film **/
    private int nbClick = 0;
    /** Tableau des coefficients ; d�finit l'importance de chaque crit�re **/  
    @Transient    
    private boolean selected;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean getSelected() {
        return this.selected ;
    }
    public boolean isSelected() {
        return selected;
    }
	/**
	 * M�thode de comparaison de Film.
	 * Compare l'objet appelant avec lui pass� en param�tre.
	 * Effectue une comparaison pour chaque attribut, et en calcul un pourcentage de similarit�
	 * @param obj Le Film � comparer
	 * @return Le pourcentage de similarit� du Film compar� (0 si aucun crit�re choisi)
	 */
    public float comparer(Film obj)
    {
        int [] tab_Coef = new int [] {5,1,1,2,4,3,4};	// nom, annee, duree, 3d, acteur, genre, realisateur
    	// variable pour sommer les choix pond�r�s par leur coefficient
        int nbChoix = 0;
        // variable pour le pourcentage de similarit�
        float pourcentage = 0;
        
        assert(obj != null);
        
        /** Comparaison de l'ann�e **/
        if(this.annee > 0)
        {
            pourcentage += comparerAnnee(obj.annee)*tab_Coef[1];
            nbChoix += tab_Coef[1];
        }
        
        /** Comparaison de la disponibilit� en 3D **/
        if(this.troisD==1)
        {
        	pourcentage += this.comparerTroisD(obj.troisD)*tab_Coef[3];
        	nbChoix += tab_Coef[3];
        }
        
        /** Comparaison de la dur�e **/
        if(this.duree != 0)
        {
	        pourcentage += this.comparerDuree(obj.duree)*tab_Coef[2];
	        nbChoix += tab_Coef[2];
        }
        
        /** Comparaison du titre **/
        if(!this.nom.equals(""))
        {
            pourcentage += this.comparerTitre(obj.nom)*tab_Coef[0];
            nbChoix += tab_Coef[0];
        }
        
        /** Comparaison des acteurs **/
        if(this.tab_Acteur != null)
        {
            pourcentage += compareTableau((ArrayList)this.tab_Acteur, (ArrayList)obj.tab_Acteur)*tab_Coef[4];
            nbChoix += tab_Coef[4];
        }
        
        /** Comparaison des r�alisateurs **/
        if(this.tab_Realisateur != null)
        {
            pourcentage += compareTableau((ArrayList)this.tab_Realisateur, (ArrayList)obj.tab_Realisateur)*tab_Coef[6];
            nbChoix += tab_Coef[6];
        }
        
        /** Comparaison des genres **/
        if(this.tab_Genre != null)
        {
            pourcentage += compareTableau((ArrayList)this.tab_Genre, (ArrayList)obj.tab_Genre)*tab_Coef[5];
            nbChoix += tab_Coef[5];
        }
        
        /** Si au moins 1 choix de crit�re a �t� r�alis� par l'utilisateur, on retourne le coefficient de similarit� **/
        if(nbChoix != 0)
        {
        	return pourcentage / nbChoix;	// pourcentage de similarit� divis� par la somme des coefficients correspondante aux crit�res entr�s
        }
        
        return 0;
    }
    
    /**
     * M�thode de comparaison pour les acteurs, r�alisateurs et genres.
     * Permet de comparer un tableau d'acteurs, r�alisateurs ou genres avec un autre
     * @param tab Film courant � comparer
     * @param tabC Objet � comparer au Film courant
     * @return Pourcentage de similarit� pour ce qui est compar� (acteurs, genres ou r�alisateurs)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private float compareTableau(List<Comparateur> tab, List<Comparateur> tabC)
    {
        boolean stop = false;
        int pourcentage = 0;
        
        assert(tab.size() != 0 && tabC.size() != 0);
        
        for(int i = 0; i < tab.size(); i++)
        {
        	stop = false;
        	
            for(int j = 0; j < tabC.size() && !stop; j++)
            {
                float temp = tab.get(i).comparer(tabC.get(j));	// appelle la m�thode de comparaison de Genre, Realisateur ou Acteur
                
                if(0 < temp)
                {
                    pourcentage += temp;
                    stop=true;
                }
            }
        }
        
        return pourcentage / tab.size(); // pourcentage de similarit� divis� par le nombre de crit�res s�lectionn�s par l'utilisateur
    }
    
    /**
     * M�thode de comparaison pour le titre.
     * Permet de comparer le titre en param�tre avec le titre du Film courant
     * @param titre Titre qui doit �tre compar�
     * @return Pourcentage de similarit� de titre
     */
    private int comparerTitre(String titre)
    {
        int pourcentage = 0;
        
        assert(titre.compareTo("") != 0);
        
        if(titre.toLowerCase().equals(this.nom.toLowerCase())) pourcentage = 100;	// si le titre cherch� est �gal au titre compar�, pourcentage de 100
        else if(titre.toLowerCase().contains(this.nom.toLowerCase())) pourcentage = 80; // si le titre du film compar� contient le titre du film cherch�, pourcentage de 80
        else pourcentage = 0;	// sinon, pourcentage de 0
        
        return pourcentage;
    }
    
    /**
     * M�thode de comparaison pour l'ann�e.
     * Permet de comparer l'ann�e en param�tre avec l'ann�e du Film courant
     * @param annee Ann�e qui doit �tre compar�e
     * @return Pourcentage de similarit� d'ann�e
     */
    private float comparerAnnee(int annee)
    {
        float pourcentage = 0;
        
        assert(annee > 0);
        
        if(annee == this.annee)pourcentage = 100;	// si ann�e identique, pourcentage de 100
        else if(annee == this.annee-1 || annee == this.annee+1)pourcentage = 90;	// si ann�e de +/- 1 an, pourcentage de 90
        else if(annee == this.annee-2 || annee == this.annee+2)pourcentage = 70; 	// si ann�e de +/- 2 ans, pourcentage de 70
        else if(annee == this.annee-3 || annee == this.annee+3)pourcentage = 40;	// si ann�e de +/- 3 ans, pourcentage de 40
        
        else pourcentage = 20 - (Math.abs(Math.max(this.annee, annee) - Math.min(this.annee, annee)));	// si l'�cart d'ann�e est sup�rieure � 3 ans,
        																								// on calcule le pourcentage selon l'�cart � l'ann�e d�sir�e
        if(pourcentage < 0)pourcentage = 0;
        
        return pourcentage;
    }
    
    /**
     * M�thode de comparaison pour la disponibilit� en 3D.
     * Permet de comparer la disponibilit� en param�tre avec la disponibilit� du Film courant
     * @param troisD Bool�en de disponibilit� en 3D
     * @return Pourcentage de similarit� sur la disponibilit� en 3D
     */
    private int comparerTroisD(int troisD)
    {
        int pourcentage = 0;
        
        if(troisD == this.troisD) pourcentage = 100;	// si m�me dispobilit�, pourcentage de 100
        else pourcentage = 40;	// sinon, pourcentage de 40
        
        return pourcentage;
    }
    
    /**
     * M�thode de comparaison pour la dur�e.
     * Permet de comparer la dur�e en param�tre avec la dur�e courante
     * @param duree La dur�e qui doit �tre compar�e
     * @return Pourcentage de similarit� sur la dur�e
     */
    private float comparerDuree(int duree)
    {
        float pourcentage = 0;
        
        assert(duree > 0);
        
        if(duree+16 > this.duree && duree-16 < this.duree) pourcentage = 100;	// si dur�e de +/- 15 minutes, pourcentage de 100
        else if (duree+21 > this.duree && duree-21 < this.duree) pourcentage = 50;	// si dur�e de +/- 20 minutes, pourcentage de 50
        else if (duree+31 > this.duree && duree-31 < this.duree) pourcentage = 20;	// si dur�e de +/- 30 minutes, pourcentage de 20
       
        else pourcentage = 20 - (Math.abs(Math.max(this.duree, duree) - Math.min(this.duree, duree)) / 10);	// si l'�cart de dur�e est sup�rieure � 20 mins,
        																									// on calcule le pourcentage selon l'�cart � la dur�e d�sir�e
        if(pourcentage < 0)pourcentage = 0;
        
        return pourcentage;
    }
    
    /**
     * M�thode de comparaison pour les tags.
     * Permet de comparer les tags du Film pass� en param�tre avec les tags du Film courant
     * @param f Films dont les tags doivent �tre compar�s
     * @return Nombre de tags �gaux
     */
    public int comparerTag(Film f)
    {
    	boolean stop = false;
        int nbEgalite = 0;
        
        for(int i = 0; i < this.tab_Tag.size(); i++)
        {
        	stop = false;
        	
            for(int j = 0; j < f.tab_Tag.size() && !stop; j++)
            {
                if(this.tab_Tag.get(i).comparer(f.getTab_Tag().get(j)) == 100)	// appelle la m�thode de comparaison de Tag
                {
                    nbEgalite++;
                    stop=true;
                }
            }
        }
        
        return nbEgalite;
    }
    
    /**
     * M�thode de comparaison du titre et des tags.
     * Permet de comparer le titre et les tags du Film pass� en param�tre ceux du Film courant
     * @param f Films dont le titre et les tags doivent �tre compar�s
     * @return Le resultat de la comparaison (pourcentage de similarit� du titre ou des tags)
     */
    public int rechercherTagAndTitle(Film f)
    {
    	int e = comparerTitre(f.getNom());
    	
    	if(e == 0)
    		return comparerTag(f);
    	else
    		return e;
    }

    @Override
    public String toString() {
            String texte="";
            String acteur="";
		String realisateur="";
		String sep="";
		String genre="";
		String tag="";
		String troisD="";
		if(this.isTroisD())troisD="oui";else troisD="non";
		int saut=9;
		for(int j=0;j<this.getTab_Acteur().size();j++){
			if(saut+this.getTab_Acteur().get(j).toString().length()<70){
				acteur+=sep+this.getTab_Acteur().get(j).toString();
				saut+=(sep+this.getTab_Acteur().get(j).toString()).length();
				sep=", ";
			}else
			{
				acteur+=",<br> "+this.getTab_Acteur().get(j).toString();
				saut=1;
			}
		}
		//
		//
		sep="";
		for(int j=0;j<this.getTab_Realisateur().size();j++){realisateur+=sep+this.getTab_Realisateur().get(j).toString();sep=", ";}
		sep="";
		saut=0;
		for(int j=0;j<this.getTab_Tag().size();j++){
			if(saut+this.getTab_Tag().get(j).toString().length()<70){
				tag+=sep+this.getTab_Tag().get(j).toString();
				saut+=(sep+this.getTab_Tag().get(j).toString()).length();
				sep=", ";
			}else
			{
				tag+=",<br> "+this.getTab_Tag().get(j).toString();
				saut=1;
			}
		}
		sep="";
		for(int j=0;j<this.getTab_Genre().size();j++){genre+=sep+this.getTab_Genre().get(j).toString();sep=", ";}
		texte ="<html><h2>"+this.getNom()+"</h2> "+"<u>Réalisateur</u> : "+realisateur+" "+"<br><br><u>Acteurs</u> : "+acteur+" "+"<br><br><u>Durée</u> : "+this.getDuree()+"<br><br><u>Disponible en 3D</u> : "+
                        troisD+"<br><br><u>Année de parution</u> : "+this.getAnnee()+"<br><br><u>Genres</u> : "+genre+"</html>";
		//
		return texte;
    }
    public String simpletoString()
    {
        String acteur="";
		String realisateur="";
		String sep="";
		int saut=9;
		for(int j=0;j<this.getTab_Acteur().size();j++){
			if(saut+this.getTab_Acteur().get(j).toString().length()<90){
				acteur+=sep+this.getTab_Acteur().get(j).toString();
				saut+=(sep+this.getTab_Acteur().get(j).toString()).length();
				sep=", ";
			}else
			{
				acteur+=",<br> "+this.getTab_Acteur().get(j).toString();
				saut=1;
			}
		}
		sep="";
		for(int j=0;j<this.getTab_Realisateur().size();j++){realisateur+=sep+this.getTab_Realisateur().get(j).toString();sep=", ";}
		return("<h2>"+this.getNom()+"</h2><br>"+"<u>Réalisateur</u> : "+realisateur+" "+"<br><br><u>Acteurs</u> : "+acteur+" "+"<br><br><u>Durée</u> : "+this.getDuree()+"<br><hr/>");
    }

    
}