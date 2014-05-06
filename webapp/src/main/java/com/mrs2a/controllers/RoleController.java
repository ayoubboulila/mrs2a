/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrs2a.controllers;

import com.mrs2a.entities.Role;
import com.mrs2a.sessionBeans.RoleFacadeLocal;
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
@Named(value = "roleController")
@SessionScoped
public class RoleController implements Serializable {

    @Inject 
  private  RoleFacadeLocal roleFacade;
    
    private Role newRole=new Role();
    
    private Role selectedRole=new Role();
    
    private Role[] selectedRoles;
    
    private List<Role> roles;
    /**
     * Creates a new instance of RoleController
     */
    public RoleController() {
    }
  
    
    @PostConstruct
   public void init(){
       this.roles=roleFacade.findAll();
       Logger.getLogger(getClass().getName()).log(Level.INFO,"init construct called {0}",roles.size());
   } 

    public RoleFacadeLocal getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(RoleFacadeLocal roleFacade) {
        this.roleFacade = roleFacade;
    }

    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public Role[] getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(Role[] selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
   
    
    public void prepareList(ActionEvent actionevent){
        this.roles=roleFacade.findAll();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
