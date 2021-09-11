package com.movie.mania.dao;
import java.sql.*;
import java.util.*;
import com.movie.mania.dto.*;
import com.movie.mania.exception.*;
public class MovieDAO
{
public void add(MovieDTO movie) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from Movies where movie_name=?");
preparedStatement.setString(1,movie.getMovieName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Movie : "+movie.getMovieName()+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into Movies (movie_name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,movie.getMovieName());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int movieId=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
movie.setMovieId(movieId);
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public List<MovieDTO> getAll() throws DAOException
{
List<MovieDTO> movies;
movies=new ArrayList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from Movies order by movie_name");
MovieDTO movieDTO;
int movieId;
String movieName;
while(resultSet.next())
{
movieId=resultSet.getInt("movie_id");
movieName=resultSet.getString("movie_name").trim();
movieDTO=new MovieDTO();
movieDTO.setMovieId(movieId);
movieDTO.setMovieName(movieName);
movies.add(movieDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return movies; 
}
}