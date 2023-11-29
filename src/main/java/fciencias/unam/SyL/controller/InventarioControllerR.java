@RestController
@RequestMapping("/inventario")
public class InventarioControllerR {

    @Autowired
    private InventarioService serviceInventario;

    @Autowired
    private TipoProductoService tipoProductoService;

    private final Logger logger = LogManager.getLogger(InventarioController.class);

    @PostMapping("/guardar")
    public ResponseEntity<String> save(@Valid @RequestBody Inventario inventario, BindingResult result) {
        if (result.hasErrors()) {
            logger.info("HAY ERRORES! ");
            logger.info(result.getAllErrors());
            return ResponseEntity.badRequest().body("Error de validación");
        }

        logger.info("*** SAVE Inventario - Controller");
        serviceInventario.create(inventario);
        return ResponseEntity.ok("Producto guardado exitosamente");
    }

    @PostMapping("/guardarEdicion")
    public ResponseEntity<String> saveEdit(@Valid @RequestBody Inventario inventario, BindingResult result) {
        if (result.hasErrors()) {
            logger.info("HAY ERRORES! ");
            logger.info(result.getAllErrors());
            return ResponseEntity.badRequest().body("Error de validación");
        } else {
            logger.info("*** UPDATE Inventario - Controller");
            serviceInventario.update(inventario);
            return ResponseEntity.ok("Producto actualizado exitosamente");
        }
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<Inventario> edit(@PathVariable Long id) {
        Inventario inventarioAEditar = serviceInventario.readByidIngrediente(id);

        if (inventarioAEditar != null) {
            return ResponseEntity.ok(inventarioAEditar);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        logger.info("*** DELETE Inventario - Controller");
        serviceInventario.deleteByIdIngrediente(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}
