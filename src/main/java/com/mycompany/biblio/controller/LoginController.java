package com.mycompany.biblio.controller;

import Film.*;
import com.mycompany.biblio.business.UserEJB;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


@ManagedBean
@SessionScoped
public class LoginController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private UserEJB userEJB;

    private User user = new User();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doLogin() {
        AuthenticationToken token= new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject currentUtilisateur = SecurityUtils.getSubject();
        try {
            currentUtilisateur.login(token);
        } catch (Exception e) {
            addWarnMessage("Connexion impossible : vérifiez le login et le mot de passe", "vérifiez le login et le mot de passe");
            return null;
        }
        
        if(currentUtilisateur.hasRole("admin"))
            return "/admin/administration.xhtml?faces-redirect=true";
        else
            return "/private/moncompte.xhtml?faces-redirect=true";
    }

    private void addWarnMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String doCreate()
    {
        try
        {
            if(!user.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"))
            {
                throw new Exception("Adresse email invalide.");
            }
            user = userEJB.create(user);
            return "index.xhtml?faces-redirect=true";
        }
        catch (Exception e) {
            addWarnMessage("Erreur d'inscription : ", e.getMessage());
            return null;
        }
    }
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}