package com.example.lab3.Controllers;

import com.example.lab3.Entities.*;
import com.example.lab3.Repositories.*;
import com.sun.jdi.IntegerValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cocina")
public class CocinaController {

    @Autowired
    private ReceetaRepository recetaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private RecetaIngredienteRepository recetaIngredienteRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private NutricionRepository nutricionRepository;

    //Listado de recetas
    @GetMapping ("/recetas")
    public String recetas(Model model){
        List<Receta> listaRecetas = recetaRepository.findAll();
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaRecetas", listaRecetas);
        model.addAttribute("listaCategorias", listaCategorias);
        return "recetasList";
    }

    //Formulario para nueva receta
    @GetMapping ("/nuevaReceta")
    public String nuevaReceta(@ModelAttribute("receta") Receta receta,
                              Model model){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("esEdicion", false);
        return "formularioRecetas";
    }

    //Formulario para editar receta
    @GetMapping ("/editarReceta")
    public String editarReceta(@ModelAttribute("receta") Receta receta,
                               Model model, @RequestParam("id") int id){

        Optional<Receta> recetaOptional = recetaRepository.findById(id);
        if(recetaOptional.isPresent()){
            receta = recetaOptional.get();
            model.addAttribute("receta", receta);
            model.addAttribute("esEdicion", true);
            model.addAttribute("listaCategorias", categoriaRepository.findAll());
            return "formularioRecetas";
        }
        return "redirect:/cocina/recetas";
    }

    //Tener en cuenta que la vista que se utiliza para las nuevas recetas y para editarlas es el mismo, debido a eso se utiliza ModelAttribute

    @PostMapping ("/saveReceta")
    public String guardarReceta(@ModelAttribute("receta") Receta receta,
                                RedirectAttributes redirectAttributes){
        if (receta.getId() == null){
            redirectAttributes.addFlashAttribute("mensaje", "Receta agregada exitosamente");
        }else {
            redirectAttributes.addFlashAttribute("mensaje", "Receta actualizada exitosamente");
        }
        recetaRepository.save(receta);
        return "redirect:/cocina/recetas";

    }

    @PostMapping("/deleteReceta")
    public String borrarReceta(@RequestParam("id") int id,
                               RedirectAttributes redirectAttributes){

        recetaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje","Receta borrada exitosamente");
        return "redirect:/cocina/recetas";

    }

    //Vista para detalles de receta
    @GetMapping("/detallesReceta")
    public String detallesReceta(@RequestParam ("id") int id,
                                 Model model){
        //Necesitamos el id de la receta que se mostrará
        Optional<Receta> recetaOptional = recetaRepository.findById(id);
        if(recetaOptional.isPresent()){
            model.addAttribute("receta", recetaOptional.get());
        }
        //Enviamos el objeto receta correspondiente
        return "detallesReceta";
    }

    //Vista para añadir ingredientes de determinada receta
    @GetMapping("/addIngredientes")
    public String ingresarIngredientes(@RequestParam ("id") int id,
                                       Model model){
        Optional<Receta> receta = recetaRepository.findById(id);

        model.addAttribute("receta",receta.get());

        List<Ingrediente> listaIngredientes = ingredienteRepository.findAll();
        model.addAttribute("listaIngredientes", listaIngredientes);
        List<UnidadMedida> listaUnidadMedidas = unidadMedidaRepository.findAll();
        model.addAttribute("listaUnidadMedidas", listaUnidadMedidas);

        model.addAttribute("idReceta", id);



        return "agregarIngredientes";
    }

    @PostMapping("/agregarIngrediente")
    public String guardarIngrediente(@RequestParam("nombre") String nombreIngrediente,
                                     @RequestParam("unidad") String unidad,
                                     @RequestParam("cantidad") String cantidadIngrediente,
                                     @RequestParam("idReceta") String idReeceta){

        Optional<Receta> receta = recetaRepository.findById(Integer.valueOf(idReeceta));
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(Integer.valueOf(nombreIngrediente));
        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findById(Integer.valueOf(unidad));

        if(receta.isPresent() && ingrediente.isPresent()){
            RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

            recetaIngrediente.setIdreceta(receta.get());
            recetaIngrediente.setIdingrediente(ingrediente.get());
            recetaIngrediente.setIdunidadMedida(unidadMedida.get());
            recetaIngrediente.setCantidad(Double.valueOf(cantidadIngrediente));

            recetaIngredienteRepository.save(recetaIngrediente);

        }

        return "redirect:/cocina/detallesReceta?id="+idReeceta;
    }

    //Formulario para editar datos de nutrición
    @GetMapping("/datosNutricion")
    public String editarDatosNutricion(@ModelAttribute("nutricion") Nutricion nutricion,
                                       @RequestParam("idReceta") int idReceta,
                                       Model model) {
        Optional<Receta> receta = recetaRepository.findById(idReceta);
        Optional<Nutricion> nutricionOptional = nutricionRepository.findByReceta(receta.get());
        model.addAttribute("receta", receta.get());
        if (nutricionOptional.isPresent()) {
            nutricion = nutricionOptional.get();
            model.addAttribute("nutricion", nutricion);
            model.addAttribute("esEdicion", true);
        } else {
            nutricion.setId(null); // Asegurar que no tenga ID para evitar confusión con edición
            nutricion.setReceta(receta.get());
            model.addAttribute("nutricion", nutricion);
            model.addAttribute("esEdicion", false);
        }
        return "formularioDatosNutricion";
    }

    //Guardar datos de nutrición
    @PostMapping ("/saveNutricion")
    public String guardarNutricion(@ModelAttribute("nutricion") Nutricion nutricion, @RequestParam("idReceta") int idReceta,
                                RedirectAttributes redirectAttributes){
        if (nutricion.getId() == null){
            redirectAttributes.addFlashAttribute("mensaje", "Datos de nutrición agregados exitosamente");
        }else {
            redirectAttributes.addFlashAttribute("mensaje", "Datos de nutrición actualizados exitosamente");
        }
        Optional<Receta> recetaAsociada = recetaRepository.findById(idReceta);
        nutricion.setReceta(recetaAsociada.get());
        nutricionRepository.save(nutricion);
        return "redirect:/cocina/detallesReceta?id=" + idReceta ;

    }

    //Filtros xd
    @GetMapping("/filtrado")
    public String searchRecipes(
            @RequestParam(name = "nombreReceta", required = false) String nombre,
            @RequestParam(name = "categoriaReceta", required = false) String categoria,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(name = "sortBy", required = false, defaultValue = "dificultad") String sortBy,
            Model model) {

        List<Receta> listaRecetas;

        // Verificar si el usuario quiere ordenar por cantidad de ingredientes
        if (sortBy.equalsIgnoreCase("ingredientes")) {
            listaRecetas = sortOrder.equalsIgnoreCase("desc")
                    ? recetaRepository.findAllOrderByCantidadIngredientesDesc()
                    : recetaRepository.findAllOrderByCantidadIngredientesAsc();
        } else {
            Sort sort = sortOrder.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

            if (nombre != null && !nombre.isEmpty() && categoria != null && !categoria.isEmpty()) {
                listaRecetas = recetaRepository.findByNombreContainingIgnoreCaseAndIdcategoria_Id(nombre, Integer.valueOf(categoria), sort);
            } else if (nombre != null && !nombre.isEmpty()) {
                listaRecetas = recetaRepository.findByNombreContainingIgnoreCase(nombre, sort);
            } else if (categoria != null && !categoria.isEmpty()) {
                listaRecetas = recetaRepository.findByIdcategoria_Id(Integer.valueOf(categoria), sort);
            } else {
                listaRecetas = recetaRepository.findAll(sort);
            }
        }

        model.addAttribute("listaRecetas", listaRecetas);
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "recetasList"; // Nombre de la vista Thymeleaf
    }

}
