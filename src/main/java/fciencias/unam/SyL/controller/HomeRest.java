package fciencias.unam.SyL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fciencias.unam.SyL.entity.Inventario;
import fciencias.unam.SyL.entity.TipoProducto;
import fciencias.unam.SyL.service.InventarioService;
import fciencias.unam.SyL.service.InventaryScrapperService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/res")
// @RestController("/res")
public class HomeRest {
	// @Autowired
    // private InventarioService serviceInventario;

    @Autowired
    private InventaryScrapperService inventaryScrapperService;

    private final Logger logger = LogManager.getLogger(HomeRest.class);


    // @GetMapping("/res")
    // public List<Inventario> edit() {
    //     return serviceInventario.readAll();
    // }
    
    // @RequestMapping(value="/example", method=GET)
    @GetMapping("/res/ex")
    // @ResponseBody
    public String example(Model model) {
        model.addAttribute("inventaryScrapped",inventaryScrapperService.getScrapping());
        // logger.info(inventaryScrapperService.getExample());
        // return inventaryScrapperService.getScrapping();
        return "inventario/scrappedResult";
        // System.out.println(inventaryScrapperService.getExample());
        // return "inventario/";
    }

}
