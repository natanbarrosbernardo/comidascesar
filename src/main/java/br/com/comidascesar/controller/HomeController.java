package br.com.comidascesar.controller;

import br.com.comidascesar.dto.ComidasDTO;
import br.com.comidascesar.service.Comidaservice;
import br.com.comidascesar.service.ComidaserviceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class HomeController {

    private final ComidaserviceImpl service;

    @Autowired
    public HomeController(ComidaserviceImpl service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ComidasDTO", new ComidasDTO());
        return "index";
    }

    //Adicionei uma verificação para caso o usuário não preencha algum campo.
    @PostMapping("/Comidas")
    public String createComidas(@Valid ComidasDTO ComidasDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        service.save(ComidasDTO);
        return "redirect:/Comidas";
    }

    @GetMapping("/Comidas")
    public String getComidas(Model model) {
        List<ComidasDTO> allComidas = service.findAll();
        model.addAttribute("Comidas", allComidas);
        return "dashboard";
    }

    // Rota para deletar.
    @GetMapping("/Comidas/delete/{id}")
    public String deleteComidas(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/Comidas"; // ReComidasrega o dashboard
    }

    // Rota para Comidasregar os dados de edição.
    @GetMapping("/Comidas/edit/{id}")
    public String editComidasForm(@PathVariable String id, Model model) {
        ComidasDTO ComidasDTO = service.findById(id);
        if (ComidasDTO == null) {
            return "redirect:/Comidas";
        }
        model.addAttribute("ComidasDTO", ComidasDTO);
        return "index";
    }

    // Rota para salvar a edição.
    @PostMapping("/Comidas/update/{id}")
    public String updateComidas(@PathVariable String id,@Valid ComidasDTO ComidasDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        service.update(id, ComidasDTO);
        return "redirect:/Comidas";
    }

}