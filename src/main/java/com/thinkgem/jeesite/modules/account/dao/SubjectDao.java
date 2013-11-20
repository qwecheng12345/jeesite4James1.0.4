/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.account.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import com.thinkgem.jeesite.modules.account.entity.Subject;


/**
 * Subject DAO Interface
 * @author James
 * @version 2013-11-14
 */
public interface SubjectDao extends SubjectDaoCustom, CrudRepository<Subject, Long> {

}


interface SubjectDaoCustom extends BaseDao<Subject> {

}


/**
 * DAO Implement
 * @author James
 */
@Repository
class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDaoCustom {

}

