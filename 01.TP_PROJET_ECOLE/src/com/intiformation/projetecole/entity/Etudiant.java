package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Etudiant extends Personne implements Serializable  {

	//________________ Props spéciales ____________//
	

	private String urlPhoto;
	private String dateNaissance;
	
	// =================Association==================//

	/**
	 * Type de la relation: Many Etudiant to Many Promotion
	 * 
	 */
	@ManyToMany(mappedBy="listeEtudiants")
	private List<Promotion> listePromotions;
	
	
	
	/**
	 * Type de relation : One Session to Many Module
	 */
	@OneToMany(mappedBy="etudiant", targetEntity=EtudiantCours.class, cascade=CascadeType.ALL)
	// 'etudiant' se récupère dans asso de EtudiantCours
	private List<EtudiantCours> listeEtudiants;
	
	
	
	//________________ Ctors ____________//
	



	/**
	 * ctor vide
	 */
	public Etudiant() {
		super();

	}


	/**
	 * ctor ss id
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param urlPhoto
	 * @param dateNaissance
	 * @param listePromotions
	 * @param listeEtudiants
	 */
	public Etudiant(String mdp, String nom, String prenom, String email, String urlPhoto,
			String dateNaissance, List<Promotion> listePromotions, List<EtudiantCours> listeEtudiants) {
		super( mdp, nom, prenom, email);
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.listePromotions = listePromotions;
		this.listeEtudiants = listeEtudiants;
	}
	

	/**
	 * ctor complet
	 * @param idPersonne
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param urlPhoto
	 * @param dateNaissance
	 * @param listePromotions
	 * @param listeEtudiants
	 */
	public Etudiant(int idPersonne, String mdp, String nom, String prenom, String email, String urlPhoto,
			String dateNaissance, List<Promotion> listePromotions, List<EtudiantCours> listeEtudiants) {
		super(idPersonne, mdp, nom, prenom, email);
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.listePromotions = listePromotions;
		this.listeEtudiants = listeEtudiants;
	}





	//_________________G/S______________________//

	public List<Promotion> getListePromotions() {
		return listePromotions;
	}


	public void setListePromotions(List<Promotion> listePromotions) {
		this.listePromotions = listePromotions;
	}


	public List<EtudiantCours> getListeEtudiants() {
		return listeEtudiants;
	}


	public void setListeEtudiants(List<EtudiantCours> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
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


	@Override
	public String toString() {
		return "Etudiant [urlPhoto=" + urlPhoto + ", dateNaissance=" + dateNaissance + ", listePromotions="
				+ listePromotions + ", listeEtudiants=" + listeEtudiants + ", getIdPersonne()=" + getIdPersonne()
				+ ", getMdp()=" + getMdp() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getEmail()="
				+ getEmail() + ", getAdresse()=" + getAdresse() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}


	
	
	
	
}// end class
