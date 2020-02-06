package com.intiformation.projetecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Personne implements Serializable  {
	
	//_________________ Propriétés ______________//
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id_personne")
		private int idPersonne;
		
		@Column(name="motDePasse")
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
		 * 						one Personne to one Adresse => @OneToOne
		 * 
		 * relation de cascade avec : cascade = CascadeType.Persist
		 * 	->Ajout
		 * 
		 */
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="ADRESSE_ID", // nom de la FK 
					referencedColumnName="id_adresse")// nom de la colonne dans la classe associée // gestion de la FK
		private Adresse adresse;
		
		//_________________ Ctors ______________//

		/**
		 * ctor complet
		 * @param idPersonne
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 */
		public Personne(int idPersonne, String mdp, String nom, String prenom, String email) {
			super();
			this.idPersonne = idPersonne;
			this.mdp = mdp;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
		}
		/**
		 * ctor ss id
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 */
		public Personne(String mdp, String nom, String prenom, String email) {
			super();
			this.mdp = mdp;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
		}
		
		/**
		 * ctor vide
		 */
		public Personne() {
			super();
			
		//_________________ G/S ______________//
			
		}
		public int getIdPersonne() {
			return idPersonne;
		}
		public void setIdPersonne(int idPersonne) {
			this.idPersonne = idPersonne;
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
			return "Personne [idPersonne=" + idPersonne + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom
					+ ", email=" + email + ", adresse=" + adresse + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}

		
		
		

		
		

}// end class
