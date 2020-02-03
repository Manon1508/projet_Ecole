package com.intiformation.projetecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.intiformation.projetecole.entity.Promotion;
import com.intiformation.projetecole.entity.Aide;
import com.intiformation.projetecole.entity.Promotion;

public class PromotionDao implements IGestion<Promotion> {

	@Override
	public boolean ajouter(Promotion pPromotion) {

		// 2. R�cup�rer l'entit� manager (interaction avec la bdd)

		// 2.1 R�cup d'une fabrique d'entity managed (EntityManagedFactory)
		// nom � aller chercher dans le persistence.xml (name)
		EntityManagerFactory fabriqueEntityManaged = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2.2 Recup d'une entityManaged � partir de la fabrique
		EntityManager entityManager = fabriqueEntityManaged.createEntityManager();

		// 2.3 r�cup d'une transaction (entittyTransaction) � partir de l'entityManager
		EntityTransaction transaction = entityManager.getTransaction();

		// 2.4 ouverture de la transaction
		transaction.begin();

		// 2.5 interaction avec la bdd via EM => ajout
		/**
		 * l'ajout dans la bdd se fait avec la methode persist() de EM. si la methode
		 * retourne une erreur, l'excception PersistenceException
		 */
		// try catch (rollback)
		entityManager.persist(pPromotion);

		// 2.6 Validation de la transaction avec la m�thode commit
		transaction.commit();
		// transaction.rollback();
		// 2.7 fermeture de ressources (EM et EMF)
		entityManager.close();
		fabriqueEntityManaged.close();

		return false;
	}

	@Override
	public boolean supprimer(int id) {
		// 1. r�cup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. r�cup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. r�cup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La suppression se fait avec la m�thode remove() de l'entity manager > Une
		 * instance doit �tre charg�e avec d'�tre d�truite dans la Bdd => find()
		 */
		// 4.1 chargement du formateur � supprimer
		Promotion promotionSupp = em.find(Promotion.class, id);

		// 4.3 suppr du formateur dans la Bdd
		em.remove(promotionSupp);

		// 5. validation de la tx
		tx.commit();

		// 6. lib�ration des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Promotion getById(int id) {
		// 1. r�cup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. r�cup de l'entite manager
		EntityManager em = emf.createEntityManager();

		/**
		 * NB : le get by id ne n�cessite pas de transaction
		 */

		// 3. r�cup d'un formateur par son id (pk)
		/**
		 * > via la m�thode find(classe entit�, cl� primaire) de l'EM
		 * 
		 * > find() retourne null si aucun enregistrement n'a �t� effectu�
		 */
		Promotion promotion = em.find(Promotion.class, id);

		// 5. lib�ration des ressources : fermeture des ressources :
		em.close();
		emf.close();

		return promotion;
	}

	@Override
	public void modifier(Promotion promotion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. r�cup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. r�cup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La modification se fait avec la m�thode merge() de l'entity manager > Une
		 * instance doit �tre charg�e avec d'�tre modifi�e dans la Bdd => find()
		 */
		// 4.1 chargement du formateur � modifier
		Promotion promotionModif = em.find(Promotion.class, promotion.getIdPromotion());

		// donc on modifie l'objet promotionMotif avec les param�tres de promotion
		promotionModif.setIdPromotion(promotion.getIdPromotion());
		promotionModif.setLibelle(promotion.getLibelle());
		promotionModif.setListeCours(promotion.getListeCours());
		promotionModif.setListeEtudiants(promotion.getListeEtudiants());


		// 4.3 modif du formateur dans la Bdd
		Promotion promotionMerged = em.merge(promotionModif);

		// // 4.4 affichage de la modif
		// System.out.println(promotionMerged);

		// 5. validation de la tx
		tx.commit();

		// 6. lib�ration des ressources
		em.close();
		emf.close();

	}

	/**
	 * M�thode � d�finir avec JPQL
	 */
	@Override
	public List<Promotion> getAll() {
		// 1. R�cup de l'EntityManager
		EntityManager em = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE").createEntityManager();

		// 2. Construction de la requ�te de type Criteria
		// 2.1 R�cup de l'interface principale de l'API Criteria � partir de l'EM
		// => CriteriaBuilder permet de construire la requ�te
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// 2.2 r�cup d'une instance de type CriteriaQuery
		// => CriteriaQuery = enveloppe dans laquelle on va construire notre requ�te
		// d�finit toutes les requ�tes de s�lection de Bdd
		// mod�liste tous les clauses de requ�te Select du JPQL
		CriteriaQuery<Promotion> criteriaQuery = criteriaBuilder.createQuery(Promotion.class);

		/**
		 * Reproduction de la requ�te JPQL : SELECT e FROM etudiant e
		 */

		// 2.2.1 Construction du FROM de la requ�te : FROM promotion e
		Root<Promotion> clauseFrom = criteriaQuery.from(Promotion.class);

		// 2.2.2 Construction du SELECT de la requ�te : SELECT e
		CriteriaQuery<Promotion> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM etudiant e

		// 3. Transmission de la requ�te Criteria � l'EntityManager
		TypedQuery<Promotion> getAllQuery = em.createQuery(clauseSELECT);

		// 4. Ex�cution et r�cup du r�sultat de la requ�te
		List<Promotion> listePromotions = getAllQuery.getResultList();

		return listePromotions;

	}

}// end class
