package Film;

/**
 * Interface Comparateur (generique).
 * Interface pour la comparaison des objets
 * 
 * @author Pierrick GUINET & Francois LOZE
 * @version 7.0
 */

public interface Comparateur <T>{
    
	/**
	 * Methode de comparaison.
	 * Declaree dans l'interface Comparateur et definie de maniere specifique dans chaque classe qui l'utilise.
	 * Utilisee pour comparer des genres, des personnes et des tags
	 * @param obj L'objet a comparer avec l'objet appelant
	 */
	public float comparer(T obj);
}