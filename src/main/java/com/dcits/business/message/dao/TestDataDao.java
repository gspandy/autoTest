package com.dcits.business.message.dao;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.message.bean.TestData;

/**
 * 测试数据Dao接口类
 * 
 * @author xuwangcheng
 * @version 1.0.0.0,20170502
 *
 */
public interface TestDataDao extends BaseDao<TestData>{
	
	/**
	 * 更新某个属性
	 * @param dataId
	 * @param dataName 属性名
	 * @param dataValue 要更新的值
	 */
	void updateDataValue(Integer dataId, String dataName, String dataValue);
	
	/**
	 * 通过数据标记来查找测试数据
	 * @param dataDiscr
	 * @param messageSceneId
	 * @return
	 */
	TestData findByDisrc(String dataDiscr, Integer messageSceneId);
}
