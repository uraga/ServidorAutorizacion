package server.utils;

import server.data.Usuario;
import server.dto.UsuarioDTO;

public class Assembler {
	
	private static Assembler instance; 
	
	private Assembler() { }
	
	public static Assembler getInstance() {
		if (instance == null) {
			instance = new Assembler();
		}
		return instance;
	}
	
	public UsuarioDTO assemble(Usuario u) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setContrasenya(u.getContrasenya());
		dto.setEmail(u.getEmail());
		
		System.out.println("User " + u.getEmail() + " correctly assembled");
		return dto;
	}
	
	public Usuario disassemble(UsuarioDTO udto) {
		Usuario u = new Usuario();
		
		u.setContrasenya(udto.getContrasenya());
		u.setEmail(udto.getEmail());
		
		System.out.println("User " +  udto.getEmail() + " correctly disassembled");
		return u;
	}
	

}
