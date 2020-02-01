package com.intiformation.projetecole.managedbean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.projetecole.dao.AideDao;
import com.intiformation.projetecole.entity.Aide;

/**
 * Ce MB sert � interagir avec la facelet aide.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="aideBean")
@SessionScoped
public class AideBean implements Serializable{

	/*Props*/
	// liste des aides de la Bdd pour alimenter la data table de aide.xhtml
	private Collection<Aide> listeAides;
	
	// prop aide
	private Aide aide;
	
	// dao de aide
	private AideDao aideDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de aide � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que
	 */
	public AideBean() {
		aideDao = new AideDao();
	}
	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des aides dans la Bdd via la Dao. 
	 * @return
	 */
	public Collection<Aide> findAllAidesBdd(){
		listeAides = aideDao.getAll();
		return listeAides;
	} // end findAllLivresBdd()
	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de aide.xhtml
	 * le "deleteID" se retrouve dans aide.xhtml
	 */
	public void supprimerAide(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id du livre)
		int aideID = (int) cp.getValue();
		
		// suppression du livre dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( aideDao.supprimer(aideID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'aide a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'aide a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de aide.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'aide � modifier
	 */
	public void selectionnerAide(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id du livre)
		int aideID = (int) cp.getValue();
		
		// r�cup du livre � modifier � partir de la Bdd
		Aide aideModif = aideDao.getById(aideID);
		
		// affectation du livre � modifier � la variable livre du managed bean 
		setAide(aideModif);

	} // end selectionnerLivre() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_livre.xhtml<br/>
	 * permet de mettre � jour le livre dans la Bdd <br/>
	 * @param event
	 */
	public void modifierAide(ActionEvent event) {
		// invocation de la dao pour la mise � jour du livre dans la bdd
		// -> la propri�t� livre est li�e � edit_livre.xhtml
		// -> la propri�t� livre est charg�e avec les infos du livre � modifier
		aideDao.modifier(aide);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle aide' dans aide.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet aide qui va contenir les infos de la nouvelle aide
	 * r�cup�r�es de ajouter_aide.xhtml <br/>
	 * @param event
	 */
	public void initialiserAide(ActionEvent event) {
		// instanciation d'un objet livre vide
		Aide aideAdd = new Aide();
		
		// affectation de l'objet � la propri�t� livre du ManagedBean
		setAide(aideAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_aide.xhtml <br/>
	 * permet d'ajouter l'aide saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelleAide(ActionEvent event) {
		// invocation de la dao pour l'ajout du livre
		// -> les infos du livre sont sauvegard�s dans la prop du livre du ManagedBean
		aideDao.ajouter(aide);
	} // end ajouterNouveauLivre()

	
	
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
	/*et le toString (pour aide uniquement)*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AideBean [aide=" + aide + "]";
	}
	
	
	
	
	
} // end aideBean
