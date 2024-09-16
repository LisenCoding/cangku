
package com.dao;

import java.util.List;
import java.util.Map;

/**
 * 通用接口
 */
public interface CommonDao{
	List<Map<String, Object>> pieSum(Map<String, Object> params);
	List<Map<String, Object>> pieCount(Map<String, Object> params);
	List<Map<String, Object>> barSumOne(Map<String, Object> params);
	List<Map<String, Object>> barCountOne(Map<String, Object> params);
	List<Map<String, Object>> barSumTwo(Map<String, Object> params);
	List<Map<String, Object>> barCountTwo(Map<String, Object> params);
	Map<String, Object> queryScore(Map<String, Object> params);
	/**
	 * 新的级联字典表的   分组求和方法
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectGroupSum(Map<String, Object> params);
	/**
	 * 新的级联字典表的   分组条数统计统计方法方法
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> newSelectGroupCount(Map<String, Object> params);
	/**
	 * 柱状图求和
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> barSum(Map<String, Object> params);

	/**
	 * 柱状图统计
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> barCount(Map<String, Object> params);
}
