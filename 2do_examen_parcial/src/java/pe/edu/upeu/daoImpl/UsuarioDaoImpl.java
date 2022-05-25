package pe.edu.upeu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.config.Conexion;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cx;
    private List<Usuario> Usuario;

    @Override
    public int create(Usuario usuario) {
        String SQL = "INSERT INTO usuario(username, password, fecha, estado, idpersona, idrol) VALUES(?,?,?,?,?,?)";
        int x = 0;
        try {
            cx = Conexion.getConex();
            ps = cx.prepareStatement(SQL);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getFecha());
            ps.setString(4, usuario.getEstado());
            ps.setInt(5, usuario.getIdpersona());
            ps.setInt(6, usuario.getIdrol());
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error:"+e);
        }
        return x;
    }

    @Override
    public int update(Usuario usuario) {
        String SQL = "UPDATE usuario SET username=?, password=?, fecha=?, estado=? WHERE idusuario=?";
        int x = 0;
        try {
            cx = Conexion.getConex();
            ps = cx.prepareStatement(SQL);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getFecha());
            ps.setString(4, usuario.getEstado());
            ps.setInt(5, usuario.getIdusuario());
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error:"+e);
        }
        return x;
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM usuario WHERE idusuario=?";
        int x = 0;
        try {
            cx = Conexion.getConex();
            ps = cx.prepareStatement(SQL);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error:"+e);
        }
        return x;  
    }

    @Override
    public Usuario read(int id) {
        String SQL ="SELECT *FROM usuario WHERE idusuario=?";
        Usuario usuario = new Usuario();
        try {
            cx = Conexion.getConex();
            ps = cx.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setUsername(rs.getString("Username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdpersona(rs.getInt("idpersona"));
                usuario.setIdrol(rs.getInt("rol"));
                usuario.setFecha(rs.getString("fecha"));
                usuario.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            System.out.println("Error:"+e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> readAll() {
         String SQL ="SELECT *FROM USUARIO";
        List<Usuario> lista = new ArrayList<>();
        try {
            cx = Conexion.getConex();
            ps = cx.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setIdpersona(rs.getInt("idpersona"));
                usuario.setIdrol(rs.getInt("idrol"));
                usuario.setFecha(rs.getString("fecha"));
                usuario.setEstado(rs.getString("estado"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error:"+e);
        }
        System.out.println("usuario");
        return lista;
    }
}
