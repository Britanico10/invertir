package com.grupo4.inversiones.repositorio;

import javax.persistence.EntityManager;

import com.grupo4.inversiones.entidades.Balance;

public class Balances extends Repositorio {
	public Balances(EntityManager em) {
		super(em);
	}
	
	   public Balance buscarPorId(Long id){   
		   return em.find(Balance.class, id);
	   }
	   public void persistir(Balance balance){
		   em.getTransaction().begin();
		   em.persist(balance);
		   em.getTransaction().commit();
	   }
}