package com.intiformation.projetecole.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Enseigne implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnseigne;

	// _______________Association___________//

	@ManyToOne
	@JoinColumn(name = "MATIERE_ID", referencedColumnName = "idMatiere")
	private Matiere matiere;
	@JoinColumn(name = "ENSEIGNANT_ID", referencedColumnName = "idEnseignant")
	private Enseignant enseignant;
	@JoinColumn(name = "PROMOTION_ID", referencedColumnName = "idPromotion")
	private Promotion promotion;

	/**
	 * ctor ss id
	 * 
	 * @param idEnseigne
	 */
	public Enseigne(Matiere matiere, Enseignant enseignant, Promotion promotion) {
		super();
		this.matiere = matiere;
		this.enseignant = enseignant;
		this.promotion = promotion;
	}

	/**
	 * ctor vide
	 */
	public Enseigne() {
		super();
	}

	/**
	 * ctor complet
	 * 
	 * @param idEnseigne
	 * @param matiere
	 * @param enseignant
	 * @param promotion
	 */
	public Enseigne(int idEnseigne, Matiere matiere, Enseignant enseignant, Promotion promotion) {
		super();
		this.idEnseigne = idEnseigne;
		this.matiere = matiere;
		this.enseignant = enseignant;
		this.promotion = promotion;
	}

	public int getIdEnseigne() {
		return idEnseigne;
	}

	public void setIdEnseigne(int idEnseigne) {
		this.idEnseigne = idEnseigne;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	
}// end class
