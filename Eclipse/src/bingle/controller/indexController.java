/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingle.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bingle.util.Article;
import bingle.util.SearchEngine;

import com.oracle.net.Sdp;


/**
 *
 * @author Fate
 */
@WebServlet(name = "indexController", urlPatterns = {"/indexController"})
public class indexController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6803419603489979751L;
	public indexController()
	{
		super();
	}
	/** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String act = request.getParameter("act");
        String url = "index.jsp";
        int nResult = 10;
        int page = 1;
        if (act != null && "init".equals(act)) {
            url = "index.jsp";
        } else if (act != null && "search".equals(act)) {
            String keyWord = request.getParameter("key");
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<Article> articles = SearchEngine.Search(keyWord, page, nResult);

            request.setAttribute("key", keyWord);
            request.setAttribute("articles", articles);

            url = "index.jsp";
        }


        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
