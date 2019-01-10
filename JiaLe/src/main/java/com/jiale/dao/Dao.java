package com.jiale.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author JiaLe 接口与对应的XML文件中Mapper的namespace做对应 方法与id做对应 参数与参数对应 返回值与返回值对应
 */

public interface Dao {

	// 查询
	public List<?> select(String str, Map<?, ?> map);

	// 增加
	public int insert(String str, Map<?, ?> map);

	// 修改
	public int update(String str, Map<?, ?> map);

	// 删除
	public int delete(String str, Map<?, ?> map);
}
