package com.musicshop.databases;

import com.musicshop.entities.*;
import com.musicshop.swing.View;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAO {
    DBUtil db;

    public DAO() {
        db= new DBUtil();
    }

    public String addAlbom(Albom albom) throws SQLException {
        try (Connection connection = db.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO public.albom (title_albom, genre) values (?,?)");
            preparedStatement.setString(1, albom.getTitleAlbom());
            preparedStatement.setString(2, albom.getGenre());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String addSinger(Singer singer) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO public.singer(name) " +
                            "values (?)");
            preparedStatement.setString(1, singer.getName());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String addSong(Song song) throws SQLException {
        try (Connection connection = db.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO public.song (title_song, duration, id_albom, id_singer) " +
                            "values (?, ?, ?, ?)");
            preparedStatement.setString(1, song.getTitleSong());
            preparedStatement.setInt(2, song.getDuration());
            preparedStatement.setInt(3, song.getIdAlbom());
            preparedStatement.setInt(4, song.getIdSinger());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String deleteSong(int id) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.song where id_song=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String deleteSinger(int id) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.singer where id_singer=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String deleteAlbom(int id) throws SQLException {
        try (Connection connection = db.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("delete from public.albom where id_albom=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String updateSinger(Singer singer) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update singer set name = ? where id_singer = ?");
            preparedStatement.setString(1, singer.getName());
            preparedStatement.setInt(2, singer.getIdSinger());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public String updateSong(Song song) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update song set title_song = ?,duration = ? " +
                            "where id_song = ?");
            preparedStatement.setString(1, song.getTitleSong());
            preparedStatement.setInt(2, song.getDuration());
            preparedStatement.setInt(3, song.getIdAlbom());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public ResultSet getColumns(String table) throws SQLException {
        try(Connection connection = db.getConnection())  {
            ResultSet resultSet = connection.getMetaData()
                    .getColumns(null, "public", table, "%");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<Song> taskTwo() throws SQLException {
        try (Connection connection = db.getConnection())  {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * from song " +
                            "where duration >  all( select duration from song s join albom a on a.id_albom = s.id_song " +
                            "where a.title_albom = 'Зачарованная моя')").executeQuery();
            LinkedList<Song> songs = new LinkedList<>();
            Song song = new Song();
            while (resultSet.next()){
                song.setIdSong(resultSet.getInt(1));
                song.setTitleSong(resultSet.getString(2));
                song.setDuration(resultSet.getInt(3));
                song.setIdSinger(resultSet.getInt(4));
                song.setIdAlbom(resultSet.getInt(5));
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getTables() throws SQLException {
        try (Connection connection = db.getConnection())  {
            ResultSet resultSet = connection.getMetaData().getTables(null,"public", "%", new String[]{"TABLE"});
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FindedMetaData getTablesAndColumns() throws SQLException {
        try (Connection connection = db.getConnection())  {
            System.out.println("Вывод таблиц и колонок");
            ResultSet rs = getTables();
            ResultSet resultSet;
            String table;
            FindedMetaData findedMetaData = new FindedMetaData();
            LinkedList<String> columns = new LinkedList<>();
            while (rs.next()){
                table = rs.getString(3);
                resultSet = getColumns(table);
                while (resultSet.next()){
                    columns.add(resultSet.getString(4));
                }
                findedMetaData.setColumns(table, columns);
            }
            return findedMetaData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateAlbom(Albom albom) throws SQLException {
        try (Connection connection = db.getConnection())  {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update public.albom set title_albom=?, genre=? where  id_albom = ?");
            preparedStatement.setString(1, albom.getTitleAlbom());
            preparedStatement.setString(2, albom.getGenre());
            preparedStatement.setInt(3, albom.getIdAlbom());
            preparedStatement.execute();
            return "Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public LinkedList<Albom> getAllAlboms() throws SQLException {
        LinkedList<Albom> alboms = new LinkedList<Albom>();
        try (Connection connection = db.getConnection())  {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from albom");
            while (rs.next()) {
                Albom albom = new Albom();
                albom.setIdAlbom(rs.getInt(1));
                albom.setTitleAlbom(rs.getString(2));
                albom.setGenre(rs.getString(3));
                alboms.add(albom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alboms;
    }

    public LinkedList<Singer> getAllSingers() throws SQLException {
        LinkedList<Singer> singers = new LinkedList<Singer>();
        try (Connection connection = db.getConnection())  {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from singer");
            while (rs.next()) {
                Singer singer = new Singer();
                singer.setIdSinger(rs.getInt(1));
                singer.setName(rs.getString(2));
                singers.add(singer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singers;
    }

    public LinkedList<Song> getAllSongs() throws SQLException {
        LinkedList<Song> songs = new LinkedList<>();
        try (Connection connection = db.getConnection())  {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from song");
            while (rs.next()) {
                Song song = new Song();
                song.setIdSong(rs.getInt(1));
                song.setTitleSong(rs.getString(2));
                song.setDuration(rs.getInt(3));
                song.setIdSinger(rs.getInt(4));
                song.setIdAlbom(rs.getInt(5));
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static void main(String[] arg) {
        try {
            DAO dao = new DAO();
            List<Song> songs = dao.getAllSongs();
            List<Singer> singers = dao.getAllSingers();
            List<Albom> alboms = dao.getAllAlboms();
            for (Albom albom : alboms) {
                System.out.println(albom);
            }
            for (Song song : songs) {
                System.out.println(song);
            }
            for (Singer singer : singers) {
                System.out.println(singer);
            }
            songs = dao.taskTwo();
            for (Song song : songs) {
                System.out.println(song);
            }
            View view = new View();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

