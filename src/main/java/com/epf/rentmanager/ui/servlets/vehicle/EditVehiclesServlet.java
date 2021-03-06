package com.epf.rentmanager.ui.servlets.vehicle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ContrainteException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/editVehicles")
public class EditVehiclesServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	Vehicle vehiculeedit = new Vehicle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listVehicles",this.vehicleService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/vehicles/edit.jsp").forward(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
	}
	
	protected void doGet2(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			try {
				request.setAttribute("listVehicles",this.vehicleService.findAll());
				request.getRequestDispatcher("./WEB-INF/views/vehicles/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		
			String id = request.getParameter("id");
			String constructeur = request.getParameter("manufacturer");
			String nb_places = request.getParameter("seats");
			
			vehiculeedit.setConstructeur(constructeur);
			vehiculeedit.setNb_places(Short.valueOf(nb_places));
			vehiculeedit.setId(Integer.valueOf(id));
			
			try {
				request.setAttribute("EditVehicles",this.vehicleService.edit(vehiculeedit));	
			} catch (ServiceException e1) {
				e1.printStackTrace();
			} catch (ContrainteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet2(request,response);
			
	}
	

}
