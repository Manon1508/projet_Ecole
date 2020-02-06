package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Etudiant extends Personne implements Serializable  {

	//________________ Props spéciales ____________//
	

	private String urlPhoto;
	private String dateNaissance;
	
	//=================Association==================//
	/**
	 * Type de la relation: une etudiant associé à une adresse
	 * 						one Etudiant to one Adresse => @OneToOne
	 * 
	 * relation de cascade avec : cascade = CascadeType.Persist
	 * 	->Ajout
	 * asso adresse
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADRESSE_ID", // nom de la FK 
				referencedColumnName="id_adresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
	

	/**
	 * Type de la relation: Many Etudiant to Many Promotion
	 * asso pour promotion
	 */
	@ManyToMany(mappedBy="listeEtudiants")
	private List<Promotion> listePromotions;
	
	
	
	/**
	 * Type de relation : One Session to Many Module
	 * asso cours
	 */
	@OneToMany(mappedBy="etudiant", targetEntity=EtudiantCours.class, cascade=CascadeType.ALL)
	// 'etudiant' se récupère dans asso de EtudiantCours
	private List<EtudiantCours> listeCours;
	
	
	
	//________________ Ctors ____________//
	



	/**
	 * ctor vide
	 */
	public Etudiant() {
		super();

	}



/**
 * ctor complet
 * @param dateNaissance
 * @param mdp
 * @param nom
 * @param prenom
 * @param email
 */
	public Etudiant(int idPersonne, String mdp, String nom, String prenom, String email, String urlPhoto,
			String dateNaissance, Adresse adresse, List<Promotion> listePromotions, List<EtudiantCours> listeCours) {
		super(idPersonne, mdp, nom, prenom, email);
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.listePromotions = listePromotions;
		this.listeCours = listeCours;
	}
	

	/**
	 * ctor ss id
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param urlPhoto
	 * @param dateNaissance
	 * @param adresse
	 * @param listePromotions
	 * @param listeCours
	 */
	public Etudiant(String mdp, String nom, String prenom, String email, String urlPhoto,
			String dateNaissance, Adresse adresse, List<Promotion> listePromotions, List<EtudiantCours> listeCours) {
		super(mdp, nom, prenom, email);
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.listePromotions = listePromotions;
		this.listeCours = listeCours;
	}

	/**
	 * sans rien
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 */
	public Etudiant(String mdp, String nom, String prenom, String email, 
			String dateNaissance) {
		super(mdp, nom, prenom, email);
		this.dateNaissance = dateNaissance;


	}


	/**
	 * ctor ss id avec adresse
	 * @return
	 */
	public Etudiant(String mdp, String nom, String prenom, String email, 
			String dateNaissance, Adresse adresse) {
		super(mdp, nom, prenom, email);
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;

	}
	


	public String getUrlPhoto() {
		return urlPhoto;
	}



	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}



	public String getDateNaissance() {
		return dateNaissance;
	}



	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}



	public Adresse getAdresse() {
		return adresse;
	}



	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}



	public List<Promotion> getListePromotions() {
		return listePromotions;
	}



	public void setListePromotions(List<Promotion> listePromotions) {
		this.listePromotions = listePromotions;
	}



	public List<EtudiantCours> getListeCours() {
		return listeCours;
	}



	public void setListeCours(List<EtudiantCours> listeCours) {
		this.listeCours = listeCours;
	}



	@Override
	public String toString() {
		return "Etudiant [urlPhoto=" + urlPhoto + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse
				+ ", listePromotions=" + listePromotions + ", listeCours=" + listeCours + ", getUrlPhoto()="
				+ getUrlPhoto() + ", getDateNaissance()=" + getDateNaissance() + ", getAdresse()=" + getAdresse()
				+ ", getListePromotions()=" + getListePromotions() + ", getListeCours()=" + getListeCours()
				+ ", getIdPersonne()=" + getIdPersonne() + ", getMdp()=" + getMdp() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getEmail()=" + getEmail() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	


	
	


	
	
	
	
}// end class
