package cn.fuego.smart.home.ui.base;

public abstract class HttpThread implements Runnable
{
	public void startHttp()
	{
		new Thread(this).start();
	}

}
