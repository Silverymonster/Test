package com.jiale.dao.dosupport;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiale.dao.Dao;

@Repository("daoSupport")
public class DaoSupport implements Dao {
	DaoSupport() {
		System.out.println("DaoSupport被构造");
	}

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 查询
	@Override
	public List<?> select(String str, Map<?, ?> map) {
		// System.out.println("select----" + map.get("username"));
		return sqlSessionTemplate.selectList(str, map);
	}

	// 增加
	@Override
	public int insert(String str, Map<?, ?> map) {
		return sqlSessionTemplate.insert(str, map);
	}

	// 修改
	@Override
	public int update(String str, Map<?, ?> map) {
		return sqlSessionTemplate.update(str, map);
	}

	// 删除
	@Override
	public int delete(String str, Map<?, ?> map) {
		return sqlSessionTemplate.delete(str, map);
	}
}
