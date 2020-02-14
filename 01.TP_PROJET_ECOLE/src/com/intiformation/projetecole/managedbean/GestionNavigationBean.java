package com.intiformation.projetecole.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name="navigationBean")
@SessionScoped
public class GestionNavigationBean implements Serializable{

	/*props*/
	private int id_page;
	private String page;
	private String contenu;
	
	/*ctors*/
	/**
	 * ctor vide pour l'instanciation du managedBean par le conteneur web du serveur
	 */
	public GestionNavigationBean() {
	}
	
	/*méthodes*/
	public String naviguerVersAccueil() {
		return "navAide";
	}

	/**
	 * @return the id_page
	 */
	public int getId_page() {
		return id_page;
	}

	/**
	 * @param id_page the id_page to set
	 */
	public void setId_page(int id_page) {
		this.id_page = id_page;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	/*encapsulation*/
	
	/*------------ Cas de navigation de choixCode vers codeAdmin/ codeEtu /codeEns------*/
	


	
}
