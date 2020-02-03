package com.intiformation.projetecole.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EtudiantCours implements Serializable {

	//_______________ Prop ___________//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEtudiantCours;
	private String motif;
	private String absence;
	
	//_______________Association___________//
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COURS_ID", referencedColumnName = "idCours")
	private Cours cours;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ETUDIANT_ID", referencedColumnName = "idEtudiant")
	private Etudiant etudiant;
	
	//_______________ctor___________//
	


	/**
	 * ctor vide
	 */
	public EtudiantCours() {
		super();
	}

	/**
	 * ctor complet
	 */
	public EtudiantCours(int idEtudiantCours, String motif, String absence, Cours cours, Etudiant etudiant) {
		super();
		this.idEtudiantCours = idEtudiantCours;
		this.motif = motif;
		this.absence = absence;
		this.cours = cours;
		this.etudiant = etudiant;
	}

	/**
	 * ctor ss id 
	 * @param motif
	 * @param absence
	 * @param cours
	 * @param etudiant
	 */
	public EtudiantCours(String motif, String absence, Cours cours, Etudiant etudiant) {
		super();
		this.motif = motif;
		this.absence = absence;
		this.cours = cours;
		this.etudiant = etudiant;
	}

	@Override
	public String toString() {
		return "EtudiantCours [idEtudiantCours=" + idEtudiantCours + ", motif=" + motif + ", absence=" + absence
				+ ", cours=" + cours + ", etudiant=" + etudiant + "]";
	}

	public int getIdEtudiantCours() {
		return idEtudiantCours;
	}

	public void setIdEtudiantCours(int idEtudiantCours) {
		this.idEtudiantCours = idEtudiantCours;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getAbsence() {
		return absence;
	}

	public void setAbsence(String absence) {
		this.absence = absence;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	

	
	
	

}// end class
