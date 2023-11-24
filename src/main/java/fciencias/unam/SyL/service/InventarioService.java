package fciencias.unam.SyL.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fciencias.unam.SyL.entity.Inventario;
import fciencias.unam.SyL.repository.InventarioRepository;


import java.util.List;
import java.util.Optional;
@Service
public class InventarioService {
	@Autowired
    private InventarioRepository repository;

    public List<Inventario> getInventarios() {
        return repository.findAll();
    }

    public Inventario saveInventario(Inventario inventario) {
        return repository.save(inventario);
    }

    public Inventario create(Inventario inventario) {
        return Optional.of(repository.save(inventario)).orElse(null);
    }

    public List<Inventario> readAll() {
        return Optional.of(repository.findAll()).orElse(null);

    }
    public Inventario readByidIngrediente(Long id) {
        return Optional.of(repository.findById(id)).orElse(null).get();
    }
    public Inventario update(Inventario inventario) {
        Inventario toUpdate = repository.getReferenceById(inventario.getIdIngrediente());
        toUpdate.setNombre(inventario.getNombre());
        toUpdate.setTipoProducto(inventario.getTipoProducto());
        toUpdate.getPeriodo().setAdquisicion(inventario.getPeriodo().getAdquisicion());
        toUpdate.getPeriodo().setExpiracion(inventario.getPeriodo().getExpiracion());
        toUpdate.setCantidad(inventario.getCantidad());
        toUpdate.setMedida(inventario.getMedida());
        toUpdate.setPrecio(inventario.getPrecio());
        toUpdate.setDescripcion(inventario.getDescripcion());
        toUpdate.setComentarios(inventario.getComentarios());
        toUpdate.setProveedor(inventario.getProveedor());
        repository.save(toUpdate);
        return toUpdate;
    }
    
    public boolean deleteByIdIngrediente(Long id) {
        try{
            repository.deleteById(id);
            return true;
        }
        catch(IllegalArgumentException e){return false;}
    }


}
