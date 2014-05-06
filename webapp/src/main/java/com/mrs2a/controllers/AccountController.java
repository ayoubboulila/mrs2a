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
import com.mrs2a.sessionBeans.RoleFacade;
import com.mrs2a.sessionBeans.RoleFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Snoopy
 */
@Named(value = "accountController")
@SessionScoped
public class AccountController implements Serializable{

    @Inject
    AccountFacadeLocal accountFacade;
    
    @Inject
    RoleFacadeLocal roleFacade;
    
    private Account newAccount=new Account();
    private Account selectedAccount=new Account();
    
    private Account[] selectedAccounts;
    
    private List<Account> accounts;
    
    private Role role =new Role();
    private int maxId;
    private User user=new User();
    
    
    /**
     * Creates a new instance of AccountController
     */
    public AccountController() {
    }
    
    
    @PostConstruct
    public void init(){    
        this.accounts=accountFacade.findAll();
   
    }

    public AccountFacadeLocal getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(AccountFacadeLocal accountFacade) {
        this.accountFacade = accountFacade;
    }

    public Account getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(Account newAccount) {
        this.newAccount = newAccount;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public Account[] getSelectedAccounts() {
        return selectedAccounts;
    }

    public void setSelectedAccounts(Account[] selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int getMaxId() {
        maxId=accountFacade.max();
        return maxId+1;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
public void prepareCreate(){
    newAccount=new Account();
    newAccount.setIdAccount(getMaxId());
    Logger.getLogger(getClass().getName()).log(Level.INFO,"account id {0}",newAccount.getIdAccount());
    
}

public void prepareList(){
    this.accounts=accountFacade.findAll();
}

public void Create(ActionEvent actionevent){
    FacesContext context = FacesContext.getCurrentInstance();
    try{
     context.addMessage(null, new FacesMessage("Info", "Create called"));
     Logger.getLogger(getClass().getName()).log(Level.INFO," create id: {0}",newAccount.getIdAccount());
    
    accountFacade.create(newAccount);
    
    Logger.getLogger(getClass().getName()).log(Level.INFO," account with id {0} created successfully",newAccount.getIdAccount());
    
    context.addMessage(null, new FacesMessage("Info", "Account created successfully"));
    prepareList();
    newAccount=new Account();
    }catch(Exception ex){
        
     Logger.getLogger(getClass().getName()).log(Level.WARNING," problem occured creating account {0} ",ex.getMessage());
     context.addMessage(null, new FacesMessage("Error!", "Exception occured"));  
    }
    
}

    public Role getRole() {
       
               
        return role;
    }

    public void setRole(Role role) {
        
        this.role = role;
    }
  

  public void affect_role(SelectEvent selectevent){
      
      Role r=(Role) selectevent.getObject();
      this.role=r;
      Logger.getLogger(getClass().getName()).log(Level.INFO,"affect_role with role name {0}", getRole().getName());
  }
  
  
  public void validate_role_selection(ActionEvent actionevent){
    
      newAccount.setIdRole(role);
   Logger.getLogger(getClass().getName()).log(Level.INFO,"validate_role_selection called with role name {0}", getRole().getName());
      
  }
  
 public void affect_user(SelectEvent selectevent){
     User u=(User) selectevent.getObject();
     this.user=u;
     Logger.getLogger(getClass().getName()).log(Level.INFO,"affect_user called with user cin {0}",getUser().getCin());
 }
  
public void validate_user_selection(ActionEvent actionevent){
    newAccount.setIdUser(user);
    Logger.getLogger(getClass().getName()).log(Level.INFO,"validate_user_selection called with user cin {0}",getUser().getCin());
    
}

public void prepareEdit(){
    Logger.getLogger(getClass().getName()).log(Level.INFO,"prepare_edit selected accounts {0}",selectedAccounts.length);
    int index=selectedAccounts.length-1;
    selectedAccount=selectedAccounts[index];  
}




  
}
