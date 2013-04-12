package Film;

/**
 * Classe FilmP (implemente l'interface Comparable).
 * Definit les differentes methodes pour comparer les pourcentages de similarite de Film
 * 
 * @author Pierrick GUINET & Franeois LOZE
 * @version 7.0
 */

@SuppressWarnings("rawtypes")
public class FilmP implements Comparable{

	/** Objet Film **/
	public Film video;
	/** Pourcentage de similarite **/
	public float pourcentage = 0;

	/**
	 * Methode de comparaison des pourcentages de similarites.
	 * Definit les differents resultats selon si un pourcentage est mieux que l'autre ou non
	 * @param f FilmP dont le pourcentage va etre compare au courant
	 * @return Le resultat de la comparaison des pourcentages
	 */
	public int compare(FilmP f)
	{
      int resultat = 0;
      
      assert(this.pourcentage >= 0 && f.pourcentage >= 0);	// test que les pourcentages sont bien positifs
      
      if(this.pourcentage > f.pourcentage)
         resultat = 1;
      else if(this.pourcentage < f.pourcentage)
         resultat = -1;
      else if(this.pourcentage - f.pourcentage<0.1)							// si les pourcentages de similarite des 2 films sont egaux, alors le nombre de clics intervient
      {					
    	  if(this.video.getNbClick() > f.video.getNbClick())
    	         resultat = 1;
    	      if(this.video.getNbClick() < f.video.getNbClick())
    	         resultat = -1;
    	      if(this.video.getNbClick() == f.video.getNbClick())
    	         resultat = 0;
      }
      
      return resultat;
	}
	
	/**
	 * Surcharge de la methode compareTo, par la comparaison de la methode compare de FilmP
	 */
	@Override
	public int compareTo(Object o) 
	{
		return compare((FilmP) o);
	}
} 