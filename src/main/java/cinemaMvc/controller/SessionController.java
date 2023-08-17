package cinemaMvc.controller;

import cinemaMvc.model.Movie;
import cinemaMvc.model.Session;
import cinemaMvc.service.Impl.MovieService;
import cinemaMvc.service.Impl.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {
    private SessionService service;
    private MovieService movieService;


    @Autowired
    public SessionController(SessionService service,MovieService movieService) {
        this.service = service;
        this.movieService = movieService;
    }

    @ModelAttribute("movieList")
    public List<Movie> movieList(){
        return   movieService.findAll();
    }

    @GetMapping("save")
    public String save(Model model){
        model.addAttribute("save",new Session());
        return "session/save";
    }

    @PostMapping("save_session")
    public String saveSession(@ModelAttribute Session session){
        service.save(session);
        return "redirect:session/save";
    }

    @GetMapping("find_all")
    public String findAll(Model model){
        model.addAttribute("session_all",service.findAll());
        return "session/find_all";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model) {
        model.addAttribute("all_session_id",service.findAllId(id));
        return "/session/all_session_id";
    }

    @GetMapping("find_by_id{id}")
    public String findByID(@PathVariable("id") int id,Model model){
        model.addAttribute("find_id",service.findById(id));
        return "session/find_by_id";
    }

    @GetMapping("update{id}")
    public String updateSessoiin(Model model,@PathVariable("id") int id){
        Session session = service.findById(id);
        model.addAttribute("update",session);
        return "session/update_session";
    }

    @PostMapping("merge_update{id}")
    public String mergeUpdate(@ModelAttribute Session session,@PathVariable("id") int id){
        service.updateById(id,session);
        return "redirect:session/find_all";
    }

    @GetMapping("delete{id}")
    public String delete(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:session/find_all";
    }
}

