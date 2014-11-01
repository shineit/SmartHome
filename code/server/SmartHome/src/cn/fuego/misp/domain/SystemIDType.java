/**   
 * @Title: SystemIDType.java 
 * @Package cn.fuego.misp.domain 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-3-14 上午01:30:41 
 * @version V1.0   
 */
package cn.fuego.misp.domain;

import cn.fuego.common.domain.PersistenceObject;

/**
 * @ClassName: SystemIDType
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-3-14 上午01:30:41
 * 
 */

public class SystemIDType implements PersistenceObject
{
	private String name;
	private int step;
	private int length;
	private int incMode;
	private int currentID;
	private String prefix;
	private String suffix;

	public static String getNameAttr()
	{
		return "name";
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getIncMode()
	{
		return incMode;
	}

	public void setIncMode(int incMode)
	{
		this.incMode = incMode;
	}

	public int getCurrentID()
	{
		return currentID;
	}

	public void setCurrentID(int currentID)
	{
		this.currentID = currentID;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public String getSuffix()
	{
		return suffix;
	}

	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}

	@Override
	public String toString()
	{
		return "SystemIDType [name=" + name + ", step=" + step + ", length=" + length + ", incMode=" + incMode + ", currentID=" + currentID + ", prefix=" + prefix + ", suffix=" + suffix + "]";
	}

}
