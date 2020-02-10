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

import com.intiformation.projetecole.dao.EtudiantCoursDao;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Cours;
import com.intiformation.projetecole.entity.Etudiant;
import com.intiformation.projetecole.entity.EtudiantCours;
import com.intiformation.projetecole.entity.Matiere;
import com.intiformation.projetecole.entity.Promotion;

@ManagedBean(name="etudiantCoursBean")
@SessionScoped
public class EtudiantCoursBean implements Serializable{
	// 1. D�finition d'une etudiantCours � ajouter
	List<Cours> listeCours1;
	List<Promotion> listePromotion1;
	List<EtudiantCours> listeEtudiantsCours1;
	Adresse adresse1 = new Adresse("pRue", "pCp", "pVille");
	Matiere matiere1 = new Matiere("pLibelle");
	Promotion promotion1 = new Promotion("pLibelle");
	Etudiant etudiant1 = new Etudiant("pMdp", "pNom", "pPrenom", "pEmail", "pUrlPhoto", "pDateNaissance", adresse1, listePromotion1, listeEtudiantsCours1);
	Cours cours1 = new Cours("pLibelle", "pDate", "pDescription", "pDuree", promotion1, matiere1, listeEtudiantsCours1);
	EtudiantCours etudiantCours1 = new EtudiantCours("pMotif", "pAbsence", cours1, etudiant1);
	
	/*Props*/
	
	// liste des etudiantCourss de la Bdd pour alimenter la data table de etudiantCours.xhtml
	private List<EtudiantCours> listeEtudiantCours;

	// prop etudiantCours
	private EtudiantCours etudiantCours;
	
	// dao de etudiantCours
	private EtudiantCoursDao etudiantCoursDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de etudiantCours � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public EtudiantCoursBean() {
		etudiantCoursDao = new EtudiantCoursDao();
		this.etudiantCours=new EtudiantCours();
	}
	
	/*encapsulation (de etudiantCours uniquement)*/

	/**
	 * @return the etudiantCours
	 */
	public EtudiantCours getEtudiantCours() {
		return etudiantCours;
	}

	/**
	 * @param etudiantCours the etudiantCours to set
	 */
	public void setEtudiantCours(EtudiantCours etudiantCours) {
		this.etudiantCours = etudiantCours;
	}

	
	/**
	 * @return the listeEtudiantCourss
	 */
	public Collection<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}

	/**
	 * @param listeEtudiantCourss the listeEtudiantCourss to set
	 */
	public void setListeEtudiantCourss(List<EtudiantCours> listeEtudiantCourss) {
		this.listeEtudiantCours = listeEtudiantCours;
	}

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des etudiantCourss dans la Bdd via la Dao. 
	 * @return
	 */
	public List<EtudiantCours> findAllEtudiantCoursBdd(){
		listeEtudiantCours = etudiantCoursDao.getAll();
		return listeEtudiantCours;
	} // end findAllEtudiantCourssBdd()
	
	/**
	 * permet de r�cup�rer dans la bdd une etudiantCours � partir de son id
	 */
	public EtudiantCours rechercherEtudiantCoursParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// r�cup de la valeur du param (l'id de l'aide)
		int etudiantCoursID = (int) cp.getValue();
		
		// r�cup de l'aide � rechercher � partir de la Bdd
		EtudiantCours etudiantCoursRecherchee = etudiantCoursDao.getById(etudiantCoursID);
		
		return etudiantCoursRecherchee;

	} // end rechercherEtudiantCoursParId() 

	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de etudiantCours.xhtml
	 * le "deleteID" se retrouve dans etudiantCours.xhtml
	 */
	public void supprimerEtudiantCours(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'etudiantCours)
		int etudiantCoursID = (int) cp.getValue();
		
		// suppression de l'etudiantCours dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( etudiantCoursDao.supprimer(etudiantCoursID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("La etudiantCours a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'etudiantCours a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de etudiantCours.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'etudiantCours � modifier
	 */
	public void selectionnerEtudiantCours(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'etudiantCours)
		int etudiantCoursID = (int) cp.getValue();
		
		// r�cup de l'etudiantCours � modifier � partir de la Bdd
		EtudiantCours etudiantCoursModif = etudiantCoursDao.getById(etudiantCoursID);
		
		// affectation del'etudiantCours � modifier � la variable etudiantCours du managed bean 
		setEtudiantCours(etudiantCoursModif);

	} // end selectionnerEtudiantCours() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_etudiantCours.xhtml<br/>
	 * permet de mettre � jour l'etudiantCours dans la Bdd <br/>
	 * @param event
	 */
	public void modifierEtudiantCours(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'etudiantCours dans la bdd
		// -> la propri�t� etudiantCours est li�e � edit_etudiantCours.xhtml
		// -> la propri�t� etudiantCours est charg�e avec les infos de l'etudiantCours � modifier
		etudiantCoursDao.modifier(etudiantCours);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle etudiantCours' dans etudiantCours.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet etudiantCours qui va contenir les infos de la nouvelle etudiantCours
	 * r�cup�r�es de ajouter_etudiantCours.xhtml <br/>
	 * @param event
	 */
	public void initialiserEtudiantCours(ActionEvent event) {
		// instanciation d'un objet etudiantCours vide
		EtudiantCours etudiantCoursAdd = new EtudiantCours();
		
		// affectation de l'objet � la propri�t� etudiantCours du ManagedBean
		setEtudiantCours(etudiantCoursAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_etudiantCours.xhtml <br/>
	 * permet d'ajouter l'etudiantCours saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelEtudiantCours(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une etudiantCours
		// -> les infos de l'etudiantCours sont sauvegard�s dans la prop de l'etudiantCours du ManagedBean
		etudiantCoursDao.ajouter(etudiantCours);
	} // end ajouterNouveauLivre()
	
} // end etudiantCoursBean