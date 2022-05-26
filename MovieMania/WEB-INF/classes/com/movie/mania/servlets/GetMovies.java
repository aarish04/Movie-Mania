package com.movie.mania.servlets;
import org.json.simple.JSONObject;
import com.movie.mania.dto.*;
import com.movie.mania.dao.*;
import com.movie.mania.exception.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetMovies extends HttpServlet
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
try
{
PrintWriter pw=response.getWriter();
MovieDAO movieDAO=new MovieDAO();
List<MovieDTO> movies=movieDAO.getAll();
response.setContentType("application/json");
Gson gson=new Gson();
String json=gson.toJson(movies);
pw.print(json);
pw.flush();
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
}catch(Exception exception)
{
System.out.println(exception);
}
}
}