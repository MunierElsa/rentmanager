package com.epf.rentmanager.ui.servlets.user;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/editUsers")
public class EditUsersServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	Client useredit = new Client();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listUsers",this.clientService.findAll());
			request.getRequestDispatcher("./WEB-INF/views/users/edit.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
			
				
	}
	
	protected void doGet2(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listUsers",this.clientService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/users/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String naissance = request.getParameter("naissance");
		
		LocalDate date_naissance = LocalDate.parse(naissance);
		
		useredit.setId(Integer.parseInt(id));
		useredit.setNom(nom);
		useredit.setPrenom(prenom);
		useredit.setEmail(email);
		useredit.setNaissance(date_naissance);
			
		try {
			request.setAttribute("EditUsers",this.clientService.edit(useredit));	
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} 
		doGet2(request,response);
			
	}
}
