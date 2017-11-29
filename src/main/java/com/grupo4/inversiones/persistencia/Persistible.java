package com.grupo4.inversiones.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator; 
 
@MappedSuperclass public class Persistible implements Serializable { 

	private static final long serialVersionUID = 1L;
	
  //@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="gen",strategy="increment")
  	@GeneratedValue(generator="gen")
	@Id
    protected Long id;
 
 
    protected Persistible() {
    	
    } 
 
    @Column(name = "id")
    public Long getId() {
    	return id;
    	} 
 
    public void setId(Long id) {
    	this.id = id;
    	} 
  
 
}