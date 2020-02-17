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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enseignant extends Personne implements Serializable {

	
	//________________ Props spéciales ____________//

	
	
	//=================Association==================//
	/**
	 * Type de la relation: un e enseignant associé à une adresse
	 * 						one Enseignant to one Adresse => @OneToOne
	 * 
	 * relation de cascade avec : cascade = CascadeType.Persist
	 * 	->Ajout
	 * 
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADRESSE_ID", // nom de la FK 
				referencedColumnName="id_adresse")// nom de la colonne dans la classe associée // gestion de la FK
	private Adresse adresse;
	
	// FAIRE ENSEIGNE
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="ENSEIGNANT_ID"), inverseJoinColumns=@JoinColumn(name="MATIERE_ID"))
	private List<Matiere> listeMatieres;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Enseigne", joinColumns=@JoinColumn(name="ENSEIGNANT_ID"), inverseJoinColumns=@JoinColumn(name="PROMOTION_ID"))
	private List<Promotion> listePromotions;
	
	
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
	public Enseignant(int idPersonne, String mdp, String nom, String prenom, String email) {
		super(idPersonne, mdp, nom, prenom, email);

	}

	/**
	 * ctor ss id
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Enseignant(String mdp, String nom, String prenom, String email) {
		super(mdp, nom, prenom, email);
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * ctor ss id avec adresse
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 */
	
	public Enseignant( String mdp, String nom, String prenom, String email, Adresse adresse) {
		super( mdp, nom, prenom, email);
		this.adresse = adresse;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	public List<Promotion> getListePromotions() {
		return listePromotions;
	}

	public void setListePromotions(List<Promotion> listePromotions) {
		this.listePromotions = listePromotions;
	}

	@Override
	public String toString() {
		return "Enseignant [adresse=" + adresse + ", listeMatieres=" + listeMatieres + ", listePromotions="
				+ listePromotions + ", getAdresse()=" + getAdresse() + ", getListeMatieres()=" + getListeMatieres()
				+ ", getListePromotions()=" + getListePromotions() + ", getIdPersonne()=" + getIdPersonne()
				+ ", getMdp()=" + getMdp() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getEmail()="
				+ getEmail() + ", toString()=" + "]";
	}


	
	


	
	
	
	
}// end classe
