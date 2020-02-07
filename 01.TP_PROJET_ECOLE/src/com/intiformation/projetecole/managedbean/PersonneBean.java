package com.intiformation.projetecole.managedbean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.intiformation.projetecole.dao.PersonneDao;
import com.intiformation.projetecole.entity.Personne;

/**
 * Ce MB sert à interagir avec la facelet personne.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="personneBean")
@SessionScoped
public class PersonneBean implements Serializable{


	// 1. Définition d'une personne à ajouter
	Personne personne1 = new Personne("pMpd", "pNom", "pPrenom", "pEmail");
	
	/*Props*/
	
	// liste des personnes de la Bdd pour alimenter la data table de personne.xhtml
	private List<Personne> listePersonnes;

	// prop personne
	private Personne personne;
	
	// dao de personne
	private PersonneDao personneDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de personne à l'intérieur
	 * (comme dans le projet Gestion Bibliothèque)
	 */
	public PersonneBean() {
		personneDao = new PersonneDao();
		this.personne=new Personne();
	}
	
	/*encapsulation (de personne uniquement)*/

	/**
	 * @return the personne
	 */
	public Personne getPersonne() {
		return personne;
	}

	/**
	 * @param personne the personne to set
	 */
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	
	/**
	 * @return the listePersonnes
	 */
	public Collection<Personne> getListePersonnes() {
		return listePersonnes;
	}

	/**
	 * @param listePersonnes the listePersonnes to set
	 */
	public void setListePersonnes(List<Personne> listePersonnes) {
		this.listePersonnes = listePersonnes;
	}

	/*méthodes*/
	/**
	 * récupération de la liste des personnes dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Personne> findAllPersonnesBdd(){
		listePersonnes = personneDao.getAll();
		return listePersonnes;
	} // end findAllPersonnesBdd()
	
	/**
	 * méthode invoquée au click du lien "supprimer" de la data table de personne.xhtml
	 * le "deleteID" se retrouve dans personne.xhtml
	 */
	public void supprimerPersonne(ActionEvent event) {
		// récup du param passé au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// récup de la valeur du param (l'id de l'personne)
		int personneID = (int) cp.getValue();
		
		// suppression de l'personne dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( personneDao.supprimer(personneID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// récup du contexte
			context = FacesContext.getCurrentInstance();
			
			// définition du message
			FacesMessage messageDelete = new FacesMessage("L'personne a été supprimée avec succès");
			
			// envoi du message vers la vue
			// null permet l'envoi d'un message global pour toute la page. 
			// si à la place de null, on entre entre doubles cotes, l'id du composant
			context.addMessage(null, messageDelete);
			// pour récupérer le message dans la vue, on utilise la balise h:message ou h:messages
		} else {
			// -> suppression not OK
			context.addMessage(null,
							   new FacesMessage(FacesMessage.SEVERITY_FATAL, 
									   						 "Echec de la suppression", 
									   						 "La suppression de l'personne a échoué"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * méthode invoquée au click du lien "modifier" de la datatable
	 * de personne.xhtml <br/>
	 * permet de récupérer dans la bdd les infos concernant l'personne à modifier
	 */
	public void selectionnerPersonne(ActionEvent event) {
		// récup du param passé au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// récup de la valeur du param (l'id de l'personne)
		int personneID = (int) cp.getValue();
		
		// récup de l'personne à modifier à partir de la Bdd
		Personne personneModif = personneDao.getById(personneID);
		
		// affectation del'personne à modifier à la variable personne du managed bean 
		setPersonne(personneModif);

	} // end selectionnerPersonne() 
	
	/**
	 * méthode invoquée au click du bouton modifier dans edit_personne.xhtml<br/>
	 * permet de mettre à jour l'personne dans la Bdd <br/>
	 * @param event
	 */
	public void modifierPersonne(ActionEvent event) {
		// invocation de la dao pour la mise à jour de l'personne dans la bdd
		// -> la propriété personne est liée à edit_personne.xhtml
		// -> la propriété personne est chargée avec les infos de l'personne à modifier
		personneDao.modifier(personne);
	} // end modifierLivre()

	/**
	 * méthode invoquée au click du bouton 'ajouter une nouvelle personne' dans personne.xhtml<br/>
	 * permet d'instancier (de préparer) un objet personne qui va contenir les infos de la nouvelle personne
	 * récupérées de ajouter_personne.xhtml <br/>
	 * @param event
	 */
	public void initialiserPersonne(ActionEvent event) {
		// instanciation d'un objet personne vide
		Personne personneAdd = new Personne();
		
		// affectation de l'objet à la propriété personne du ManagedBean
		setPersonne(personneAdd);
	} // end initialiserLivre()
	
	/**
	 * méthode invoquée au click sur le bouton "ajouter" dans ajouter_personne.xhtml <br/>
	 * permet d'ajouter l'personne saisie dans le formulaire à la bdd
	 * @param event
	 */
	public void ajouterNouvellePersonne(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une personne
		// -> les infos de l'personne sont sauvegardés dans la prop de l'personne du ManagedBean
		personneDao.ajouter(personne);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end personneBean
