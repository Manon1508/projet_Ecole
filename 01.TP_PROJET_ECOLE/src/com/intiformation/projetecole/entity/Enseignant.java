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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enseignant implements Serializable {

	
	//________________ Props spéciales ____________//
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_enseignant")
	private int idEnseignant;
	
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
	 * Type de la relation: une enseignant associé à une adresse
	 * 						one Enseignant to one Adresse => @OneToOne
	 * 
	 * relation de cascade avec : cascade = CascadeType.Persist
	 * 	->Ajout
	 * 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADRESSE_ID", // nom de la FK 
				referencedColumnName="idAdresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
	
//  	/**
//  	 * Type de relation : One enseigne to Many enseignant
//  	 */
//  	@OneToMany(mappedBy=" Enseignant", targetEntity=Enseigne.class, cascade=CascadeType.ALL)
//  	 //  'session' se récupère dans asso de module
//  	private List<Enseigne> listeEnseignes;
//  	
	//________________ Ctors ____________//
	
	/**
	 * ctor vide 
	 */
	public Enseignant() {
		super();

	}

	/**
	 * ctor complet
	 * @param idEnseignant
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Enseignant(int idEnseignant, String mdp, String nom, String prenom, String email, Adresse adresse) {
		super();
		this.idEnseignant = idEnseignant;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}


	/**
	 * ctor ss id
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Enseignant(String mdp, String nom, String prenom, String email, Adresse adresse) {
		super();
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}



	@Override
	public String toString() {
		return "Enseignant [idEnseignant=" + idEnseignant + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", adresse=" + adresse + "]";
	}

	

	public int getIdEnseignant() {
		return idEnseignant;
	}

	public void setIdEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
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



	
	
	
	
}// end classe
