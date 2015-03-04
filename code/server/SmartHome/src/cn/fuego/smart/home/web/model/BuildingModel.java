/**   
* @Title: BuildingModel.java 
* @Package cn.fuego.smart.home.web.model 
* @Description: TODO
* @author Tang Jun   
* @date 2015-3-4 上午10:27:31 
* @version V1.0   
*/ 
package cn.fuego.smart.home.web.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.smart.home.domain.Building;
import cn.fuego.smart.home.domain.SensorPlan;

 /** 
 * @ClassName: BuildingModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2015-3-4 上午10:27:31 
 *  
 */
public class BuildingModel
{
	private Building building;
	private List<SensorPlan> planList = new ArrayList<SensorPlan>();
	public Building getBuilding()
	{
		return building;
	}
	public void setBuilding(Building building)
	{
		this.building = building;
	}
	public List<SensorPlan> getPlanList()
	{
		return planList;
	}
	public void setPlanList(List<SensorPlan> planList)
	{
		this.planList = planList;
	}
	
	

}
