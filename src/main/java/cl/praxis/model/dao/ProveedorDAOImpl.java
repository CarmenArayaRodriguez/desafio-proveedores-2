package cl.praxis.model.dao;

import cl.praxis.model.Proveedor;
import cl.praxis.model.conexion.BDConexion;
import cl.praxis.model.dto.ProveedorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public boolean addProveedor(Proveedor proveedor) {
        String query = "INSERT INTO proveedores (nombre, rut, direccion, correo, telefono, contacto, telefono_contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = BDConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, proveedor.getNombre().toUpperCase().trim());
            pstmt.setString(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setString(4, proveedor.getCorreo());
            pstmt.setString(5, proveedor.getTelefono());
            pstmt.setString(6, proveedor.getContacto());
            pstmt.setString(7, proveedor.getTelefonoContacto());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProveedor(Proveedor proveedor) {
        String query = "UPDATE proveedores SET nombre = ?, rut = ?, direccion = ?, correo = ?, telefono = ?, contacto = ?, telefono_contacto = ? WHERE id = ?";
        try (Connection conn = BDConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, proveedor.getNombre().toUpperCase().trim());
            pstmt.setString(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setString(4, proveedor.getCorreo());
            pstmt.setString(5, proveedor.getTelefono());
            pstmt.setString(6, proveedor.getContacto());
            pstmt.setString(7, proveedor.getTelefonoContacto());
            pstmt.setInt(8, proveedor.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProveedor(int id) {
        String query = "DELETE FROM proveedores WHERE id = ?";
        try (Connection conn = BDConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProveedorDTO> getAllProveedoresDTO() {
        List<ProveedorDTO> proveedores = new ArrayList<>();
        String query = "SELECT id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto FROM proveedores ORDER BY nombre ASC";
        try (Connection conn = BDConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProveedorDTO proveedor = new ProveedorDTO();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setRut(rs.getString("rut"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setTelefonoContacto(rs.getString("telefono_contacto"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al recuperar proveedores: " + e.getMessage());
        }
        return proveedores;
    }


@Override
public Proveedor getProveedorById(int id) {
    Proveedor proveedor = null;
    String sql = "SELECT * FROM proveedores WHERE id = ?";
    try (Connection conn = BDConexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setRut(rs.getString("rut"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedor.setTelefonoContacto(rs.getString("telefono_contacto"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return proveedor;
}

}
