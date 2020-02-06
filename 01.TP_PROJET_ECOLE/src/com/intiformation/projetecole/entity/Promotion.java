package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Promotion implements Serializable {

	// _________props_____________//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPromotion;
	private String libelle;

	// =================Association==================//

	/**
	 * asso avec matiere pour donner enseigne
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="PROMOTION_ID"), inverseJoinColumns=@JoinColumn(name="MATIERE_ID"))
	private List<Matiere> listeMatieres;
	
	/**
	 * asso avec enseignant pour donnner enseigne
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="PROMOTION_ID"), inverseJoinColumns=@JoinColumn(name="ENSEIGNANT_ID"))
	private List<Enseignant> listeEnseignants;
	
	/**
	 * asso avec cours
	 */
	@OneToMany(mappedBy="promotion", targetEntity=Cours.class, cascade=CascadeType.ALL)
	// 'session' se récupère dans asso de module
	private List<Cours> listeCours;
	
	/**
	 * asso avec etudiant
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="promo_asso_etu", joinColumns=@JoinColumn(name="PROMOTION_ID"), inverseJoinColumns=@JoinColumn(name="ETUDIANT_ID"))
	private List<Etudiant> listeEtudiants;
	
	/*--------- AMELIORATION POUR AVOIR 2 TABLES */

	
	

	// _____________ctor__________//

	/**
	 * ctor ss id
	 * 
	 * @param libelle
	 */
	public Promotion(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * ctor vide
	 */
	public Promotion() {
		super();
	}

	/**
	 * ctor complet
	 * 
	 * @param idPromotion
	 * @param libelle
	 */
	public Promotion(int idPromotion, String libelle) {
		super();
		this.idPromotion = idPromotion;
		this.libelle = libelle;
	}

	// _____________G/S__________//

	public int getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
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

	public List<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	public List<Enseignant> getListeEnseignants() {
		return listeEnseignants;
	}

	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}

	@Override
	public String toString() {
		return "Promotion [idPromotion=" + idPromotion + ", libelle=" + libelle + ", listeMatieres=" + listeMatieres
				+ ", listeEnseignants=" + listeEnseignants + ", listeCours=" + listeCours + "]";
	}


}
