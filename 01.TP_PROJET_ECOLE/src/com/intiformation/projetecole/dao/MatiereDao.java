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

import com.intiformation.projetecole.entity.Aide;

public class MatiereDao implements IGestion<Aide> {

	@Override
	public boolean ajouter(Aide pAide) {

		// 1. D�finition d'une aide � ajouter
		//Aide aide = new Aide("pPage", "pContenu");

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
		entityManager.persist(aide);

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
		 * > La suppression se fait avec la m�thode remove() de l'entity manager
		 * > Une instance doit �tre charg�e avec d'�tre d�truite dans la Bdd
		 *   => find()
		 */
			// 4.1 chargement du formateur � modifier
			Aide aideSupp = em.find(Aide.class, 2);
			
			// 4.3 suppr du formateur dans la Bdd
			em.remove(aideSupp);
			
		// 5. validation de la tx
		tx.commit();
		
		// 6. lib�ration des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Aide getById(int id) {
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
		Aide aide = em.find(Aide.class, 1);
		
		// 4. affichage
		System.out.println("Get aide by id : ");
		System.out.println("\t " + aide);
		
		// 5. lib�ration des ressources : fermeture des ressources : 
		em.close();
		emf.close();

		return aide;
	}

	@Override
	public boolean modifier(Aide t) {
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
		 * > La modification se fait avec la m�thode merge() de l'entity manager
		 * > Une instance doit �tre charg�e avec d'�tre modifi�e dans la Bdd
		 *   => find()
		 */
			// 4.1 chargement du formateur � modifier
			Aide aideModif = em.find(Aide.class, 1);
			
			// 4.2 modif du formateur
			aideModif.setPage("INDEX");
			aideModif.setContenu("Bienvenue sur le projet WebCole d�di� � l'apprentissage de JavaEE");
			
			// 4.3 modif du formateur dans la Bdd
			Aide aideMerged = em.merge(aideModif);
			
			// 4.4 affichage de la modif
			System.out.println(aideMerged);
			
		// 5. validation de la tx
		tx.commit();
		
		// 6. lib�ration des ressources
		em.close();
		emf.close();
		return false;

	}

	/**
	 * M�thode � d�finir avec JPQL
	 */
	@Override
	public List<Aide> getAll() {
		// 1. R�cup de l'EntityManager
		EntityManager em = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE")
									  .createEntityManager();
		
		// 2. Construction de la requ�te de type Criteria
			// 2.1 R�cup de l'interface principale de l'API Criteria � partir de l'EM
			// => CriteriaBuilder permet de construire la requ�te
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			
			// 2.2 r�cup d'une instance de type CriteriaQuery
			// => CriteriaQuery = enveloppe dans laquelle on va construire notre requ�te
			// 					  d�finit toutes les requ�tes de s�lection de Bdd
			// 					  mod�liste tous les clauses de requ�te Select du JPQL
			CriteriaQuery<Aide> criteriaQuery = criteriaBuilder.createQuery(Aide.class);
			
			/**
			 * Reproduction de la requ�te JPQL : SELECT e FROM etudiant e
			 */
			
				// 2.2.1 Construction du FROM de la requ�te : FROM aide e
				Root<Aide> clauseFrom = criteriaQuery.from(Aide.class);
				
				// 2.2.2 Construction du SELECT de la requ�te : SELECT e
				CriteriaQuery<Aide> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM etudiant e
				
		// 3. Transmission de la requ�te Criteria � l'EntityManager
		TypedQuery<Aide> getAllQuery = em.createQuery(clauseSELECT);
		
		// 4. Ex�cution et r�cup du r�sultat de la requ�te
		List<Aide> listeAides = getAllQuery.getResultList();
		
		// 5. Affichage
		System.out.println("Liste des �tudiants dans la Bdd : ");
		for (Aide help : listeAides) {
			System.out.println(help);
		}
		return listeAides;

	}
}// end class
