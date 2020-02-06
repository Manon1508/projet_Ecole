package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cours implements Serializable {

	// _________props_____________//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCours;
	private String libelle;
	private String date;
	private String description;
	private String duree;

	// =================Association==================//

	
	
	/**
	 * asso avec matiere
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MATTIERE_ID", referencedColumnName = "idMatiere")
	private Matiere matiere;
	
	
	/**
	 * Type de la relation: Many cours to One promotion est le côté porteur de la FK
	 * => ajout de @JoinColumn
	 * asso avec promo 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROMOTION_ID", referencedColumnName = "idPromotion")
	private Promotion promotion;


	

	

	/**
	 * Type de relation : One Session to Many Module
	 * assoc avec etudiant / etudiantcours
	 */
	@OneToMany(mappedBy = "cours", targetEntity = EtudiantCours.class, cascade = CascadeType.ALL)
	// 'etudiant' se récupère dans asso de EtudiantCours
	private List<EtudiantCours> listeEtudiants;
	
	// MARIE @OneToMany(targetEntity=EtudiantCours.class, cascade=CascadeType.ALL, mappedBy="cours")
	//private List<EtudiantCours> listeEtudiantCours;

	// _____________ctor__________//

	/**
	 * ctor vide
	 */
	public Cours() {
		super();
	}

	/**
	 * ctor complet
	 * 
	 * @param idCours
	 * @param libelle
	 * @param date
	 * @param description
	 * @param duree
	 */
	public Cours(int idCours, String libelle, String date, String description, String duree) {
		super();
		this.idCours = idCours;
		this.libelle = libelle;
		this.date = date;
		this.description = description;
		this.duree = duree;
	}

	/**
	 * ctor ss id
	 * 
	 * @param libelle
	 * @param date
	 * @param description
	 * @param duree
	 * @param promotion
	 * @param matiere
	 * @param listeEtudiants
	 */
	public Cours(String libelle, String date, String description, String duree, Promotion promotion, Matiere matiere,
			List<EtudiantCours> listeEtudiants) {
		super();
		this.libelle = libelle;
		this.date = date;
		this.description = description;
		this.duree = duree;
		this.promotion = promotion;
		this.matiere = matiere;
		this.listeEtudiants = listeEtudiants;
	}

	/**
	 * ctor complet bis
	 * 
	 * @param idCours
	 * @param libelle
	 * @param date
	 * @param description
	 * @param duree
	 * @param promotion
	 * @param matiere
	 * @param listeEtudiants
	 */
	public Cours(int idCours, String libelle, String date, String description, String duree, Promotion promotion,
			Matiere matiere, List<EtudiantCours> listeEtudiants) {
		super();
		this.idCours = idCours;
		this.libelle = libelle;
		this.date = date;
		this.description = description;
		this.duree = duree;
		this.promotion = promotion;
		this.matiere = matiere;
		this.listeEtudiants = listeEtudiants;
	}

	// _____________G/S__________//

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public List<EtudiantCours> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<EtudiantCours> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", libelle=" + libelle + ", date=" + date + ", description=" + description
				+ ", duree=" + duree + ", promotion=" + promotion + ", matiere=" + matiere + ", listeEtudiants="
				+ listeEtudiants + "]";
	}

	
}
