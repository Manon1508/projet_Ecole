/**
 * 
 */
package com.intiformation.projetecole.dao;

import java.util.List;

import com.intiformation.projetecole.entity.Aide;

/**
 * Interface avec les 5 m�thodes principales
 * 
 * @author IN-BR-026
 *
 */
public interface IGestion<T> {

	/**
	 * M�thode g�n�rique Ajouter 
	 * 
	 * @return
	 */
	public boolean ajouter(T t);

	/**
	 * M�thode g�n�rique Supprimer
	 * @return
	 */
	public boolean supprimer(int id);


	/**
	 * M�thode g�n�rique GetById
	 * @return
	 */
	public T getById(int id);

	/**
	 * M�thode g�n�rique Modifier
	 * @return
	 */
	public void modifier (T t);

	/**
	 * Cette m�thode est faite avec JPQL
	 * M�thode g�n�rique GetAll
	 * @return
	 */
	public List<T> getAll();


}// end Interface
