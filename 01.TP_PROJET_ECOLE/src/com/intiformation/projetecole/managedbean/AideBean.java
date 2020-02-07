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

import com.intiformation.projetecole.dao.AideDao;
import com.intiformation.projetecole.entity.Aide;

/**
 * Ce MB sert à interagir avec la facelet aide.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="aideBean")
@SessionScoped
public class AideBean implements Serializable{


	// 1. Définition d'une aide à ajouter
	Aide aide1 = new Aide("pPage", "pContenu");
	
	/*Props*/
	
	// liste des aides de la Bdd pour alimenter la data table de aide.xhtml
	private List<Aide> listeAides;

	// prop aide
	private Aide aide;
	
	// dao de aide
	private AideDao aideDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de aide à l'intérieur
	 * (comme dans le projet Gestion Bibliothèque)
	 */
	public AideBean() {
		aideDao = new AideDao();
		this.aide=new Aide();
	}
	
	/*encapsulation (de aide uniquement)*/

	/**
	 * @return the aide
	 */
	public Aide getAide() {
		return aide;
	}

	/**
	 * @param aide the aide to set
	 */
	public void setAide(Aide aide) {
		this.aide = aide;
	}

	
	/**
	 * @return the listeAides
	 */
	public Collection<Aide> getListeAides() {
		return listeAides;
	}

	/**
	 * @param listeAides the listeAides to set
	 */
	public void setListeAides(List<Aide> listeAides) {
		this.listeAides = listeAides;
	}

	/*méthodes*/
	/**
	 * récupération de la liste des aides dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Aide> findAllAidesBdd(){
		listeAides = aideDao.getAll();
		return listeAides;
	} // end findAllAidesBdd()
	
	/**
	 * méthode invoquée au click du lien "supprimer" de la data table de aide.xhtml
	 * le "deleteID" se retrouve dans aide.xhtml
	 */
	public void supprimerAide(ActionEvent event) {
		// récup du param passé au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// récup de la valeur du param (l'id de l'aide)
		int aideID = (int) cp.getValue();
		
		// suppression de l'aide dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( aideDao.supprimer(aideID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// récup du contexte
			context = FacesContext.getCurrentInstance();
			
			// définition du message
			FacesMessage messageDelete = new FacesMessage("L'aide a été supprimée avec succès");
			
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
									   						 "La suppression de l'aide a échoué"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * permet de récupérer dans la bdd une aide à partir de son id
	 */
	public Aide rechercherAideParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// récup de la valeur du param (l'id de l'aide)
		int aideID = (int) cp.getValue();
		
		// récup de l'aide à rechercher à partir de la Bdd
		Aide aideRecherchee = aideDao.getById(aideID);
		
		return aideRecherchee;

	} // end rechercherAideParId() 
	
	/**
	 * méthode invoquée au click du lien "modifier" de la datatable
	 * de aide.xhtml <br/>
	 * permet de récupérer dans la bdd les infos concernant l'aide à modifier
	 */
	public void selectionnerAide(ActionEvent event) {
		// récup du param passé au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// récup de la valeur du param (l'id de l'aide)
		int aideID = (int) cp.getValue();
		
		// récup de l'aide à modifier à partir de la Bdd
		Aide aideModif = aideDao.getById(aideID);
		
		// affectation del'aide à modifier à la variable aide du managed bean 
		setAide(aideModif);

	} // end selectionnerAide() 
	
	/**
	 * méthode invoquée au click du bouton modifier dans edit_aide.xhtml<br/>
	 * permet de mettre à jour l'aide dans la Bdd <br/>
	 * @param event
	 */
	public void modifierAide(ActionEvent event) {
		// invocation de la dao pour la mise à jour de l'aide dans la bdd
		// -> la propriété aide est liée à edit_aide.xhtml
		// -> la propriété aide est chargée avec les infos de l'aide à modifier
		aideDao.modifier(aide);
	} // end modifierLivre()

	/**
	 * méthode invoquée au click du bouton 'ajouter une nouvelle aide' dans aide.xhtml<br/>
	 * permet d'instancier (de préparer) un objet aide qui va contenir les infos de la nouvelle aide
	 * récupérées de ajouter_aide.xhtml <br/>
	 * @param event
	 */
	public void initialiserAide(ActionEvent event) {
		// instanciation d'un objet aide vide
		Aide aideAdd = new Aide();
		
		// affectation de l'objet à la propriété aide du ManagedBean
		setAide(aideAdd);
	} // end initialiserLivre()
	
	/**
	 * méthode invoquée au click sur le bouton "ajouter" dans ajouter_aide.xhtml <br/>
	 * permet d'ajouter l'aide saisie dans le formulaire à la bdd
	 * @param event
	 */
	public void ajouterNouvelleAide(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une aide
		// -> les infos de l'aide sont sauvegardés dans la prop de l'aide du ManagedBean
		aideDao.ajouter(aide);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end aideBean
