package com.mbalem.cursos.boot.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	/**	
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "id=" + id + "";
	}

}
