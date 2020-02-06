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
public class Administrateur extends Personne implements Serializable {

	//________________ Props spéciales ____________//
	
	
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
				referencedColumnName="id_adresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
		
		
		//________________ Ctors ____________//
		
		/**
		 * ctor vide 
		 */
		public Administrateur() {
			super();

		}

		/**
		 * ctor ss asso
		 * @param idAdministrateur
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 * @param adresse
		 */
		public Administrateur(int idPersonne, String mdp, String nom, String prenom, String email) {
			super(idPersonne, mdp, nom, prenom, email);

		}

		
		/**
		 * Ctor avec ad
		 * @param mdp
		 * @param nom
		 * @param prenom
		 * @param email
		 * @param adresse
		 */
		public Administrateur(int idPersonne, String mdp, String nom, String prenom, String email, Adresse adresse) {
			super(idPersonne, mdp, nom, prenom, email);
			this.adresse = adresse;
		}

		/**
		 * ctor ss is ss asso
		 * @param string
		 * @param string2
		 * @param string3
		 * @param string4
		 */
		public Administrateur(String string, String string2, String string3, String string4) {

		}

		public Adresse getAdresse() {
			return adresse;
		}

		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}

		@Override
		public String toString() {
			return "Administrateur [adresse=" + adresse + ", getAdresse()=" + getAdresse() + ", getIdPersonne()="
					+ getIdPersonne() + ", getMdp()=" + getMdp() + ", getNom()=" + getNom() + ", getPrenom()="
					+ getPrenom() + ", getEmail()=" + getEmail() + ", toString()=" + super.toString() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + "]";
		}




		
		
		
		
		

}// end classe
