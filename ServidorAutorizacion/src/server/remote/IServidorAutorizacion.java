package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorAutorizacion extends Remote{
	
	public void registrar(String email, String contrasenya) throws RemoteException;
	
	public void login(String email, String contrasenya) throws RemoteException;

}
