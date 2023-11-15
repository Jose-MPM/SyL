package fciencias.unam.SyL;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fciencias.unam.SyL.entity.DateRange;
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
    	inventario.setPeriodo(new DateRange());
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
    	// esto podría generar un error profe?
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
        logger.debug("*********** ATRIBUTOS RECIBIDOS: ");
        logger.debug(inventario.getNombre());
        logger.debug(inventario.getPeriodo().getAdquisicion());
        logger.debug(inventario.getPeriodo().getExpiracion());
        logger.info("*** SAVE Inventario - Controller");
        service.saveInventario(inventario);
       return "redirect:/inventario";
    }
    
    // @GetMapping("/restStatic")
    // public String restStatic(Model model) {
    //     return "restStatic";
    // }
}
