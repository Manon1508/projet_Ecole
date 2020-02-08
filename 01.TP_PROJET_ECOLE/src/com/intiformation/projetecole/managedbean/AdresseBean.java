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
 * Ce MB sert � interagir avec la facelet adresse.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="adresseBean")
@SessionScoped
public class AdresseBean implements Serializable{


	// 1. D�finition d'une adresse � ajouter
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
	 * ctor vide avec instanciation de la DAO de adresse � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
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

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des adresses dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Adresse> findAllAdressesBdd(){
		listeAdresses = adresseDao.getAll();
		return listeAdresses;
	} // end findAllAdressesBdd()
	
	/**
	 * permet de r�cup�rer dans la bdd une adresse � partir de son id
	 */
	public Adresse rechercherAdresseParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// r�cup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// r�cup de l'adresse � rechercher � partir de la Bdd
		Adresse adresseRecherchee = adresseDao.getById(adresseID);
		
		return adresseRecherchee;

	} // end rechercherAdresseParId() 

	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de adresse.xhtml
	 * le "deleteID" se retrouve dans adresse.xhtml
	 */
	public void supprimerAdresse(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// suppression de l'adresse dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( adresseDao.supprimer(adresseID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'adresse a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'adresse a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de adresse.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'adresse � modifier
	 */
	public void selectionnerAdresse(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'adresse)
		int adresseID = (int) cp.getValue();
		
		// r�cup de l'adresse � modifier � partir de la Bdd
		Adresse adresseModif = adresseDao.getById(adresseID);
		
		// affectation del'adresse � modifier � la variable adresse du managed bean 
		setAdresse(adresseModif);

	} // end selectionnerAdresse() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_adresse.xhtml<br/>
	 * permet de mettre � jour l'adresse dans la Bdd <br/>
	 * @param event
	 */
	public void modifierAdresse(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'adresse dans la bdd
		// -> la propri�t� adresse est li�e � edit_adresse.xhtml
		// -> la propri�t� adresse est charg�e avec les infos de l'adresse � modifier
		adresseDao.modifier(adresse);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle adresse' dans adresse.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet adresse qui va contenir les infos de la nouvelle adresse
	 * r�cup�r�es de ajouter_adresse.xhtml <br/>
	 * @param event
	 */
	public void initialiserAdresse(ActionEvent event) {
		// instanciation d'un objet adresse vide
		Adresse adresseAdd = new Adresse();
		
		// affectation de l'objet � la propri�t� adresse du ManagedBean
		setAdresse(adresseAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_adresse.xhtml <br/>
	 * permet d'ajouter l'adresse saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelleAdresse(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une adresse
		// -> les infos de l'adresse sont sauvegard�s dans la prop de l'adresse du ManagedBean
		adresseDao.ajouter(adresse);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end adresseBean
