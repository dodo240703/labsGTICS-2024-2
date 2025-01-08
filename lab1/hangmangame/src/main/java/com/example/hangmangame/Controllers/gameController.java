package com.example.hangmangame.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hangmanGame")
public class gameController {

    public static List<String> getWordsByTopic(String topic) {
        // Ejemplo de palabras por tema
        switch (topic.toLowerCase()) {
            case "animales":
                return List.of("leon", "elefante", "tigre", "cebra", "jirafa", "delfin", "ballena", "gorila", "panda",
                        "aguila", "hipopotamo", "koala", "lobo", "oso", "canguro");
            case "frutas":
                return List.of("manzana", "platano", "kiwi", "mango", "pera", "uva", "fresa", "naranja", "piña", "sandía",
                        "cereza", "melon", "papaya", "limon", "higo");
            case "países":
                return List.of("Mexico", "Canada", "Brasil", "España", "Francia", "Italia", "Alemania", "Japon",
                        "Australia", "Argentina", "Chile", "Peru", "Estados Unidos", "China", "India");
            default:
                return new ArrayList<>(); // Si el tema no existe, devuelve una lista vacía
        }
    }

    private List<String> topics = List.of("Animales", "Frutas", "Países");

    @GetMapping("/configuration")
    public String configuration(Model model) {
        model.addAttribute("topics", topics);

        return "configurationGame";
    }

    @PostMapping("/endConfiguration")
    public String endConfiguration(@RequestParam(name = "large") Integer large,
                                   @RequestParam(name = "attempts") Integer attempts,
                                   @RequestParam(name = "topic") String topic,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        // 1. Obtener la lista de palabras por tema
        List<String> words = getWordsByTopic(topic);

        // 2. Filtrar palabras por longitud
        List<String> filteredWords = words.stream().filter(word -> word.length() == large).toList();

        if (filteredWords.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No hay palabras disponibles para el tema y longitud seleccionados.");
            return "redirect:/hangmanGame/configuration";
        }

        // 3. Seleccionar una palabra al azar
        String secretWord = filteredWords.get((int) (Math.random() * filteredWords.size()));

        // 4. Preparar los espacios para las letras
        char[] spaces = new char[large];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = '_';
        }

        // 5. Guardar datos en el modelo para la vista
        model.addAttribute("spaces", new String(spaces)); // Guiones bajos para mostrar en la vista
        model.addAttribute("attemptsLeft", attempts); // Número de intentos restantes
        model.addAttribute("secretWord", secretWord); // La palabra secreta (puede usarse solo internamente)
        model.addAttribute("topic", topic); // El tema seleccionado

        return "playingGame";
    }

    @PostMapping("/guessLetter")
    public String guessLetter(@RequestParam(name = "letter") char letter,
                              @RequestParam(name = "secretWord") String secretWord,
                              @RequestParam(name = "spaces") String spaces,
                              @RequestParam(name = "attemptsLeft") int attemptsLeft,
                              @RequestParam(name = "topic") String topic,
                              Model model) {
        // Convertir los espacios actuales en un arreglo de caracteres
        char[] currentSpaces = spaces.toCharArray();
        boolean correctGuess = false;

        // Verificar si la letra está en la palabra secreta
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                currentSpaces[i] = letter; // Rellenar el espacio con la letra correcta
                correctGuess = true;
            }
        }

        // Actualizar intentos si no acertó
        if (!correctGuess) {
            attemptsLeft--;
        }

        // Verificar si ganó o perdió
        if (new String(currentSpaces).equals(secretWord)) {
            model.addAttribute("message", "¡Felicidades, has ganado!");
            return "gameOver"; // Redirige a la vista de final del juego
        } else if (attemptsLeft <= 0) {
            model.addAttribute("message", "Lo siento, has perdido. La palabra era: " + secretWord);
            return "gameOver"; // Redirige a la vista de final del juego
        }

        // Actualizar modelo para la vista del juego
        model.addAttribute("spaces", new String(currentSpaces));
        model.addAttribute("attemptsLeft", attemptsLeft);
        model.addAttribute("secretWord", secretWord);
        model.addAttribute("topic", topic);

        return "playingGame"; // Redirige a la vista del juego
    }

}
