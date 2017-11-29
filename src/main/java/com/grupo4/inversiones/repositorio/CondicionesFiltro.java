package com.grupo4.inversiones.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;

public class CondicionesFiltro extends Repositorio {

	public CondicionesFiltro(EntityManager em) {
		super(em);
	}
	
	   public CondicionesFiltro buscarPorId(Long id){   
		   return em.find(CondicionesFiltro.class, id);
	   }
	   
	   public void borrarPorId(Long id){   
		   em.getTransaction().begin();
		   em.remove(buscarPorId(id));
		   em.getTransaction().commit();
	   }
	   
	   public long buscarDuenio(long id) {
		   return em.find(CondicionFiltro.class, id).getDuenio();
	   }
	   
	   public CondicionFiltro buscarPorNombre(String nombre){
		   Query query = em.createQuery("SELECT c FROM CondicionFiltro c WHERE c.nombreCondicion = :nombre").setParameter("nombre", nombre);
		   List<CondicionFiltro> resultados = query.getResultList();
		   if (resultados.size() == 0) return null;
		   return (CondicionFiltro) resultados.get(0);
	   }
	   
	   public List<CondicionFiltro> buscarTodas(long idUsuario){
		   EntityManager em2 = App.EM_FACTORY.createEntityManager();
		   Query query = em2.createQuery("SELECT c FROM CondicionFiltro c WHERE c.duenio = :idUsuario").setParameter("idUsuario", idUsuario);
		   List<CondicionFiltro> condiciones = query.getResultList();
		   return condiciones;
	   }
	   
	   public void persistir(CondicionFiltro condicion){
		   em.getTransaction().begin();
		   em.persist(condicion);
		   em.getTransaction().commit();
	   }
	
}
