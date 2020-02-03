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
	 * Type de relation : Many Sessions to Many Etudiants
	 * 
	 * coté maitre : - le nom de la table de jointure - le nom des FK
	 * 
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "promotions_asso_etudiants", joinColumns = @JoinColumn(name = "PROMOTION_ID"), inverseJoinColumns = @JoinColumn(name = "ETUDIANT_ID"))
	private List<Etudiant> listeEtudiants;
	
	
	
	/*--------- AMELIORATION POUR AVOIR 2 TABLES */
	@OneToMany(mappedBy="promotion", targetEntity=Cours.class, cascade=CascadeType.ALL)
	// 'session' se récupère dans asso de module
	private List<Cours> listeCours;
	
//  /**
//   * Type de relation : One enseigne to Many promotion
//   */
//  @OneToMany(mappedBy="promotion", targetEntity=Enseigne.class, cascade=CascadeType.ALL)
//   //'session' se récupère dans asso de module
//  private List<Enseigne> listeEnseignes;
	

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

	
	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	@Override
	public String toString() {
		return "Promotion [idPromotion=" + idPromotion + ", libelle=" + libelle + "]";
	}

}
