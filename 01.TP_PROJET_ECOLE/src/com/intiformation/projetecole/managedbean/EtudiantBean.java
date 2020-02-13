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

import com.intiformation.projetecole.dao.EtudiantDao;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;

/**
 * Ce MB sert à interagir avec la facelet etudiant.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="etudiantBean")
@SessionScoped
public class EtudiantBean implements Serializable{


	// 1. Définition d'une etudiant à ajouter
	Adresse adresse1 = new Adresse("pRue", "pCp", "pVille");
	Etudiant etudiant1 = new Etudiant("pMpd", "pNom", "pPrenom", "pEmail", "pDate", adresse1);
	
	/*Props*/
	
	// liste des etudiants de la Bdd pour alimenter la data table de etudiant.xhtml
	private List<Etudiant> listeEtudiants;

	// prop etudiant
	private Etudiant etudiant;
	
	// dao de etudiant
	private EtudiantDao etudiantDao;
	
	
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de etudiant à l'intérieur
	 * (comme dans le projet Gestion Bibliothèque)
	 */
	public EtudiantBean() {
		etudiantDao = new EtudiantDao();
		this.etudiant=new Etudiant();
	}
	
	/*encapsulation (de etudiant uniquement)*/

	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	
	/**
	 * @return the listeEtudiants
	 */
	public Collection<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}

	/**
	 * @param listeEtudiants the listeEtudiants to set
	 */
	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	/*méthodes*/
	/**
	 * récupération de la liste des etudiants dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Etudiant> findAllEtudiantsBdd(){
		listeEtudiants = etudiantDao.getAll();
		return listeEtudiants;
	} // end findAllEtudiantsBdd()
	
	/**
	 * méthode invoquée au click du lien "supprimer" de la data table de etudiant.xhtml
	 * le "deleteID" se retrouve dans etudiant.xhtml
	 */
	public void supprimerEtudiant(ActionEvent event) {
		// récup du param passé au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// récup de la valeur du param (l'id de l'etudiant)
		int etudiantID = (int) cp.getValue();
		
		// suppression de l'etudiant dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( etudiantDao.supprimer(etudiantID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// récup du contexte
			context = FacesContext.getCurrentInstance();
			
			// définition du message
			FacesMessage messageDelete = new FacesMessage("L'etudiant a été supprimée avec succès");
			
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
									   						 "La suppression de l'etudiant a échoué"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * méthode invoquée au click du lien "modifier" de la datatable
	 * de etudiant.xhtml <br/>
	 * permet de récupérer dans la bdd les infos concernant l'etudiant à modifier
	 */
	public void selectionnerEtudiant(ActionEvent event) {
		// récup du param passé au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// récup de la valeur du param (l'id de l'etudiant)
		int etudiantID = (int) cp.getValue();
		
		// récup de l'etudiant à modifier à partir de la Bdd
		Etudiant etudiantModif = etudiantDao.getById(etudiantID);
		
		// affectation del'etudiant à modifier à la variable etudiant du managed bean 
		setEtudiant(etudiantModif);

	} // end selectionnerEtudiant() 
	
	/**
	 * méthode invoquée au click du bouton modifier dans edit_etudiant.xhtml<br/>
	 * permet de mettre à jour l'etudiant dans la Bdd <br/>
	 * @param event
	 */
	public void modifierEtudiant(ActionEvent event) {
		// invocation de la dao pour la mise à jour de l'etudiant dans la bdd
		// -> la propriété etudiant est liée à edit_etudiant.xhtml
		// -> la propriété etudiant est chargée avec les infos de l'etudiant à modifier
		etudiantDao.modifier(etudiant);
	} // end modifierLivre()

	/**
	 * méthode invoquée au click du bouton 'ajouter une nouvelle etudiant' dans etudiant.xhtml<br/>
	 * permet d'instancier (de préparer) un objet etudiant qui va contenir les infos de la nouvelle etudiant
	 * récupérées de ajouter_etudiant.xhtml <br/>
	 * @param event
	 */
	public void initialiserEtudiant(ActionEvent event) {
		// instanciation d'un objet etudiant vide
		Etudiant etudiantAdd = new Etudiant();
		
		// affectation de l'objet à la propriété etudiant du ManagedBean
		setEtudiant(etudiantAdd);
	} // end initialiserLivre()
	
	/**
	 * méthode invoquée au click sur le bouton "ajouter" dans ajouter_etudiant.xhtml <br/>
	 * permet d'ajouter l'etudiant saisie dans le formulaire à la bdd
	 * @param event
	 */
	public void ajouterNouvelleEtudiant(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une etudiant
		// -> les infos de l'etudiant sont sauvegardés dans la prop de l'etudiant du ManagedBean
		etudiantDao.ajouter(etudiant);
	} // end ajouterNouveauLivre()

	

     

	
	
} // end etudiantBean
