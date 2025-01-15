package com.example.rentaautos.Controller;

import com.example.rentaautos.Entity.Auto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    // Lista para almacenar los autos en memoria
    private List<Auto> listaAutos = new ArrayList<>();

    @GetMapping("/listaAutos")
    public String listarAutos(Model model){
        model.addAttribute("autos", listaAutos);
        return "listaAutos";
    }

    @GetMapping("/listaSedes")
    public String listarSedes(){
        return "listaSedes";
    }

    @GetMapping("/listaSeguros")
    public String listarSeguros(){
        return "listaSeguros";
    }

    @GetMapping("/addCar")
    public String addCar(){
        return "addCarform";
    }

    @GetMapping("/addSede")
    public String addSede(){
        return "addSedeForm";
    }

    @GetMapping("/addSeguro")
    public String addSeguro(){
        return "addSeguroForm";
    }

    @PostMapping("/saveCar")
    public String saveCar(Auto auto, Model model){

        listaAutos.add(auto);
        model.addAttribute("autos", listaAutos);
        System.out.println(listaAutos.size());
        return "listaAutos";
    }

}
