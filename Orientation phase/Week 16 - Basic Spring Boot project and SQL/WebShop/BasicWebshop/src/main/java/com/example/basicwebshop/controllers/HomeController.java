package com.example.basicwebshop.controllers;

import com.example.basicwebshop.exceptions.WSException;
import com.example.basicwebshop.models.Search;
import com.example.basicwebshop.models.ShopItem;
import com.example.basicwebshop.repositories.ShopItemRepository;
import com.example.basicwebshop.services.SearchService;
import com.example.basicwebshop.services.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    private final ShopItemService shopItemService;
    private final SearchService searchService;

    @Autowired
    public HomeController(ShopItemService shopItemService, SearchService searchService) {
        this.shopItemService = shopItemService;
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String indexRedirect(){
        return "redirect:/webshop";
    }

    @GetMapping("/webshop")
    public String home(Model model){
        model.addAttribute(Attribute.ITEMS.toString(), shopItemService.getAll()); //it would use the ATTribute enum class, if i have a typo it can solve the problem// bc its on the enum list in diff version
        return "index";
    }

    @GetMapping("/only-available")
    public String onlyAvailable(Model model) throws WSException {
        model.addAttribute(Attribute.ITEMS.toString(), shopItemService.getAvailable());
        return "index";
    }

    @GetMapping("/cheapest-first")
    public String cheapestFirst(Model model){
        model.addAttribute(Attribute.ITEMS.toString(), shopItemService.sortByPrice());
        return "index";
    }

    @GetMapping("/contains-nike")
    public String containsNike(Model model) throws WSException {
        model.addAttribute(Attribute.ITEMS.toString(), searchService.search(new Search("nike")));
        return "index";
    }

    @GetMapping("/average-stock")
    public String averageStock(Model model){
        model.addAttribute(Attribute.AVERAGE.toString(), shopItemService.getAverageStock());
        return "index";
    }

    @GetMapping("/most-expensive")
    public String mostExpensive(Model model) throws WSException {
        model.addAttribute(Attribute.ITEMS.toString(), shopItemService.getMostExpensive());
        return "index";
    }

    @GetMapping("/price-in-eur")
    public String setEuroLocale(){
      return "redirect:/webshop?rate=de_DE";
    }

    @GetMapping("/price-in-original")
    public String setOriginalLocale(){
        return "redirect:/webshop?rate=cs_CZ";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("search") Search search) throws WSException {
        model.addAttribute(Attribute.ITEMS.toString(), searchService.search(search));
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute(Attribute.NEWITEM.toString())){
            model.addAttribute(Attribute.NEWITEM.toString(), new ShopItem());
        }
        return "administration";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ShopItem item, Model model){
        model.addAttribute(Attribute.ID.toString(), item.getId());
        shopItemService.add(item);
        return "redirect:/";
    }

    //exception thrown if some endpoint does not work in this controller
    @ExceptionHandler(WSException.class)
    public ModelAndView handleWebshopException( WSException exception){
        ModelAndView modelAndView = new ModelAndView("index", exception.getHttpStatus());
        modelAndView.addObject(Attribute.ERROR.toString(), exception.getMessage());
        modelAndView.addObject(Attribute.SEARCH.toString(), new Search());
        return modelAndView;
    }

    //everytime then there is a request, handled by the controller,
    //any controller where instantiating the model
    //the search bar would be visible on every page because of the modelattribute.
    // its added to the exceptionhandler too
    @ModelAttribute
    public void defaultAttributes(Model model){
        model.addAttribute(Attribute.SEARCH.toString(), new Search());
    }

}
