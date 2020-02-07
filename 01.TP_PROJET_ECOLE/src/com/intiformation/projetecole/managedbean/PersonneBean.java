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
 * Ce MB sert � interagir avec la facelet personne.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="personneBean")
@SessionScoped
public class PersonneBean implements Serializable{


	// 1. D�finition d'une personne � ajouter
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
	 * ctor vide avec instanciation de la DAO de personne � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
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

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des personnes dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Personne> findAllPersonnesBdd(){
		listePersonnes = personneDao.getAll();
		return listePersonnes;
	} // end findAllPersonnesBdd()
	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de personne.xhtml
	 * le "deleteID" se retrouve dans personne.xhtml
	 */
	public void supprimerPersonne(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'personne)
		int personneID = (int) cp.getValue();
		
		// suppression de l'personne dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( personneDao.supprimer(personneID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'personne a �t� supprim�e avec succ�s");
			
			// envoi du message vers la vue
			// null permet l'envoi d'un message global pour toute la page. 
			// si � la place de null, on entre entre doubles cotes, l'id du composant
			context.addMessage(null, messageDelete);
			// pour r�cup�rer le message dans la vue, on utilise la balise h:message ou h:messages
		} else {
			// -> suppression not OK
			context.addMessage(null,
							   new FacesMessage(FacesMessage.SEVERITY_FATAL, 
									   						 "Echec de la suppression", 
									   						 "La suppression de l'personne a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de personne.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'personne � modifier
	 */
	public void selectionnerPersonne(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'personne)
		int personneID = (int) cp.getValue();
		
		// r�cup de l'personne � modifier � partir de la Bdd
		Personne personneModif = personneDao.getById(personneID);
		
		// affectation del'personne � modifier � la variable personne du managed bean 
		setPersonne(personneModif);

	} // end selectionnerPersonne() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_personne.xhtml<br/>
	 * permet de mettre � jour l'personne dans la Bdd <br/>
	 * @param event
	 */
	public void modifierPersonne(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'personne dans la bdd
		// -> la propri�t� personne est li�e � edit_personne.xhtml
		// -> la propri�t� personne est charg�e avec les infos de l'personne � modifier
		personneDao.modifier(personne);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle personne' dans personne.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet personne qui va contenir les infos de la nouvelle personne
	 * r�cup�r�es de ajouter_personne.xhtml <br/>
	 * @param event
	 */
	public void initialiserPersonne(ActionEvent event) {
		// instanciation d'un objet personne vide
		Personne personneAdd = new Personne();
		
		// affectation de l'objet � la propri�t� personne du ManagedBean
		setPersonne(personneAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_personne.xhtml <br/>
	 * permet d'ajouter l'personne saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvellePersonne(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une personne
		// -> les infos de l'personne sont sauvegard�s dans la prop de l'personne du ManagedBean
		personneDao.ajouter(personne);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end personneBean
