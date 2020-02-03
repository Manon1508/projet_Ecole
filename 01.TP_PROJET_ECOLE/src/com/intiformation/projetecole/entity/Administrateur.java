package com.intiformation.projetecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Administrateur implements Serializable {

	//________________ Props spéciales ____________//
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personne")
	private int idAdministrateur;
	
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
	 * Type de la relation: une personne associé à une adresse
	 * 						one Administrateur to one Adresse => @OneToOne
	 * 
	 * relation de cascade avec : cascade = CascadeType.Persist
	 * 	->Ajout
	 * 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADRESSE_ID", // nom de la FK 
				referencedColumnName="idAdresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
		
		
		//________________ Ctors ____________//
		
		/**
		 * ctor vide 
		 */
		public Administrateur() {
			super();

		}

		/**
		 * ctor complet
		 * @param idAdministrateur
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 * @param adresse
		 */
		public Administrateur(int idAdministrateur, String mdp, String nom, String prenom, String email, Adresse adresse) {
			super();
			this.idAdministrateur = idAdministrateur;
			this.mdp = mdp;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.adresse = adresse;
		}


		

		/**
		 * Ctor ss id
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 * @param adresse
		 */
		public Administrateur(String mdp, String nom, String prenom, String email, Adresse adresse) {
			super();
			this.mdp = mdp;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.adresse = adresse;
		}

		@Override
		public String toString() {
			return "Administrateur [idAdministrateur=" + idAdministrateur + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom
					+ ", email=" + email + ", adresse=" + adresse + "]";
		}

		public int getIdAdministrateur() {
			return idAdministrateur;
		}

		public void setIdAdministrateur(int idAdministrateur) {
			this.idAdministrateur = idAdministrateur;
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

		
		// Ajouter les G/S des associations
		
		
		
		

}// end classe
