package com.intiformation.projetecole.managedbean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.intiformation.projetecole.dao.EnseignantDao;
import com.intiformation.projetecole.entity.Enseignant;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;
import com.intiformation.projetecole.entity.Enseignant;

/**
 * Ce MB sert à interagir avec la facelet enseignant.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="enseignantBean")
@SessionScoped
public class EnseignantBean implements Serializable{


	// 1. Définition d'une enseignant à ajouter
	Adresse adresse1 = new Adresse("pRue", "pCp", "pVille");
	Enseignant admin1 = new Enseignant("pMpd", "pNom", "pPrenom", "pEmail", adresse1);
	
	/*Props*/
	
	// liste des enseignants de la Bdd pour alimenter la data table de enseignant.xhtml
	private List<Enseignant> listeEnseignants;

	// prop enseignant
	private Enseignant enseignant;
	
	// dao de enseignant
	private EnseignantDao enseignantDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de enseignant à l'intérieur
	 * (comme dans le projet Gestion Bibliothèque)
	 */
	public EnseignantBean() {
		enseignantDao = new EnseignantDao();
		this.enseignant=new Enseignant();
	}
	
	/*encapsulation (de enseignant uniquement)*/

	/**
	 * @return the enseignant
	 */
	public Enseignant getEnseignant() {
		return enseignant;
	}

	/**
	 * @param enseignant the enseignant to set
	 */
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	
	/**
	 * @return the listeEnseignants
	 */
	public Collection<Enseignant> getListeEnseignants() {
		return listeEnseignants;
	}

	/**
	 * @param listeEnseignants the listeEnseignants to set
	 */
	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}

	/*méthodes*/
	/**
	 * récupération de la liste des enseignants dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Enseignant> findAllEnseignantsBdd(){
		listeEnseignants = enseignantDao.getAll();
		return listeEnseignants;
	} // end findAllEnseignantsBdd()
	
	/**
	 * méthode invoquée au click du lien "supprimer" de la data table de enseignant.xhtml
	 * le "deleteID" se retrouve dans enseignant.xhtml
	 */
	public void supprimerEnseignant(ActionEvent event) {
		// récup du param passé au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// récup de la valeur du param (l'id de l'enseignant)
		int enseignantID = (int) cp.getValue();
		
		// suppression de l'enseignant dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( enseignantDao.supprimer(enseignantID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// récup du contexte
			context = FacesContext.getCurrentInstance();
			
			// définition du message
			FacesMessage messageDelete = new FacesMessage("L'enseignant a été supprimée avec succès");
			
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
									   						 "La suppression de l'enseignant a échoué"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * méthode invoquée au click du lien "modifier" de la datatable
	 * de enseignant.xhtml <br/>
	 * permet de récupérer dans la bdd les infos concernant l'enseignant à modifier
	 */
	public void selectionnerEnseignant(ActionEvent event) {
		// récup du param passé au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// récup de la valeur du param (l'id de l'enseignant)
		int enseignantID = (int) cp.getValue();
		
		// récup de l'enseignant à modifier à partir de la Bdd
		Enseignant enseignantModif = enseignantDao.getById(enseignantID);
		
		// affectation del'enseignant à modifier à la variable enseignant du managed bean 
		setEnseignant(enseignantModif);

	} // end selectionnerEnseignant() 
	
	/**
	 * méthode invoquée au click du bouton modifier dans edit_enseignant.xhtml<br/>
	 * permet de mettre à jour l'enseignant dans la Bdd <br/>
	 * @param event
	 */
	public void modifierEnseignant(ActionEvent event) {
		// invocation de la dao pour la mise à jour de l'enseignant dans la bdd
		// -> la propriété enseignant est liée à edit_enseignant.xhtml
		// -> la propriété enseignant est chargée avec les infos de l'enseignant à modifier
		enseignantDao.modifier(enseignant);
	} // end modifierLivre()

	/**
	 * méthode invoquée au click du bouton 'ajouter une nouvelle enseignant' dans enseignant.xhtml<br/>
	 * permet d'instancier (de préparer) un objet enseignant qui va contenir les infos de la nouvelle enseignant
	 * récupérées de ajouter_enseignant.xhtml <br/>
	 * @param event
	 */
	public void initialiserEnseignant(ActionEvent event) {
		// instanciation d'un objet enseignant vide
		Enseignant enseignantAdd = new Enseignant();
		
		// affectation de l'objet à la propriété enseignant du ManagedBean
		setEnseignant(enseignantAdd);
	} // end initialiserLivre()
	
	/**
	 * méthode invoquée au click sur le bouton "ajouter" dans ajouter_enseignant.xhtml <br/>
	 * permet d'ajouter l'enseignant saisie dans le formulaire à la bdd
	 * @param event
	 */
	public void ajouterNouvelleEnseignant(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une enseignant
		// -> les infos de l'enseignant sont sauvegardés dans la prop de l'enseignant du ManagedBean
		enseignantDao.ajouter(enseignant);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end enseignantBean
