package com.intiformation.projetecole.managedbean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import com.intiformation.projetecole.dao.AdministrateurDao;
import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.entity.Adresse;


/**
 * Ce MB sert � interagir avec la facelet administrateur.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="administrateurBean")
@SessionScoped
public class AdministrateurBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7848011578324559649L;
	// 1. D�finition d'une administrateur � ajouter
	Adresse adresse1 = new Adresse("pRue", "pCp", "pVille");
	Administrateur admin1 = new Administrateur("pMpd", "pNom", "pPrenom", "pEmail", adresse1);
	
	/*Props*/
	
	// liste des administrateurs de la Bdd pour alimenter la data table de administrateur.xhtml
	private List<Administrateur> listeAdministrateurs;

	// prop administrateur
	private Administrateur administrateur;
	
	// dao de administrateur
	private AdministrateurDao administrateurDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de administrateur � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public AdministrateurBean() {
		administrateurDao = new AdministrateurDao();
		this.administrateur=new Administrateur();
	}
	
	/*encapsulation (de administrateur uniquement)*/

	/**
	 * @return the administrateur
	 */
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	/**
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	
	/**
	 * @return the listeAdministrateurs
	 */
	public Collection<Administrateur> getListeAdministrateurs() {
		return listeAdministrateurs;
	}

	/**
	 * @param listeAdministrateurs the listeAdministrateurs to set
	 */
	public void setListeAdministrateurs(List<Administrateur> listeAdministrateurs) {
		this.listeAdministrateurs = listeAdministrateurs;
	}

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des administrateurs dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Administrateur> findAllAdministrateursBdd(){
		listeAdministrateurs = administrateurDao.getAll();
		return listeAdministrateurs;
	} // end findAllAdministrateursBdd()
	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de administrateur.xhtml
	 * le "deleteID" se retrouve dans administrateur.xhtml
	 */
	public void supprimerAdministrateur(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'administrateur)
		int administrateurID = (int) cp.getValue();
		
		// suppression de l'administrateur dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( administrateurDao.supprimer(administrateurID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'administrateur a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'administrateur a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de administrateur.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'administrateur � modifier
	 */
	public void selectionnerAdministrateur(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'administrateur)
		int administrateurID = (int) cp.getValue();
		
		// r�cup de l'administrateur � modifier � partir de la Bdd
		Administrateur administrateurModif = administrateurDao.getById(administrateurID);
		
		// affectation del'administrateur � modifier � la variable administrateur du managed bean 
		setAdministrateur(administrateurModif);

	} // end selectionnerAdministrateur() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_administrateur.xhtml<br/>
	 * permet de mettre � jour l'administrateur dans la Bdd <br/>
	 * @param event
	 */
	public void modifierAdministrateur(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'administrateur dans la bdd
		// -> la propri�t� administrateur est li�e � edit_administrateur.xhtml
		// -> la propri�t� administrateur est charg�e avec les infos de l'administrateur � modifier
		administrateurDao.modifier(administrateur);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle administrateur' dans administrateur.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet administrateur qui va contenir les infos de la nouvelle administrateur
	 * r�cup�r�es de ajouter_administrateur.xhtml <br/>
	 * @param event
	 */
	public void initialiserAdministrateur(ActionEvent event) {
		// instanciation d'un objet administrateur vide
		Administrateur administrateurAdd = new Administrateur();
		
		// affectation de l'objet � la propri�t� administrateur du ManagedBean
		setAdministrateur(administrateurAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_administrateur.xhtml <br/>
	 * permet d'ajouter l'administrateur saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelleAdministrateur(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une administrateur
		// -> les infos de l'administrateur sont sauvegard�s dans la prop de l'administrateur du ManagedBean
		administrateurDao.ajouter(administrateur);
	} // end ajouterNouveauLivre()


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Administrateur admin = (Administrateur) value;
        return admin.getEmail().toLowerCase().contains(filterText)
                || admin.getNom().toLowerCase().contains(filterText)
                || admin.getPrenom().toLowerCase().contains(filterText)
                || admin.getIdPersonne() < filterInt;
    }
    
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

     

	
	
} // end administrateurBean
