package com.intiformation.projetecole.test;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class testPrimefaces {

	
	@RequestScoped
	public class UserLoginView {
	     
	    private String username;
	     
	    private String password;
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	   
	    public void login() {
	        FacesMessage message = null;
	        boolean loggedIn = false;
	         
	        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
	            loggedIn = true;
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
	        }
	         
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	    }   
	}
	
}
