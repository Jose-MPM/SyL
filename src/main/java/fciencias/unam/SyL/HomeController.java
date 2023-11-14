package fciencias.unam.SyL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fciencias.unam.SyL.entity.Inventario;
import fciencias.unam.SyL.entity.TipoProducto;
import fciencias.unam.SyL.service.InventarioService;
import fciencias.unam.SyL.service.TipoProductoService;
import jakarta.validation.Valid;
@Controller
public class HomeController {
	
    @Autowired
    private InventarioService service;
    
    @Autowired
    private TipoProductoService tipoProductoService;
    

    private final Logger logger = LogManager.getLogger(HomeController.class);
    
    @ModelAttribute
    public void init(Model model) {
    	Inventario inventario = new Inventario();
        model.addAttribute("inventario", inventario);
    	List<TipoProducto> listaDeTiposDeProducto = tipoProductoService.getTiposProducto();
        model.addAttribute("listaDeTiposDeProducto", listaDeTiposDeProducto);
    }

    @GetMapping("/AgregarProducto")
    public String agregarP(){
        return "agregarProducto";
    }
    @GetMapping("/inventario")
    public String inventarios(Model model) {
        model.addAttribute("inventario", service.getInventarios());
        return "inventario";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/tinga")
    public String tinga() {
        return "receta1";
    }

    @GetMapping("/lasana")
    public String lasana() {
        return "receta2";
    }

   @PostMapping("/guardar")
    public String save(@Valid @ModelAttribute Inventario inventario, BindingResult result) {
       if (result.hasErrors()) {
            logger.info("HAY ERRORES! ");
            logger.info(result.getAllErrors());
            return "agregarProducto";

        }
        
        logger.info("*** SAVE Inventario - Controller");
        logger.debug("*********** ATRIBUTOS RECIBIDOS: ");
        service.saveInventario(inventario);
    //    InventarioRepository.flush();

    //    this.mailSendr.sendSimpleMessage("ingrediente agregado");
       return "redirect:/inventario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Inventario inventarioAEditar = service.getInventarioById(id);
        
        if (inventarioAEditar != null) {
            model.addAttribute("inventario", inventarioAEditar);
            List<TipoProducto> listaDeTiposDeProducto = tipoProductoService.getTiposProducto();
            model.addAttribute("listaDeTiposDeProducto", listaDeTiposDeProducto);
            model.addAttribute("tipoProducto", inventarioAEditar.getTipoProducto());
            return "editarProducto"; 
        } else {
            return "redirect:/inventario"; // redirige a una página de error
        }
    }


    //@PostMapping("/guardarEdicion")
    //public String saveEdit(@Valid @ModelAttribute Inventario inventario, BindingResult result) {
    //}

    //@GetMapping("/eliminar/{id}")
    //public String delete(@PathVariable Long id) {
    //}

    
    // @GetMapping("/restStatic")
    // public String restStatic(Model model) {
    //     return "restStatic";
    // }
}
