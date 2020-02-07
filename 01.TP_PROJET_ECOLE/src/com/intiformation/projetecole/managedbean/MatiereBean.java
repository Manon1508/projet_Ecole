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

import com.intiformation.projetecole.dao.MatiereDao;
import com.intiformation.projetecole.entity.Aide;
import com.intiformation.projetecole.entity.Matiere;

@ManagedBean(name="matiereBean")
@SessionScoped
public class MatiereBean implements Serializable{
	// 1. D�finition d'une matiere � ajouter
	Matiere matiere1 = new Matiere();
	
	/*Props*/
	
	// liste des matieres de la Bdd pour alimenter la data table de matiere.xhtml
	private List<Matiere> listeMatieres;

	// prop matiere
	private Matiere matiere;
	
	// dao de matiere
	private MatiereDao matiereDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de matiere � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public MatiereBean() {
		matiereDao = new MatiereDao();
		this.matiere=new Matiere();
	}
	
	/*encapsulation (de matiere uniquement)*/

	/**
	 * @return the matiere
	 */
	public Matiere getMatiere() {
		return matiere;
	}

	/**
	 * @param matiere the matiere to set
	 */
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	
	/**
	 * @return the listeMatieres
	 */
	public Collection<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	/**
	 * @param listeMatieres the listeMatieres to set
	 */
	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des matieres dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Matiere> findAllMatieresBdd(){
		listeMatieres = matiereDao.getAll();
		return listeMatieres;
	} // end findAllMatieresBdd()
	
	/**
	 * permet de r�cup�rer dans la bdd une matiere � partir de son id
	 */
	public Matiere rechercherMatiereParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// r�cup de la valeur du param (l'id de l'aide)
		int matiereID = (int) cp.getValue();
		
		// r�cup de l'aide � rechercher � partir de la Bdd
		Matiere matiereRecherchee = matiereDao.getById(matiereID);
		
		return matiereRecherchee;

	} // end rechercherMatiereParId() 

	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de matiere.xhtml
	 * le "deleteID" se retrouve dans matiere.xhtml
	 */
	public void supprimerMatiere(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'matiere)
		int matiereID = (int) cp.getValue();
		
		// suppression de l'matiere dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( matiereDao.supprimer(matiereID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'matiere a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'matiere a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de matiere.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'matiere � modifier
	 */
	public void selectionnerMatiere(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'matiere)
		int matiereID = (int) cp.getValue();
		
		// r�cup de l'matiere � modifier � partir de la Bdd
		Matiere matiereModif = matiereDao.getById(matiereID);
		
		// affectation del'matiere � modifier � la variable matiere du managed bean 
		setMatiere(matiereModif);

	} // end selectionnerMatiere() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_matiere.xhtml<br/>
	 * permet de mettre � jour l'matiere dans la Bdd <br/>
	 * @param event
	 */
	public void modifierMatiere(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'matiere dans la bdd
		// -> la propri�t� matiere est li�e � edit_matiere.xhtml
		// -> la propri�t� matiere est charg�e avec les infos de l'matiere � modifier
		matiereDao.modifier(matiere);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle matiere' dans matiere.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet matiere qui va contenir les infos de la nouvelle matiere
	 * r�cup�r�es de ajouter_matiere.xhtml <br/>
	 * @param event
	 */
	public void initialiserMatiere(ActionEvent event) {
		// instanciation d'un objet matiere vide
		Matiere matiereAdd = new Matiere();
		
		// affectation de l'objet � la propri�t� matiere du ManagedBean
		setMatiere(matiereAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_matiere.xhtml <br/>
	 * permet d'ajouter l'matiere saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelleMatiere(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une matiere
		// -> les infos de l'matiere sont sauvegard�s dans la prop de l'matiere du ManagedBean
		matiereDao.ajouter(matiere);
	} // end ajouterNouveauLivre()
	
} // end matiereBean

