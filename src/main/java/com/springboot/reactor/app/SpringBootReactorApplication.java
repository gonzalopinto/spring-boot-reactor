package com.springboot.reactor.app;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.reactor.app.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		List<String> listaNombres = Arrays.asList("Gon Pin", "Ana Duque", "Laurin Pajuelo", "Leah Montejo",
				"Oona Montejo", "Bruce Lee", "Bruce Willis");

		Flux<String> nombres = Flux.fromIterable(listaNombres);
		// Flux.just("Gon Pin", "Ana Duque", "Laurin Pajuelo", "Leah Montejo", "Oona Montejo","Bruce Lee", "Bruce
		// Willis");

		Flux<Usuario> usuarios = nombres
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> usuario.getNombre().toLowerCase().equals("bruce")).doOnNext(usuario ->
				{
					if (usuario == null)
					{
						throw new RuntimeException("Nombres no puede ser vacío");
					}
					System.out.println(usuario.getNombre() + " " + usuario.getApellidos());
				}).map(usuario ->
				{
					String n = usuario.getNombre().toLowerCase();
					usuario.setNombre(n);
					return usuario;
				});

		usuarios.subscribe(u -> log.info(u.toString()), error -> log.error(error.getMessage()), new Runnable()
		{
			@Override
			public void run()
			{
				log.info("Ha finalizado la ejecución del Observable con éxito");
			}
		});
	}

}
