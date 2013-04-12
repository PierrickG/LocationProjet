package com.mycompany.biblio.controller;

import Film.*;
import com.mycompany.biblio.business.FilmEJB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class FilmController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private FilmEJB filmEJB;

    private HtmlDataTable dataTable;

    private Film film = new Film();
    private ListDataModel filmList;
    private ArrayList<Genre> genreList;
    private ListDataModel filmListR=new ListDataModel();

    public FilmController()
    {
        genreList = new ArrayList<Genre>();
        genreList.add(new Genre("Action"));
        genreList.add(new Genre("Animation"));
        genreList.add(new Genre("Aventure"));
        genreList.add(new Genre("Comedie"));
        genreList.add(new Genre("Drame"));
        genreList.add(new Genre("Famille"));
        genreList.add(new Genre("Fantastique"));
        genreList.add(new Genre("Horreur"));
        genreList.add(new Genre("Romance"));
        genreList.add(new Genre("Science Fiction"));
        genreList.add(new Genre("Thriller"));
    }
    
    private void updateFilmList() {
        filmList = new ListDataModel(filmEJB.findAll());
    }
    private void updateFilmListR() {
        filmListR = new ListDataModel((List)filmListR.getWrappedData());
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

     public void ajout(Film film)
     {
        /** Copie de tous les realisateur saisis dans le champ d'ajout "Realisateur" dans l'attribut "tab_realisateur" du Film **/
         
        String realisateur [] = film.getRealisateur().split(";");
        Realisateur[] realisateurs = Realisateur.TransformeStringRealisateurNom(realisateur);
        
        ArrayList <Realisateur> liste_real = new ArrayList <Realisateur>();
        liste_real.addAll(Arrays.asList(realisateurs));
        
        film.setTab_Realisateur(liste_real);
        
        /** Copie de tous les acteurs saisis dans le champ d'ajout "Acteur" dans l'attribut "tab_acteur" du Film **/
        
        String acteur [] = film.getActeur().split(";");
        Acteur[] acteurs = Acteur.TransformeStringActeurNom(acteur);
        
        ArrayList <Acteur> liste_act = new ArrayList <Acteur>();
        liste_act.addAll(Arrays.asList(acteurs));
        
        film.setTab_Acteur(liste_act);
        
        /** Copie de l'attribut "3D" sélectionné ou non dans l'attribut "troisD" du Film **/
        boolean temp =  film.isThreeD();
        if(temp == true)
        {
            film.setTroisD(1);
        }
        else
            film.setTroisD(0);
         
        /** Copie de tous les genres sélectionnés avec "genre" dans l'attribut "tab_genre" du Film **/
         
        ArrayList <Genre> liste_genre = new ArrayList <Genre>();
        String genre;
        Genre g;
        
        for(int i = 0; i < film.getGenre().size(); i++)
        {
            genre = film.getGenre().get(i);
            g = new Genre(genre);
            liste_genre.add(g);
        }
        
        film.setTab_Genre(liste_genre);
    }
     
     public void affichage(Film film)
     {
        /** Création du String associé au Realisateur du Film **/
        String realisateur = "";
        List<Realisateur> list_real = film.getTab_Realisateur();
        
        for(int i = 0; i < list_real.size(); i++)
        {
            Realisateur r = list_real.get(i);
            realisateur += r.getNom().substring(0, 1).toUpperCase() + r.getNom().substring(1);
            realisateur += ", " + r.getPrenom().substring(0, 1).toUpperCase() + r.getPrenom().substring(1) + ";";
        }
        
        realisateur = realisateur.substring(0, realisateur.length()-1);
        
        film.setRealisateur(realisateur);
        
        /** Création du String associé à l'Acteur du Film **/
        String acteur = "";
        List<Acteur> list_act = film.getTab_Acteur();
        
        for(int i = 0; i < list_act.size(); i++)
        {
            Acteur a = list_act.get(i);
            acteur += a.getNom().substring(0, 1).toUpperCase() + a.getNom().substring(1);
            acteur += ", " + a.getPrenom().substring(0, 1).toUpperCase() + a.getPrenom().substring(1) + ";";
        }
        
        acteur = acteur.substring(0, acteur.length()-1);
        
        film.setActeur(acteur);
        
        /** Création du booléen associé à TroisD du Film **/
        film.setThreeD(film.getTroisD());
        
        /** Création d'une liste de genre en String pour chaque Film **/
        ArrayList<String> genre = new ArrayList<String>();
        List<Genre> list_genre = film.getTab_Genre();
        
        for(int i = 0; i < list_genre.size(); i++)
        {
            genre.add(list_genre.get(i).toString());
        }
        
        film.setGenre(genre);
     }
     
     public String afficher_Realisateurs(Film film)
     {
         String realisateurs = "";
         List<Realisateur> liste_realisateurs = film.getTab_Realisateur();
         
         for(int i = 0; i < liste_realisateurs.size(); i++)
         {
             realisateurs += liste_realisateurs.get(i).toString();
         }
         
         return realisateurs;
     }
     
    public String doNew() {
        film = new Film();
        return "newFilm.xhtml";
    }

    public String doCreate() {
        ajout(film);
        film = filmEJB.create(film);
        return "listFilms.xhtml";
    }
    
    public String doCancel() {
        return "listFilms.xhtml";
    }

    public String doDelete() {
        List<Film> films = (List<Film>)filmList.getWrappedData();
        filmEJB.delete(onlySelected(films));
        updateFilmList();
        return "listFilms.xhtml";
    }

    private List<Film> onlySelected(List<Film> list) {
        for (Iterator<Film> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        film = (Film)filmList.getRowData();
        affichage(film);
        
        return "editFilm.xhtml";
    }

    public String doSave() {
        ajout(film);
        film = filmEJB.update(film);
        return "listFilms.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public ListDataModel getFilmList() {
        updateFilmList();
        return filmList;
    }

    public void setFilmList(ListDataModel filmList) {
        this.filmList = filmList;
    }
    public ListDataModel getFilmListR() {
        updateFilmListR();
        return filmListR;
    }

    public void setFilmListR(ListDataModel filmList) {
        this.filmListR = filmList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
    private String recherch="";

    public String getRecherch() {
        return recherch;
    }

    public void setRecherch(String recherch) {
        this.recherch = recherch;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    private String reponse="";
    public String rechercher()
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
            List<Film> listeFil = new ArrayList<Film>();
            for(int i=0;i<longeurTab;i++)
            {
                listeFil.add(liste.get(i).video);
            }
            filmListR =new ListDataModel(listeFil);
        }
        else
        {
            
        }
        return "index.xhtml";
    } 
    public String doRead() {
        film = (Film)filmListR.getRowData();
        return "votreFilm.xhtml?faces-redirect=true";
    }
    public String doRead(Long filmR) {
        List<Film> ListeFilm = filmEJB.findAll();
        int i =0;
        while(ListeFilm.size()>i && !(ListeFilm.get(i).getIdFilm().equals(filmR)))i++;
        if(ListeFilm.size()==0 || i > ListeFilm.size())           
            return "erreur.xhtml";
        else
            film = ListeFilm.get(i);
        return "votreFilm.xhtml";
    }
}