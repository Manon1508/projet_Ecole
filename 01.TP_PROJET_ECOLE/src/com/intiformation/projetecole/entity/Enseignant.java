package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Enseignant extends Personne implements Serializable {

	
	//________________ Props spéciales ____________//
	// aucune
	
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
	 * @param idPersonne
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

	}

	@Override
	public String toString() {
		return "Enseignant [getIdPersonne()=" + getIdPersonne() + ", getMdp()=" + getMdp() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getEmail()=" + getEmail() + ", getAdresse()=" + getAdresse()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	// Ajouter les G/S des associations

	
	
	
	
}// end classe
