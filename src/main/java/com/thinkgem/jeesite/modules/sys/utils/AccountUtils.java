package com.thinkgem.jeesite.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.account.dao.SubjectDao;
import com.thinkgem.jeesite.modules.account.entity.Subject;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;

public class AccountUtils extends BaseService {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static SubjectDao subjectDao = SpringContextHolder.getBean(SubjectDao.class);

	private static final String[] DC_ACCOUNT = {"type", "level"};
	
	public static final String CACHE_USER = "user";
	public static final String CACHE_SUBJECT = "subject";
	
	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userDao.findOne(principal.getId());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}
	
	public static List<Subject> getSubjectList(String...dcString) {
		List<Subject> subjectList = Lists.newArrayList();
//		User user = getUser();
//			if (user.isAdmin()){
//				officeList = officeDao.findAllList();
//			}else{
//				officeList = officeDao.findAllChild(user.getOffice().getId(), "%,"+user.getOffice().getId()+",%");
//			}
		DetachedCriteria dc = subjectDao.createDetachedCriteria();
//			dc.add(dataScopeFilter(user, dc.getAlias(), ""));
		for (int i = 0; i < dcString.length; i++) {
			dc.add(Restrictions.eq(DC_ACCOUNT[i], dcString[i]));
		}
		dc.add(Restrictions.eq("delFlag", Subject.DEL_FLAG_NORMAL));
		subjectList = subjectDao.find(dc);
		return subjectList;
	}
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}
}
