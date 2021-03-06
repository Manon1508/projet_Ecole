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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.intiformation.projetecole.dao.EnseignantDao;
import com.intiformation.projetecole.entity.Enseignant;
import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;
import com.intiformation.projetecole.entity.Enseignant;

/**
 * Ce MB sert � interagi r avec la facelet enseignant.xhtml, en faisant le lien avec l'entity et la DAO
 * @author IN-BR-026
 *
 */
@ManagedBean(name="enseignantBean")
@SessionScoped
public class EnseignantBean implements Serializable{


	// 1. D�finition d'une enseignant � ajouter
	Adresse adresse1 = new Adresse("pRue", "pCp", "pVille");
	Enseignant ens1 = new Enseignant("pMpd", "pNom", "pPrenom", "pEmail", adresse1);
	
	/*Props*/
	
	// liste des enseignants de la Bdd pour alimenter la data table de enseignant.xhtml
	private Collection<Enseignant> listeenseignant;

	// prop enseignant
	private Enseignant enseignant;
	
	// dao de enseignant
	private EnseignantDao enseignantDao;
	
	
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de enseignant � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public EnseignantBean() {
		enseignantDao = new EnseignantDao();
		enseignant = new Enseignant();
	}
	

	/*encapsulation */



	public Adresse getAdresse1() {
		return adresse1;
	}


	public void setAdresse1(Adresse adresse1) {
		this.adresse1 = adresse1;
	}


	public Enseignant getEns1() {
		return ens1;
	}


	public void setEns1(Enseignant ens1) {
		this.ens1 = ens1;
	}


	public Collection<Enseignant> getListeenseignant() {
		return listeenseignant;
	}


	public void setListeenseignant(Collection<Enseignant> listeenseignant) {
		this.listeenseignant = listeenseignant;
	}


	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}


	public EnseignantDao getEnseignantDao() {
		return enseignantDao;
	}


	public void setEnseignantDao(EnseignantDao enseignantDao) {
		this.enseignantDao = enseignantDao;
	}



	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des enseignants dans la Bdd via la Dao. 
	 * @return
	 */

	public Collection<Enseignant> findAllenseignantBdd(){
		listeenseignant = enseignantDao.getAll();
		return listeenseignant;
	}


	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de enseignant.xhtml
	 * le "deleteID" se retrouve dans enseignant.xhtml
	 */
	public void supprimerEnseignant(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'enseignant)
		int enseignantID = (int) cp.getValue();
		
		// suppression de l'enseignant dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = FacesContext.getCurrentInstance();
		if( enseignantDao.supprimer(enseignantID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
		
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("L'enseignant a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'enseignant a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	

	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de enseignant.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'enseignant � modifier
	 */
	public void selectionnerEnseignant(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'enseignant)
		int enseignantID = (int) cp.getValue();
		
		// r�cup de l'enseignant � modifier � partir de la Bdd
		Enseignant enseignantModif = enseignantDao.getById(enseignantID);
		
		// affectation del'enseignant � modifier � la variable enseignant du managed bean 
		setEnseignant(enseignantModif);

	} // end selectionnerEnseignant() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_enseignant.xhtml<br/>
	 * permet de mettre � jour l'enseignant dans la Bdd <br/>
	 * @param event
	 */
	public void modifierEnseignant(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'enseignant dans la bdd
		// -> la propri�t� enseignant est li�e � edit_enseignant.xhtml
		// -> la propri�t� enseignant est charg�e avec les infos de l'enseignant � modifier
		enseignantDao.modifier(enseignant);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle enseignant' dans enseignant.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet enseignant qui va contenir les infos de la nouvelle enseignant
	 * r�cup�r�es de ajouter_enseignant.xhtml <br/>
	 * @param event
	 */
	public void initialiserEnseignant(ActionEvent event) {
		// instanciation d'un objet enseignant vide
		Enseignant enseignantAdd = new Enseignant();
		
		// affectation de l'objet � la propri�t� enseignant du ManagedBean
		setEnseignant(enseignantAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_enseignant.xhtml <br/>
	 * permet d'ajouter l'enseignant saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvelleEnseignant(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une enseignant
		// -> les infos de l'enseignant sont sauvegard�s dans la prop de l'enseignant du ManagedBean
		enseignantDao.ajouter(enseignant);
	} // end ajouterNouveauLivre()

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Enseignant ens = (Enseignant) value;
        return ens.getEmail().toLowerCase().contains(filterText)
                || ens.getNom().toLowerCase().contains(filterText)
                || ens.getPrenom().toLowerCase().contains(filterText)
                || ens.getIdPersonne() < filterInt;
    }
    
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

     

	
	
} // end enseignantBean
