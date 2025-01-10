package com.gml.gestionCursos.controllers;

import com.gml.gestionCursos.entities.Curso;
import com.gml.gestionCursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String cursos(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "cursos";
    }
    
    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model) {
    	Curso curso = new Curso();
    	
    	model.addAttribute("curso", curso);
    	model.addAttribute("pageTitle", "Agregar Curso");
    	
    	return "agregarCurso";
    }
    @PostMapping("/cursos/guardar")
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes){
        try{
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message", "El curso se guardo correctamente");
        }
        catch(Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }
}
