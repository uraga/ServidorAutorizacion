package server.remote;

import java.rmi.Remote;

public interface IServidorAutorizacion extends Remote{
	
	public void registrar(String email, String contrasenya);
	
	public void login(String email, String contrasenya);

}
