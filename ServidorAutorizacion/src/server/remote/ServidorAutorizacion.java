package server.remote;

import java.rmi.Naming;
import java.util.ArrayList;

import server.data.Usuario;

public class ServidorAutorizacion implements IServidorAutorizacion{

	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	@Override
	public void registrar(String email, String contrasenya) {
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				System.out.println("Error -- ya existe un usuario con ese email");
			} else {
				Usuario user = new Usuario();
				user.setEmail(email);
				user.setContrasenya(contrasenya);
				usuarios.add(user);
				System.out.println("Usuario registrado correctamente");
			}
		}		
	}

	@Override
	public void login(String email, String contrasenya) {
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				if (u.getContrasenya().equals(contrasenya)) {
					System.out.println("Login correcto, ¡¡¡¡¡¡Bienvenido!!!!!!");
				} else {
					System.out.println("Contraseña incorrecta");
				}
			} else {
				System.out.println("No existe ningún usuario con ese email");
			}
		}
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
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
