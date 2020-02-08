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

import com.intiformation.projetecole.dao.CoursDao;
import com.intiformation.projetecole.entity.Cours;
import com.intiformation.projetecole.entity.Etudiant;
import com.intiformation.projetecole.entity.EtudiantCours;
import com.intiformation.projetecole.entity.Matiere;
import com.intiformation.projetecole.entity.Promotion;

@ManagedBean(name="coursBean")
@SessionScoped
public class CoursBean implements Serializable{
	// 1. D�finition d'une cours � ajouter
	List<EtudiantCours> listeEtudiantsCours1;
	Matiere matiere1 = new Matiere("pLibelle");
	Promotion promotion1 = new Promotion("pLibelle");
	Cours cours1 = new Cours("pLibelle", "pDate", "pDescription", "pDuree", promotion1, matiere1, listeEtudiantsCours1);
	
	/*Props*/
	
	// liste des courss de la Bdd pour alimenter la data table de cours.xhtml
	private List<Cours> listeCourss;

	// prop cours
	private Cours cours;
	
	// dao de cours
	private CoursDao coursDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de cours � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public CoursBean() {
		coursDao = new CoursDao();
		this.cours=new Cours();
	}
	
	/*encapsulation (de cours uniquement)*/

	/**
	 * @return the cours
	 */
	public Cours getCours() {
		return cours;
	}

	/**
	 * @param cours the cours to set
	 */
	public void setCours(Cours cours) {
		this.cours = cours;
	}

	
	/**
	 * @return the listeCourss
	 */
	public Collection<Cours> getListeCourss() {
		return listeCourss;
	}

	/**
	 * @param listeCourss the listeCourss to set
	 */
	public void setListeCourss(List<Cours> listeCourss) {
		this.listeCourss = listeCourss;
	}

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des courss dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Cours> findAllCourssBdd(){
		listeCourss = coursDao.getAll();
		return listeCourss;
	} // end findAllCourssBdd()
	
	/**
	 * permet de r�cup�rer dans la bdd une cours � partir de son id
	 */
	public Cours rechercherCoursParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// r�cup de la valeur du param (l'id de l'aide)
		int coursID = (int) cp.getValue();
		
		// r�cup de l'aide � rechercher � partir de la Bdd
		Cours coursRecherchee = coursDao.getById(coursID);
		
		return coursRecherchee;

	} // end rechercherCoursParId() 

	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de cours.xhtml
	 * le "deleteID" se retrouve dans cours.xhtml
	 */
	public void supprimerCours(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'cours)
		int coursID = (int) cp.getValue();
		
		// suppression de l'cours dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( coursDao.supprimer(coursID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("La cours a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'cours a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de cours.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'cours � modifier
	 */
	public void selectionnerCours(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'cours)
		int coursID = (int) cp.getValue();
		
		// r�cup de l'cours � modifier � partir de la Bdd
		Cours coursModif = coursDao.getById(coursID);
		
		// affectation del'cours � modifier � la variable cours du managed bean 
		setCours(coursModif);

	} // end selectionnerCours() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_cours.xhtml<br/>
	 * permet de mettre � jour l'cours dans la Bdd <br/>
	 * @param event
	 */
	public void modifierCours(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'cours dans la bdd
		// -> la propri�t� cours est li�e � edit_cours.xhtml
		// -> la propri�t� cours est charg�e avec les infos de l'cours � modifier
		coursDao.modifier(cours);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle cours' dans cours.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet cours qui va contenir les infos de la nouvelle cours
	 * r�cup�r�es de ajouter_cours.xhtml <br/>
	 * @param event
	 */
	public void initialiserCours(ActionEvent event) {
		// instanciation d'un objet cours vide
		Cours coursAdd = new Cours();
		
		// affectation de l'objet � la propri�t� cours du ManagedBean
		setCours(coursAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_cours.xhtml <br/>
	 * permet d'ajouter l'cours saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouveauCours(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une cours
		// -> les infos de l'cours sont sauvegard�s dans la prop de l'cours du ManagedBean
		coursDao.ajouter(cours);
	} // end ajouterNouveauLivre()
	
} // end coursBean

