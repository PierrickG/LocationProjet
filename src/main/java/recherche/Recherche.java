/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recherche;

import Film.Acteur;
import Film.Film;
import com.mycompany.biblio.business.FilmEJB;
import Film.FilmP;
import Film.Genre;
import Film.Realisateur;
import Film.Tag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Neko
 */
@ManagedBean
@SessionScoped
public class Recherche {
    
    private String recherch="";
    private String reponse="";
    @EJB private FilmEJB filmEJB;
    private Film f = new Film();
    
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    public String getRecherch() {
        return recherch;
    }

    public void setRecherch(String recherch) {
        this.recherch = recherch;
    }
    public Recherche(){
        
    }
    
    public void CreeFilm()
    {
        ArrayList<Acteur>  act = new ArrayList<Acteur>();
        String acteur ="DiCaprio, Leonardo;Winslet, Kate;Zane, Billy;Bates, Kathy;Fisher, Frances;Hill, Bernard";
        String [] actTab = acteur.split(";");
        for(int i=0;i<actTab.length;i++){
            String[] tmp = actTab[i].split(", ");
            act.add(new Acteur(tmp[0],tmp[1]));
        }
        //
        ArrayList<Realisateur>  real = new ArrayList<Realisateur>();
        String realisateur ="Cameron, James";
        String [] realTab = realisateur.split(";");
        for(int i=0;i<realTab.length;i++){
            String[] tmp = realTab[i].split(", ");
            real.add(new Realisateur(tmp[0],tmp[1]));
        }
        
        ArrayList<Genre> genre = new ArrayList<Genre>();
        genre.add(new Genre("Aventure"));
        ArrayList<Tag> tag = new ArrayList<Tag>();
        tag.add(new Tag("bateau"));
        Film f = new Film("Titanic", 1998, 194, 1, act, real, genre,tag);
        filmEJB.create(f);
    }
    public void rechercher()
    {
        if(!recherch.equals("")){
            Film filmChercher = new Film();
            String []st=recherch.split("/");
            ArrayList <Tag> t = new ArrayList<Tag>(st.length);
            for(int r=0;r<st.length;r++)
            {
                    t.add(new Tag(st[r]));
            }
            filmChercher.setNom(st[0]);
            filmChercher.setTab_Tag(t);
            List<Film> ListeFilm = filmEJB.findAll();
            ArrayList<FilmP> liste=null;
            liste = new ArrayList<FilmP>();
            for(int i=0;i<ListeFilm.size();i++)
            {
                    FilmP p = new FilmP();
                    p.video = ListeFilm.get(i);			
                    p.pourcentage = filmChercher.rechercherTagAndTitle(ListeFilm.get(i));
                    liste.add(p);
                    //System.out.println(p.video.getNom()+" : "+p.pourcentage);
            }
            Collections.sort(liste, Collections.reverseOrder());
            int longeurTab =Math.min(liste.size(), 5);
            //
            int mRecherche=0;
            for(int i=0;i<longeurTab;i++)
            {		
                    if(liste.get(i).pourcentage>0)
                    {
                            mRecherche++;
                    }
            }
            //
            longeurTab =Math.min(mRecherche, 5);
            if(longeurTab>0)
            
            for(int i=0;i<longeurTab;i++)
            {
                reponse = "<a href=\"votreFilm.xhtml#"+liste.get(i).video.getIdFilm()+"\" ><div id=\"FicheFilm\">";
                reponse += "<p>";
                reponse += (CreeFilm(liste.get(i).video));
                reponse+="</p>";
                reponse+="</div></a>";
            }
          reponse+="";   
        }
        else
        {
            reponse="";
        }
    } 
	
	public String CreeFilm(Film f){
		String acteur="";
		String realisateur="";
		String sep="";
		int saut=9;
		for(int j=0;j<f.getTab_Acteur().size();j++){
			if(saut+f.getTab_Acteur().get(j).toString().length()<90){
				acteur+=sep+f.getTab_Acteur().get(j).toString();
				saut+=(sep+f.getTab_Acteur().get(j).toString()).length();
				sep=", ";
			}else
			{
				acteur+=",<br> "+f.getTab_Acteur().get(j).toString();
				saut=1;
			}
		}
		sep="";
		for(int j=0;j<f.getTab_Realisateur().size();j++){realisateur+=sep+f.getTab_Realisateur().get(j).toString();sep=", ";}
		return("<h2>"+f.getNom()+"</h2><br>"+"<u>Réalisateur</u> : "+realisateur+" "+"<br><br><u>Acteurs</u> : "+acteur+" "+"<br><br><u>Durée</u> : "+f.getDuree());
		//
	}
        public String afficherfilm(){
            List<Film> ListeFilm = filmEJB.findAll();
            int i=0;
            int id=1;
            while(ListeFilm.get(i).getIdFilm()!=id&&i<ListeFilm.size())i++;
            Film f=null;
            if(!(i<ListeFilm.size()))
                f = ListeFilm.get(i);
            if(f!=null)
            return f.toString();
            else
                return "";
        }
}
