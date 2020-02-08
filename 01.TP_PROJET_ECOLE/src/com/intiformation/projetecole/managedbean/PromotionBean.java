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

import com.intiformation.projetecole.dao.PromotionDao;
import com.intiformation.projetecole.entity.Promotion;

@ManagedBean(name="promotionBean")
@SessionScoped
public class PromotionBean implements Serializable{
	// 1. D�finition d'une promotion � ajouter
	Promotion promotion1 = new Promotion("pLibelle");
	
	/*Props*/
	
	// liste des promotions de la Bdd pour alimenter la data table de promotion.xhtml
	private List<Promotion> listePromotions;

	// prop promotion
	private Promotion promotion;
	
	// dao de promotion
	private PromotionDao promotionDao;
	/*ctor */

	/**
	 * ctor vide avec instanciation de la DAO de promotion � l'int�rieur
	 * (comme dans le projet Gestion Biblioth�que)
	 */
	public PromotionBean() {
		promotionDao = new PromotionDao();
		this.promotion=new Promotion();
	}
	
	/*encapsulation (de promotion uniquement)*/

	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	
	/**
	 * @return the listePromotions
	 */
	public Collection<Promotion> getListePromotions() {
		return listePromotions;
	}

	/**
	 * @param listePromotions the listePromotions to set
	 */
	public void setListePromotions(List<Promotion> listePromotions) {
		this.listePromotions = listePromotions;
	}

	/*m�thodes*/
	/**
	 * r�cup�ration de la liste des promotions dans la Bdd via la Dao. 
	 * @return
	 */
	public List<Promotion> findAllPromotionsBdd(){
		listePromotions = promotionDao.getAll();
		return listePromotions;
	} // end findAllPromotionsBdd()
	
	/**
	 * permet de r�cup�rer dans la bdd une promotion � partir de son id
	 */
	public Promotion rechercherPromotionParId(ActionEvent event) {
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("searchID");
		
		// r�cup de la valeur du param (l'id de l'aide)
		int promotionID = (int) cp.getValue();
		
		// r�cup de l'aide � rechercher � partir de la Bdd
		Promotion promotionRecherchee = promotionDao.getById(promotionID);
		
		return promotionRecherchee;

	} // end rechercherPromotionParId() 

	
	/**
	 * m�thode invoqu�e au click du lien "supprimer" de la data table de promotion.xhtml
	 * le "deleteID" se retrouve dans promotion.xhtml
	 */
	public void supprimerPromotion(ActionEvent event) {
		// r�cup du param pass� au click du lien "supprimer"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("deleteID");
		
		// r�cup de la valeur du param (l'id de l'promotion)
		int promotionID = (int) cp.getValue();
		
		// suppression de l'promotion dans la Bdd via la dao
		// --> envoi d'un message vers la vue
		FacesContext context = null;
		if( promotionDao.supprimer(promotionID)) {
			// -> suppression OK
			// -> envoi d'un message vers la vue avec la classe FacesMessage
			// r�cup du contexte
			context = FacesContext.getCurrentInstance();
			
			// d�finition du message
			FacesMessage messageDelete = new FacesMessage("La promotion a �t� supprim�e avec succ�s");
			
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
									   						 "La suppression de l'promotion a �chou�"));
			
		} // end else
	} // end supprimerLivre() 
	
	/**
	 * m�thode invoqu�e au click du lien "modifier" de la datatable
	 * de promotion.xhtml <br/>
	 * permet de r�cup�rer dans la bdd les infos concernant l'promotion � modifier
	 */
	public void selectionnerPromotion(ActionEvent event) {
		// r�cup du param pass� au click du lien "modifier"
		//"deleteID" est l'id du composant f:param
		UIParameter cp = (UIParameter) event.getComponent()
											.findComponent("editID");
		
		// r�cup de la valeur du param (l'id de l'promotion)
		int promotionID = (int) cp.getValue();
		
		// r�cup de l'promotion � modifier � partir de la Bdd
		Promotion promotionModif = promotionDao.getById(promotionID);
		
		// affectation del'promotion � modifier � la variable promotion du managed bean 
		setPromotion(promotionModif);

	} // end selectionnerPromotion() 
	
	/**
	 * m�thode invoqu�e au click du bouton modifier dans edit_promotion.xhtml<br/>
	 * permet de mettre � jour l'promotion dans la Bdd <br/>
	 * @param event
	 */
	public void modifierPromotion(ActionEvent event) {
		// invocation de la dao pour la mise � jour de l'promotion dans la bdd
		// -> la propri�t� promotion est li�e � edit_promotion.xhtml
		// -> la propri�t� promotion est charg�e avec les infos de l'promotion � modifier
		promotionDao.modifier(promotion);
	} // end modifierLivre()

	/**
	 * m�thode invoqu�e au click du bouton 'ajouter une nouvelle promotion' dans promotion.xhtml<br/>
	 * permet d'instancier (de pr�parer) un objet promotion qui va contenir les infos de la nouvelle promotion
	 * r�cup�r�es de ajouter_promotion.xhtml <br/>
	 * @param event
	 */
	public void initialiserPromotion(ActionEvent event) {
		// instanciation d'un objet promotion vide
		Promotion promotionAdd = new Promotion();
		
		// affectation de l'objet � la propri�t� promotion du ManagedBean
		setPromotion(promotionAdd);
	} // end initialiserLivre()
	
	/**
	 * m�thode invoqu�e au click sur le bouton "ajouter" dans ajouter_promotion.xhtml <br/>
	 * permet d'ajouter l'promotion saisie dans le formulaire � la bdd
	 * @param event
	 */
	public void ajouterNouvellePromotion(ActionEvent event) {
		// invocation de la dao pour l'ajout d'une promotion
		// -> les infos de l'promotion sont sauvegard�s dans la prop de l'promotion du ManagedBean
		promotionDao.ajouter(promotion);
	} // end ajouterNouveauLivre()
	
} // end promotionBean

