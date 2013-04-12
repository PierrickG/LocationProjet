package com.mycompany.biblio.controller;

import com.mycompany.biblio.business.*;
import Film.*;

import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class UtilisateurController {

    @EJB
    private UserEJB userEJB;
    private HtmlDataTable dataTable;
    private User user = new User();
    private ListDataModel utilisateurList;

   private void updateUtilisateurList() {
        utilisateurList = new ListDataModel(userEJB.findAll());
    }

    public String doNew() {
        user = new User();
        return "/newUser.xhtml?faces-redirect=true";
    }

    public String doCreate() {
            user = userEJB.create(user);
            user = new User();
            
            return "/listUsers.xhtml?faces-redirect=true";
    }

    public String doCancel() {
        return "/listUsers.xhtml?faces-redirect=true";
    }

   public String doDelete() {
        List<User> user = (List<User>) utilisateurList.getWrappedData();
        userEJB.delete(onlySelected(user));
        updateUtilisateurList();
        return "/listUsers.xhtml?faces-redirect=true";
    }

    private List<User> onlySelected(List<User> list) {
        for (Iterator<User> it = list.iterator(); it.hasNext();) {
            if (!(it.next().isSelected())) {
                it.remove();
            }
        }
        return list;
    }

    public String doShow() {
        user = (User) utilisateurList.getRowData();
        return "/showUser.xhtml?faces-redirect=true";
    }

    public String doEdit() {
        user = (User) utilisateurList.getRowData();
        return "/editUser.xhtml?faces-redirect=true";
    }

    public String doSave() {
        user = userEJB.update(user);
        return "/admin/listUsers.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListDataModel getUserList() {
        updateUtilisateurList();
        return utilisateurList;
    }

    public void setCategoryList(ListDataModel userList) {
        this.utilisateurList = userList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
}