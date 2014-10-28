package cn.fuego.misp.web.model.user;
/**
 * 
* @ClassName: PasswordModel 
* @Description: 修改密码
* @author Aether
* @date 2014-10-22 上午12:14:05 
*
 */
public class PasswordModel
{
	private String oldPassword;
	private String newPassword;
	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getNewPassword()
	{
		return newPassword;
	}
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
	
}
