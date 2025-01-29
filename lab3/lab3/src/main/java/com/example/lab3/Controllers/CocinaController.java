package com.example.lab3.Controllers;

import com.example.lab3.Entities.Receta;
import com.example.lab3.Repositories.ReceetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cocina")
public class CocinaController {

    @Autowired
    private ReceetaRepository receetaRepository;

    //Listados
    @GetMapping ("/recetas")
    public String recetas(Model model){
        List<Receta> listaRecetas = receetaRepository.findAll();
        model.addAttribute("listaRecetas", listaRecetas);
        return "recetasList";
    }

}
