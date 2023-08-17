package cinemaMvc.controller;

import cinemaMvc.model.Movie;
import cinemaMvc.service.Impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("save")
    public String save(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie/save";
    }

    @PostMapping("save_movie")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:movie/save";
    }

    @GetMapping("find_all")
    public String findAll(Model model) {
        model.addAttribute("find_all", movieService.findAll());
        return "movie/find_all";
    }

    @GetMapping("find_by_id{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("find_id", movieService.findById(id));
        return "movie/find_by_id";
    }

    @GetMapping("update{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("movie", movieService.findById(id));
        return "movie/update";
    }

    @PostMapping("merge_upadte{id}")
    public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable("Id") int id) {
        movieService.updateById(id, movie);
        return "redirect:movie/find_all";
    }

    @GetMapping("delete{id}")
    public String delete(@PathVariable("id") int id) {
        movieService.deleteById(id);
        return "movie/find_all";
    }
}
