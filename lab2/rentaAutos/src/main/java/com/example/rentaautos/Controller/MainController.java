package com.example.rentaautos.Controller;

import com.example.rentaautos.Entity.Auto;
import com.example.rentaautos.Entity.Sede;
import com.example.rentaautos.Entity.Seguro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    // Lista para almacenar los autos en memoria
    private List<Auto> listaAutos = new ArrayList<>();
    private int currentIdAutos = 1; // Variable para generar IDs autoincrementales

    //Lista para almacenar las sedes en memoria
    private List<Sede> listaSedes = new ArrayList<>();
    private int currentIdSedes = 1; // Variable para generar IDs autoincrementales

    //Lista para almacenar los seguros en memoria
    private List<Seguro> listaSeguros = new ArrayList<>();
    private int currentIdSeguros = 1; // Variable para generar IDs autoincrementales



    //Inicio listados
    @GetMapping("/listaAutos")
    public String listarAutos(Model model){
        model.addAttribute("autos", listaAutos);
        return "listaAutos";
    }

    @GetMapping("/listaSedes")
    public String listarSedes(Model model){
        model.addAttribute("sedes", listaSedes);
        return "listaSedes";
    }

    @GetMapping("/listaSeguros")
    public String listarSeguros(Model model){
        model.addAttribute("seguros", listaSeguros);
        return "listaSeguros";
    }
    //Fin listados

    //Inicio Página de agregar
    @GetMapping("/addCar")
    public String addCar(Model model){
        model.addAttribute("sedes",listaSedes);
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
    //Fin página de agregar

    //Inicio de guardado
    @PostMapping("/saveCar")
    public String saveCar(Auto auto, Model model){
        auto.setId(currentIdAutos++);
        listaAutos.add(auto);
        return "redirect:/listaAutos";
    }

    @PostMapping("/saveSede")
    public String saveSede(Sede sede, Model model){
        sede.setId(currentIdSedes++);
        listaSedes.add(sede);
        return "redirect:/listaSedes";
    }

    @PostMapping("/saveSeguro")
    public String saveSeguro(Seguro seguro, Model model){
        seguro.setId(currentIdSeguros++);
        listaSeguros.add(seguro);
        return "redirect:/listaSeguros";
    }
    //Fin de guardado

    //Inicio de edición
    @GetMapping("/editAuto")
    public String editAuto(Model model, @RequestParam int id){
        Auto autoEncontrado = null;
        for (Auto auto : listaAutos) {
            if (auto.getId() == id) {
                autoEncontrado = auto;
                break; // Detiene el bucle al encontrar el auto
            }
        }
        model.addAttribute("sedes", listaSedes);
        model.addAttribute("auto", autoEncontrado);
        return "editCarForm";
    }
    @PostMapping("/saveChangesCar")
    public String saveChangesCar(Auto auto, Model model) {
        // Buscar el auto en la lista por su ID
        Auto autoExistente = listaAutos.stream()
                .filter(a -> a.getId() == auto.getId())
                .findFirst()
                .orElse(null);

        if (autoExistente != null) {
            // Si el auto existe, actualizamos los datos
            autoExistente.setModelo(auto.getModelo());
            autoExistente.setColor(auto.getColor());
            autoExistente.setKilometraje(auto.getKilometraje());
            autoExistente.setSede(auto.getSede());
            autoExistente.setCostoDia(auto.getCostoDia());
        }

        return "redirect:/listaAutos";
    }

    @GetMapping("/editSede")
    public String editSede(Model model, @RequestParam int id){
        Sede sedeEncontrada = null;
        for (Sede sede : listaSedes) {
            if (sede.getId() == id) {
                sedeEncontrada = sede;
                break; // Detiene el bucle al encontrar el auto
            }
        }
        model.addAttribute("sede", sedeEncontrada);
        return "editSedeForm";
    }
    @PostMapping("/saveChangesSede")
    public String saveChangesSede(Sede sede, Model model) {
        // Buscar el auto en la lista por su ID
        Sede sedeExistente = listaSedes.stream()
                .filter(s -> s.getId() == sede.getId())
                .findFirst()
                .orElse(null);

        if (sedeExistente != null) {
            // Si el auto existe, actualizamos los datos
            sedeExistente.setDireccion(sede.getDireccion());
            sedeExistente.setDistrito(sede.getDistrito());
        }

        return "redirect:/listaSedes";
    }

    @GetMapping("/editSeguro")
    public String editSeguro(Model model, @RequestParam int id){
        Seguro seguroEncontrado = null;
        for (Seguro seguro : listaSeguros) {
            if (seguro.getId() == id) {
                seguroEncontrado = seguro;
                break; // Detiene el bucle al encontrar el auto
            }
        }
        model.addAttribute("seguro", seguroEncontrado);
        return "editSeguroForm";
    }
    @PostMapping("/saveChangesSeguro")
    public String saveChangesSeguro(Seguro seguro, Model model) {
        Seguro seguroExistente = listaSeguros.stream()
                .filter(s -> s.getId() == seguro.getId())
                .findFirst()
                .orElse(null);

        if (seguroExistente != null) {
            // Si el auto existe, actualizamos los datos
            seguroExistente.setCoberturaMax(seguro.getCoberturaMax());
            seguroExistente.setEmpresaAseguradora(seguro.getEmpresaAseguradora());
            seguroExistente.setTarifa(seguro.getTarifa());
        }

        return "redirect:/listaSeguros";
    }


    //Fin de edición

    //Inicio eliminación
    @PostMapping("/deleteAuto")
    public String deleteCar(@RequestParam int id, Model model) {
        listaAutos.removeIf(auto -> auto.getId().equals(id)); // Elimina el auto por su ID
        return "redirect:/listaAutos";
    }

    @PostMapping("/deleteSede")
    public String deleteSede(@RequestParam int id, Model model) {
        listaSedes.removeIf(sede -> sede.getId().equals(id)); // Elimina la sede por su ID
        return "redirect:/listaSedes";
    }

    @PostMapping("/deleteSeguro")
    public String deleteSeguro(@RequestParam int id, Model model) {
        listaSeguros.removeIf(seguro -> seguro.getId().equals(id)); // Elimina el auto por su ID
        return "redirect:/listaSeguros";
    }

    //Fin eliminación

}
