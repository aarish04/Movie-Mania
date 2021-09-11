package com.movie.mania.servlets;
import org.json.simple.JSONObject;
import com.movie.mania.dto.*;
import com.movie.mania.dao.*;
import com.movie.mania.exception.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class AddMovie extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
System.out.println(e);
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
String movieName=b.toString();
MovieDTO movieDTO=new MovieDTO();
movieDTO.setMovieName(movieName);
MovieDAO movieDAO=new MovieDAO();
try
{
movieDAO.add(movieDTO);
}catch(DAOException daoException)
{
PrintWriter pw=response.getWriter();
JSONObject obj=new JSONObject();
String exception=daoException.getMessage();
obj.put("response",exception);
response.setContentType("application/json");
pw.print(obj);
pw.flush();
}
PrintWriter pw=response.getWriter();
JSONObject obj=new JSONObject();
obj.put("response","Movie added.");
response.setContentType("application/json");
pw.print(obj);
pw.flush();
}catch(Exception exception)
{
System.out.println(exception);
}
}
}