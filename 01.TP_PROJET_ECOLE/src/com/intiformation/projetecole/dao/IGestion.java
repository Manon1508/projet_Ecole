/**
 * 
 */
package com.intiformation.projetecole.dao;

import java.util.List;

import com.intiformation.projetecole.entity.Aide;

/**
 * Interface avec les 5 méthodes principales
 * 
 * @author IN-BR-026
 *
 */
public interface IGestion<T> {

	/**
	 * Méthode générique Ajouter 
	 * 
	 * @return
	 */
	public boolean ajouter(T t);

	/**
	 * Méthode générique Supprimer
	 * @return
	 */
	public boolean supprimer(int id);


	/**
	 * Méthode générique GetById
	 * @return
	 */
	public T getById(int id);

	/**
	 * Méthode générique Modifier
	 * @return
	 */
	public void modifier (T t);

	/**
	 * Cette méthode est faite avec JPQL
	 * Méthode générique GetAll
	 * @return
	 */
	public List<T> getAll();


}// end Interface
