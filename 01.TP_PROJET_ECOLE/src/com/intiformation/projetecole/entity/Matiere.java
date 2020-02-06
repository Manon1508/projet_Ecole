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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	/**
	 * asso avec promo qui donne enseigne
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="MATIERE_ID"), inverseJoinColumns=@JoinColumn(name="PROMOTION_ID"))
	private List<Promotion> listePromotions;
	
	/**
	 * asso avec enseignant qui donne enseigne
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="MATIERE_ID"), inverseJoinColumns=@JoinColumn(name="ENSEIGNANT_ID"))
	private List<Enseignant> listeEnseignants;
	
	/**
	 * 
	 * asso avec cours 
	 */
	@OneToMany(mappedBy="matiere", targetEntity=Cours.class, cascade=CascadeType.ALL)
	// 'session' se récupère dans asso de module
	private List<Cours> listeCours;
	

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


	public List<Promotion> getListePromotions() {
		return listePromotions;
	}

	public void setListePromotions(List<Promotion> listePromotions) {
		this.listePromotions = listePromotions;
	}

	public List<Enseignant> getListeEnseignants() {
		return listeEnseignants;
	}

	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}

	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", libelle=" + libelle + ", listePromotions=" + listePromotions
				+ ", listeEnseignants=" + listeEnseignants + "]";
	}


	

}
