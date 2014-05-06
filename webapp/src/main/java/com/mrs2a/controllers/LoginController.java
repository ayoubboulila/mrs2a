/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrs2a.controllers;

import com.mrs2a.entities.Account;
import com.mrs2a.entities.Role;
import com.mrs2a.entities.User;
import com.mrs2a.sessionBeans.AccountFacadeLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Snoopy
 */
@Named(value = "loginController")
@RequestScoped
public class LoginController {

    @Inject
    private AccountFacadeLocal accountfacade;
    
    
    
    private String login;
    private String password;
    private User user;
    private Role role;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        
        
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
   
    public String login(ActionEvent actionevent){
       String navigation="";
       FacesContext context = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
// à faire travailler sur les sessions et la sécurité
   try{     
      Account acc= accountfacade.DoLogin(getLogin(), getPassword());
        
      if(acc==null){
          navigation="echecAuth";
      }else{
          // user and role are not nedded, account is enough to access them
          
       user=acc.getIdUser();
       role=acc.getIdRole();
      context.getExternalContext().getSessionMap().put("account", acc);
        context.getExternalContext().getSessionMap().put("user", user);
        context.getExternalContext().getSessionMap().put("role", role);
       //*****************************************
       // navigation : admin/secretaire/medecin
       
       navigation=role.getName();
       
       context.getExternalContext().redirect(request.getContextPath()+"/jsf/"+navigation+"/home.xhtml");
       
       
       
          Logger.getLogger(getClass().getName()).log(Level.INFO,"navigation {0}",navigation);
      }
        
     
   }catch(IOException ex){
   Logger.getLogger(getClass().getName()).log(Level.WARNING,"login external error !! {0}",ex.getMessage());
   
   }
       return navigation; 
    }
    
  
    
    
    
    
    
    public String test(){
        
        FacesContext context=FacesContext.getCurrentInstance();
        
     Account a1= (Account) context.getExternalContext().getSessionMap().get("account");
     
         
        return "Scope : "+a1.getIdRole().getName();
    }
    
    
}
