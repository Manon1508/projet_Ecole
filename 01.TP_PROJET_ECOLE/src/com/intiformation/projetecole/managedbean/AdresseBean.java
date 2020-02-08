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

import com.intiformation.projetecole.dao.AdresseDao;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Cours;

/**
 * Ce MB sert à interagir avec la facelet adresse.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="adresseBean")
@SessionScoped
public class AdresseBean implements Serializable{


	// 1. Définition d'une adresse à ajouter
	Adresse adresse1 = new Adresse("rue", "33000", "Bordeaux");
	
	/*Props*/
	
	// liste des adresses de la Bdd pour alimenter la data table de adresse.xhtml
	private List<Adresse> listeAdresses;

	// prop adresse
	private Adresse adresse;
	
	// dao de adresse
	private AdresseDao adresseDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de adresse à l'intérieur
	 * (comme dans le projet Gestion Bibliothèque)
	 */
	public AdresseBean() {
		adresseDao = new AdresseDao();
		this.adresse=new Adresse();
	}
	
	/*encapsulation (de adresse uniquement)*/

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	
	/**
	 * @return the listeAdresses
	 */
	public Collection<Adresse> getListeAdresses() {
		return listeAdresses;
	}

	/**
	 * @param listeAdresses the listeAdresses to set
	 */
	public void setListeAdresses(List<Adresse> listeAdresses) {
		this.listeAdresses = listeAdresses;
	}

	/*méthodes*/
	/**
	 * récupération de la liste des adresses dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Adresse> findAllAdressesBdd(){
		listeAdresses = adresseDao.getAll();
		return listeAdresses;
	} // end findAllAdressesBdd()
	
	/**
	 * permet de récupérer dans la bdd une adresse à partir de son id
	 */
	public Adresse rechercherAdresseParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// récup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// récup de l'adresse à rechercher à partir de la Bdd
		Adresse adresseRecherchee = adresseDao.getById(adresseID);
		
		return adresseRecherchee;

	} // end rechercherAdresseParId() 

	
	/**
	 * méthode invoquée au click du lien "supprimer" de la data table de adresse.xhtml
	 * le "deleteID" se retrouve dans adresse.xhtml
	 */
	public void supprimerAdresse(ActionEvent event) {
		// récup du param passé au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// récup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// suppression de l'adresse dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( adresseDao.supprimer(adresseID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// récup du contexte
			context = FacesContext.getCurrentInstance();
			
			// définition du message
			FacesMessage messageDelete = new FacesMessage("L'adresse a été supprimée avec succès");
			
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
									   						 "La suppression de l'adresse a échoué"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * méthode invoquée au click du lien "modifier" de la datatable
	 * de adresse.xhtml <br/>
	 * permet de récupérer dans la bdd les infos concernant l'adresse à modifier
	 */
	public void selectionnerAdresse(ActionEvent event) {
		// récup du param passé au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// récup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// récup de l'adresse à modifier à partir de la Bdd
		Adresse adresseModif = adresseDao.getById(adresseID);
		
		// affectation del'adresse à modifier à la variable adresse du managed bean 
		setAdresse(adresseModif);

	} // end selectionnerAdresse() 
	
	/**
	 * méthode invoquée au click du bouton modifier dans edit_adresse.xhtml<br/>
	 * permet de mettre à jour l'adresse dans la Bdd <br/>
	 * @param event
	 */
	public void modifierAdresse(ActionEvent event) {
		// invocation de la dao pour la mise à jour de l'adresse dans la bdd
		// -> la propriété adresse est liée à edit_adresse.xhtml
		// -> la propriété adresse est chargée avec les infos de l'adresse à modifier
		adresseDao.modifier(adresse);
	} // end modifierLivre()

	/**
	 * méthode invoquée au click du bouton 'ajouter une nouvelle adresse' dans adresse.xhtml<br/>
	 * permet d'instancier (de préparer) un objet adresse qui va contenir les infos de la nouvelle adresse
	 * récupérées de ajouter_adresse.xhtml <br/>
	 * @param event
	 */
	public void initialiserAdresse(ActionEvent event) {
		// instanciation d'un objet adresse vide
		Adresse adresseAdd = new Adresse();
		
		// affectation de l'objet à la propriété adresse du ManagedBean
		setAdresse(adresseAdd);
	} // end initialiserLivre()
	
	/**
	 * méthode invoquée au click sur le bouton "ajouter" dans ajouter_adresse.xhtml <br/>
	 * permet d'ajouter l'adresse saisie dans le formulaire à la bdd
	 * @param event
	 */
	public void ajouterNouvelleAdresse(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une adresse
		// -> les infos de l'adresse sont sauvegardés dans la prop de l'adresse du ManagedBean
		adresseDao.ajouter(adresse);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end adresseBean
