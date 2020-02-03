package com.intiformation.projetecole.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Modèle de données qui correspond à la table Aide dans 
 * la bdd
 * 
 * 
 * @author IN-BR-026
 *
 */
@Entity
public class Aide {
	
	//_________________ Propriétés ______________//
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aide")
	private int idAide;
	
	@Column(name="page")
	private String page;
	
	@Column(name="contenu")
	private String contenu;
	//_________________ Ctors ______________//
	/**
	 * ctor complet
	 * @param idAide
	 * @param page
	 * @param contenu
	 */
	public Aide(int idAide, String page, String contenu) {
		super();
		this.idAide = idAide;
		this.page = page;
		this.contenu = contenu;
	}
	/**
	 * ctor sans id
	 * @param page
	 * @param contenu
	 */
	public Aide(String page, String contenu) {
		super();
		this.page = page;
		this.contenu = contenu;
	}

	/**
	 * ctor vide
	 */
	public Aide() {
		super();
	}
	
	//_________________ G/S ______________//
	public int getIdAide() {
		return idAide;
	}
	public void setIdAide(int idAide) {
		this.idAide = idAide;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	/**
	 * Methode toString 
	 * 
	 */
	@Override
	public String toString() {
		return "Aide [idAide=" + idAide + ", page=" + page + ", contenu=" + contenu + "]";
	}

}// end class
