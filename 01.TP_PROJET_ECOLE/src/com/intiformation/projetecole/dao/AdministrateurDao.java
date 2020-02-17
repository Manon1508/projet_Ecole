package com.intiformation.projetecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.tool.JpaUtil;


public class AdministrateurDao implements IGestion<Administrateur> {

	@Override
	public boolean ajouter(Administrateur pAdministrateur) {

		// 2. Récupérer l'entité manager (interaction avec la bdd)

		// 2.1 Récup d'une fabrique d'entity managed (EntityManagedFactory)
		// nom à aller chercher dans le persistence.xml (name)
		EntityManagerFactory fabriqueEntityManaged = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2.2 Recup d'une entityManaged à partir de la fabrique
		EntityManager entityManager = fabriqueEntityManaged.createEntityManager();

		// 2.3 récup d'une transaction (entittyTransaction) à partir de l'entityManager
		EntityTransaction transaction = entityManager.getTransaction();

		// 2.4 ouverture de la transaction
		transaction.begin();

		// 2.5 interaction avec la bdd via EM => ajout
		/**
		 * l'ajout dans la bdd se fait avec la methode persist() de EM. si la methode
		 * retourne une erreur, l'excception PersistenceException
		 */
		// try catch (rollback)
		entityManager.persist(pAdministrateur);

		// 2.6 Validation de la transaction avec la méthode commit
		transaction.commit();
		// transaction.rollback();
		// 2.7 fermeture de ressources (EM et EMF)
		entityManager.close();
		fabriqueEntityManaged.close();

		return false;
	}

	@Override
	public boolean supprimer(int id) {
		// 1. récup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. récup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La suppression se fait avec la méthode remove() de l'entity manager > Une
		 * instance doit être chargée avec d'être détruite dans la Bdd => find()
		 */
		// 4.1 chargement du formateur à supprimer
		Administrateur adminSupp = em.find(Administrateur.class, id);

		// 4.3 suppr du formateur dans la Bdd
		em.remove(adminSupp);

		// 5. validation de la tx
		tx.commit();

		// 6. libération des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Administrateur getById(int id) {
		// 1. récup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		/**
		 * NB : le get by id ne nécessite pas de transaction
		 */

		// 3. récup d'un formateur par son id (pk)
		/**
		 * > via la méthode find(classe entité, clé primaire) de l'EM
		 * 
		 * > find() retourne null si aucun enregistrement n'a été effectué
		 */
		Administrateur admin = em.find(Administrateur.class, id);

		// 5. libération des ressources : fermeture des ressources :
		em.close();
		emf.close();

		return admin;
	}

	@Override
	public void modifier(Administrateur admin) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. récup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La modification se fait avec la méthode merge() de l'entity manager > Une
		 * instance doit être chargée avec d'être modifiée dans la Bdd => find()
		 */
		// 4.1 chargement du formateur à modifier
		Administrateur adminModif = em.find(Administrateur.class, admin.getIdPersonne());

		// donc on modifie l'objet adminMotif avec les paramètres de admin
		adminModif.setIdPersonne(admin.getIdPersonne());
		adminModif.setNom(admin.getNom());
		adminModif.setPrenom(admin.getPrenom());
		adminModif.setMdp(admin.getMdp());
		adminModif.setEmail(admin.getEmail());
		adminModif.setAdresse(admin.getAdresse());

		// 4.3 modif du formateur dans la Bdd
		Administrateur adminMerged = em.merge(adminModif);

		// // 4.4 affichage de la modif
		// System.out.println(adminMerged);

		// 5. validation de la tx
		tx.commit();

		// 6. libération des ressources
		em.close();
		emf.close();

	}

	/**
	 * Méthode à définir avec JPQL
	 */
	@Override
	public List<Administrateur> getAll() {
		// 1. Récup de l'EntityManager
		EntityManager em = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE").createEntityManager();

		// 2. Construction de la requête de type Criteria
		// 2.1 Récup de l'interface principale de l'API Criteria à partir de l'EM
		// => CriteriaBuilder permet de construire la requête
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// 2.2 récup d'une instance de type CriteriaQuery
		// => CriteriaQuery = enveloppe dans laquelle on va construire notre requête
		// définit toutes les requêtes de sélection de Bdd
		// modéliste tous les clauses de requête Select du JPQL
		CriteriaQuery<Administrateur> criteriaQuery = criteriaBuilder.createQuery(Administrateur.class);

		/**
		 * Reproduction de la requête JPQL : SELECT e FROM etudiant e
		 */

		// 2.2.1 Construction du FROM de la requête : FROM admin e
		Root<Administrateur> clauseFrom = criteriaQuery.from(Administrateur.class);

		// 2.2.2 Construction du SELECT de la requête : SELECT e
		CriteriaQuery<Administrateur> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM etudiant e

		// 3. Transmission de la requête Criteria à l'EntityManager
		TypedQuery<Administrateur> getAllQuery = em.createQuery(clauseSELECT);

		// 4. Exécution et récup du résultat de la requête
		List<Administrateur> listeAdministrateurs = getAllQuery.getResultList();

		return listeAdministrateurs;

	}
	
	// méthode pour savior si l'administrateur existe dans la bdd
	
	EntityManager em = JpaUtil.getInstance();
	
public boolean isExist(String pIdentifiant, String pMdp) {
		
		try {
			
			Query query = em.createQuery("select count(a.idPersonne) from administrateur a where a.email=?1 and a.mdp =?2"); 
			query.setParameter(1, pIdentifiant);
			query.setParameter(2, pMdp);
			Long existence = (Long) query.getSingleResult();
			
			return (existence==1)? true:false; 
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}// end class
