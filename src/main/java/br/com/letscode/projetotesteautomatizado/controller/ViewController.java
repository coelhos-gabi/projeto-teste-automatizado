package br.com.letscode.projetotesteautomatizado.controller;

import br.com.letscode.projetotesteautomatizado.model.Ator;
import br.com.letscode.projetotesteautomatizado.model.Filme;
import br.com.letscode.projetotesteautomatizado.service.AtorService;
import br.com.letscode.projetotesteautomatizado.service.FilmeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ViewController {

    private FilmeService filmeService;
    private AtorService atorService;

    @GetMapping("/home")
    public String home(Model model){
        List<Filme> listaFilmes = this.filmeService.listarFilmes();
        model.addAttribute("filmes",listaFilmes);
        return "home";
    }

    @GetMapping("/create-filme")
    public String createFilme(Model model){
        Filme filme = new Filme();
        filme.setAtores(List.of(new Ator(),new Ator()));

        model.addAttribute("filme", filme);
        model.addAttribute("atores", atorService.listarAtores());
        return "create-filme";
    }

    @GetMapping("/create-ator")
    public String createComposto(Model model) {
        model.addAttribute("ator", new Ator());
        return "create-ator";
    }

    @PostMapping("/save-filme")
    public String save(@Valid Filme filme, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-filme";
        }
        filmeService.adicionarFilme(filme);
        return "redirect:/home";
    }

    @PostMapping("/save-ator")
    public String saveComposto(@Valid Ator ator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-ator";
        }
        atorService.adicionarAtor(ator);
        return "redirect:/home";
    }
}
