package com.intiformation.projetecole.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse implements Serializable {

	// _________________ Propriétés ______________//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adresse")
	private int idAdresse;

	@Column(name = "rue")
	private String rue;

	@Column(name = "code_postal")
	private String cp;

	@Column(name = "ville")
	private String ville;

	// _________________ Ctors ______________//

	/**
	 * ctor complet
	 * 
	 * @param idAdresse
	 * @param rue
	 * @param cp
	 * @param ville
	 */
	public Adresse(int idAdresse, String rue, String cp, String ville) {
		super();
		this.idAdresse = idAdresse;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
	}

	/**
	 * ctor ss id
	 * 
	 * @param rue
	 * @param cp
	 * @param ville
	 */
	public Adresse(String rue, String cp, String ville) {
		super();
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
	}

	/**
	 * ctor vide
	 */
	public Adresse() {
		super();
	}
	
	// _________________ G/S ______________//

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", rue=" + rue + ", cp=" + cp + ", ville=" + ville + "]";
	}

}// end class
