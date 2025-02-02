package com.example.lab3.Controllers;

import com.example.lab3.Entities.Categoria;
import com.example.lab3.Entities.Receta;
import com.example.lab3.Repositories.CategoriaRepository;
import com.example.lab3.Repositories.ReceetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Listado de recetas
    @GetMapping ("/recetas")
    public String recetas(Model model){
        List<Receta> listaRecetas = recetaRepository.findAll();
        model.addAttribute("listaRecetas", listaRecetas);
        return "recetasList";
    }

    @GetMapping ("/nuevaReceta")
    public String nuevaReceta(@ModelAttribute("receta") Receta receta,
                              Model model){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("esEdicion", false);
        return "formularioRecetas";
    }

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

}
