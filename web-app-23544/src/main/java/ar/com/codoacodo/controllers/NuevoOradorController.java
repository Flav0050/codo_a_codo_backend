package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador/nuevo")
public class NuevoOradorController extends HttpServlet{

	//crear > post
	protected void doGet(
				HttpServletRequest request, //aca viene lo que manda el usuario 
				HttpServletResponse response /*manda el backend al frontend*/
			) throws ServletException, IOException {
		
		//capturo los parametros enviados por el front
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String tema = request.getParameter("tema");
		
		//creo mi orador 
		Orador nuevo = new Orador(nombre, apellido, email, tema, LocalDate.now());
		
		//ahora por medio del repository se guarda en la base de datos
		OradorRepository repository = new MySqlOradorRepository();
		
		repository.save(nuevo);
		
		//ahora le respondo al front
		response.getWriter().print("OK");//json
	}

}
