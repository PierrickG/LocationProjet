/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biblio.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Apocalypse
 */
@ManagedBean
@SessionScoped
public class IdentificationController {
    private String login = "";
    private String pass = "";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String check_identification()
    {
        if(login.equals("master") && pass.equals("admin"))
        {
            return "listFilms.xhtml";
        }
        else
        {
            return "admin_login.xhtml";
        }
    }
}