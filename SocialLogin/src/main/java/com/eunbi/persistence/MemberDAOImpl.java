package com.eunbi.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession sqlSession;
	
	static final String NAMESPACE ="com.eunbi.mapper.MemberMapper";
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	
}
