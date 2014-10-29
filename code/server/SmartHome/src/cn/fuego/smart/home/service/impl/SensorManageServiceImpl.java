/**   
* @Title: SensorManageSerivceImpl.java 
* @Package cn.fuego.smart.home.service.impl 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-29 下午2:56:31 
* @version V1.0   
*/ 
package cn.fuego.smart.home.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.common.dao.datasource.AbstractDataSource;
import cn.fuego.common.dao.datasource.DataBaseSourceImpl;
import cn.fuego.common.dao.impl.AbstractDao;
import cn.fuego.smart.home.domain.Sensor;
import cn.fuego.smart.home.service.SensorManageService;

 /** 
 * @ClassName: SensorManageSerivceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-29 下午2:56:31 
 *  
 */
public class SensorManageServiceImpl implements SensorManageService
{

	private Log log = LogFactory.getLog(SensorManageServiceImpl.class);

	/* (non-Javadoc)
	 * @see cn.fuego.smart.home.service.SensorManageService#getSensorDataSource()
	 */
	@Override
	public AbstractDataSource<Sensor> getSensorDataSource()
	{
		AbstractDataSource<Sensor> datasource = new DataBaseSourceImpl<Sensor>(Sensor.class);
		
		return datasource;
	}

}
