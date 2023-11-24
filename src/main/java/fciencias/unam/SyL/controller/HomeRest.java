package fciencias.unam.SyL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import fciencias.unam.SyL.entity.Inventario;
import fciencias.unam.SyL.entity.TipoProducto;
import fciencias.unam.SyL.service.InventarioService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController("/res")
public class HomeRest {
	@Autowired
    private InventarioService serviceInventario;

    @GetMapping("/res")
    public List<Inventario> edit() {
        return serviceInventario.readAll();
    }

}
