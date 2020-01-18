package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.data.Usuario;

public class ServidorAutorizacion extends UnicastRemoteObject implements IServidorAutorizacion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> usuarios;

	protected ServidorAutorizacion() throws RemoteException {
		super();
		usuarios = new ArrayList<Usuario>();
		Usuario u1 = new Usuario();
		u1.setEmail("j.uraga@opendeusto.es"); u1.setContrasenya("12345");
		Usuario u2 = new Usuario();
		u2.setEmail("gorka.garate@opendeusto.es"); u2.setContrasenya("abcd");
		Usuario u3 = new Usuario();
		u3.setEmail("ibai.guillen@opendeusto.es"); u3.setContrasenya("12345");
		Usuario u4 = new Usuario();
		u4.setEmail("yeray.bellanco@opendeusto.es"); u4.setContrasenya("ab");
		
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		usuarios.add(u4);
	}
	
	@Override
	public void registrar(String email, String contrasenya) {
		System.out.println("..");
		System.out.println("..Mail: " + email);
		System.out.println("..Pass: " + contrasenya);
		boolean existe = false;
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				existe = true;
				System.out.println("Error -- ya existe un usuario con ese email");
			}
		}
		if (!existe) {
			Usuario u = new Usuario();
			u.setEmail(email);
			u.setContrasenya(contrasenya);
			usuarios.add(u);
			System.out.println("Usuario registrado correctamente");
		}
		
	}

	@Override
	public boolean login(String email, String contrasenya) {
		System.out.println("..");
		System.out.println("..Mail: " + email);
		System.out.println("..Pass: " + contrasenya);
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				if (u.getContrasenya().equals(contrasenya)) {
					System.out.println("Login correcto, ¡¡¡¡¡¡Bienvenido!!!!!!");
					return true;
				} else {
					System.out.println("Contraseña incorrecta");
					return false;
				}
			} 
		}
		System.out.println("No existe ningún usuario con ese email");
		return false;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		
		try {
			IServidorAutorizacion server = new ServidorAutorizacion();
			Naming.rebind(name, server);
			System.out.println("* Server '" + name + "' active and waiting...");
			
			server.registrar("prueba@prueba.es", "prueba"); //Se registra bien
			server.login("prueba@prueba.es", "prueba"); // ¿¿Login correcto??
			server.registrar("j.uraga@opendeusto.es", "12345"); //Ya existe
			server.login("j.uraga@opendeusto.es", "12345"); //Login correcto
			server.login("j.uraga@opendeusto.com", "12345"); //Email no existe 
			server.login("j.uraga@opendeusto.es", "abcd"); //Contraseña incorrecta
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
