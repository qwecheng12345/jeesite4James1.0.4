/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.account.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Business Entity
 * @author James
 * @version 2013-11-14
 */
@Entity
@Table(name = "sys_business")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Business extends DataEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Account oppositeAccount;
	private Double oppositeBalance;
	private Account ownerAccount;
	private Double ownerBalance;
	private String type;

	public Business() {
		super();
	}
	
	public Business(Long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opposite_account_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Account getOppositeAccount() {
		return oppositeAccount;
	}

	public void setOppositeAccount(Account oppositeAccount) {
		this.oppositeAccount = oppositeAccount;
	}

	public Double getOppositeBalance() {
		return oppositeBalance;
	}

	public void setOppositeBalance(Double oppositeBalance) {
		this.oppositeBalance = oppositeBalance;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_account_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Account getOwnerAccount() {
		return ownerAccount;
	}

	public void setOwnerAccount(Account ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	public Double getOwnerBalance() {
		return ownerBalance;
	}

	public void setOwnerBalance(Double ownerBalance) {
		this.ownerBalance = ownerBalance;
	}

	@Length(min = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
