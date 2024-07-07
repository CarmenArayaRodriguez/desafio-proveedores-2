package cl.praxis.model.dao;

import cl.praxis.model.Proveedor;
import cl.praxis.model.dto.ProveedorDTO;
import java.util.List;

public interface ProveedorDAO {
    boolean addProveedor(Proveedor proveedor);
    boolean updateProveedor(Proveedor proveedor);
    boolean deleteProveedor(int id);
    List<ProveedorDTO> getAllProveedoresDTO();
	Proveedor getProveedorById(int id);
}
