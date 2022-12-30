package com.springboot.reactor.app.models;

public class Usuario {

	private String nombre;
	private String apellidos;

	public Usuario(String nombre, String apellidos)
	{
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellidos()
	{
		return this.apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	@Override
	public String toString()
	{
		return "Usuario [nombre=" + this.nombre + ", apellidos=" + this.apellidos + "]";
	}

}
