package com.tia102g3.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/back-end/emp/member/member2")
public class Hello extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
	    req.setCharacterEncoding("Big5");
        res.setContentType("text/html; charset=Big5");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        
        	System.out.println(name==null);                           //XX
        	System.out.println("--------------------------");
        	if(name!=null) {
        		System.out.println(name.trim().length()==0); //VV
        		System.out.println(name.trim().isEmpty());       //VV
        		System.out.println(name.trim().equals(""));      //VV
        	}
        	
        	
        out.println("<HTML>");
        out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("Hello, �A�n: " + name);
        out.println("</BODY></HTML>");
        
        System.out.println(req.getRequestURI()); // p109(p500) ---> ��k8(method 8) ---> (/SL314/Hello)
  }
  
  public String getServletInfo() {
    return "A servlet that knows the name of the person to whom it's" + 
           "saying hello";
  }
}
