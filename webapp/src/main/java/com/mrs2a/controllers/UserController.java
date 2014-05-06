/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrs2a.controllers;

import com.mrs2a.entities.User;
import com.mrs2a.sessionBeans.UserFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author Snoopy
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    
    @Inject
    UserFacadeLocal userFacade;
    
    
    private User newUser=new User();
    private User selectedUser=new User();
    
    private User[] selectedUsers;
    
    private List<User> users;
    
    
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
   
    @PostConstruct
    public void init(){
       this.users=userFacade.findAll();
       
       Logger.getLogger(getClass().getName()).log(Level.INFO,"users init called {0}",users.size());
         
    }

    public UserFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User[] getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(User[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
   public void prepareList(ActionEvent actionEvent){
       
       this.users=userFacade.findAll();
   }
    
    
    
    
}
