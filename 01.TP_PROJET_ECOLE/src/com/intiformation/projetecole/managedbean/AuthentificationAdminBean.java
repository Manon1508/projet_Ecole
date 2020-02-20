package com.intiformation.projetecole.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



import com.intiformation.projetecole.dao.AdministrateurDao;
import com.intiformation.projetecole.tool.SessionUtils;

@ManagedBean(name="authentificationBean")
@SessionScoped
public class AuthentificationAdminBean {
	
	private String email;
	private String motDePasse;
	
	private AdministrateurDao administrateurDao;
	
	public AuthentificationAdminBean() {
		administrateurDao = new AdministrateurDao();
	}
	
	
	public String connecterAdmin() {
		if(administrateurDao.isExist(email, motDePasse)) {
			// --> user exist
			// creation de la session
			
			HttpSession session = SessionUtils.getUserSession();
			//sauvegarde du login dans la session
			session.setAttribute("user_login", email);
			
			// renvoie de la page de redirection
			return "menuAdmin.xhtml";
		}else {
		    // --> user not exist
			// => message d'erreur vers la vue
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la connexion", "Identifiant ou mot de passe invalide"));
			
			// redirection
			return "codeAdmin.xhtml";
		}// end else
	}// end method
	
	public String deconnecterAdmin() {
		//recup de la session
		HttpSession session = SessionUtils.getUserSession();
		
		// deconnexion
		session.invalidate();
		
		// redirection
		return "codeAdmin.xhtml";
	}// end metrhod



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public AdministrateurDao getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(AdministrateurDao administrateurDao) {
		this.administrateurDao = administrateurDao;
	}
	
	
	
	

}
