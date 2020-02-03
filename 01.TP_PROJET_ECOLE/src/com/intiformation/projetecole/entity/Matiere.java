package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Matiere implements Serializable {

	// _________props_____________//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMatiere;
	private String libelle;

	// =================Association==================//

	/*--------- AMELIORATION POUR AVOIR 2 TABLES */
	@OneToMany(mappedBy="promotion", targetEntity=Cours.class, cascade=CascadeType.ALL)
	// 'session' se récupère dans asso de module
	private List<Cours> listeCours;
	
	/**
	 * Type de relation : One enseigne to Many Matiere
	 */
//	@OneToMany(mappedBy="enseigne", targetEntity=Matiere.class, cascade=CascadeType.ALL)
//	// 'session' se récupère dans asso de module
//	private List<Matiere> listeMatieres;
//	

	// _____________ctor__________//

	/**
	 * ctor ss id
	 * 
	 * @param libelle
	 */
	public Matiere(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * ctor vide
	 */
	public Matiere() {
		super();
	}

	/**
	 * ctor complet
	 * 
	 * @param idMatiere
	 * @param libelle
	 */
	public Matiere(int idMatiere, String libelle) {
		super();
		this.idMatiere = idMatiere;
		this.libelle = libelle;
	}

	// _____________G/S__________//

	public int getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", libelle=" + libelle + ", listeCours=" + listeCours + "]";
	}

	

}
