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
public class Etudiant implements Serializable  {

	//________________ Props spéciales ____________//
	

	private String urlPhoto;
	private String dateNaissance;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etudiant")
	private int idEtudiant;
	
	@Column(name="mot de passe")
	private String mdp;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;
	
	
	//=================Association==================//
	/**
	 * Type de la relation: une etudiant associé à une adresse
	 * 						one Etudiant to one Adresse => @OneToOne
	 * 
	 * relation de cascade avec : cascade = CascadeType.Persist
	 * 	->Ajout
	 * 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADRESSE_ID", // nom de la FK 
				referencedColumnName="idAdresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
	

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
	public Etudiant(String urlPhoto, String dateNaissance, String mdp, String nom, String prenom, String email,
			Adresse adresse, List<Promotion> listePromotions, List<EtudiantCours> listeEtudiants) {
		super();
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.listePromotions = listePromotions;
		this.listeEtudiants = listeEtudiants;
	}

	/**
	 * ctor complet
	 * @param idEtudiant
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param urlPhoto
	 * @param dateNaissance
	 * @param listePromotions
	 * @param listeEtudiants
	 */
	public Etudiant(String urlPhoto, String dateNaissance, int idEtudiant, String mdp, String nom, String prenom,
			String email, Adresse adresse, List<Promotion> listePromotions, List<EtudiantCours> listeEtudiants) {
		super();
		this.urlPhoto = urlPhoto;
		this.dateNaissance = dateNaissance;
		this.idEtudiant = idEtudiant;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
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


	public int getIdEtudiant() {
		return idEtudiant;
	}


	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Etudiant [urlPhoto=" + urlPhoto + ", dateNaissance=" + dateNaissance + ", idEtudiant=" + idEtudiant
				+ ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse
				+ ", listePromotions=" + listePromotions + ", listeEtudiants=" + listeEtudiants + "]";
	}



	
	
	
	
}// end class
