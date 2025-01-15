package com.example.rentaautos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autos")
public class MainController {

    @GetMapping("/listaAutos")
    public String listarAutos(){
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

}
