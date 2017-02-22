package com.robbie.mvc.controllers;

import com.robbie.mvc.entity.Dealership;
import com.robbie.mvc.services.DealershipService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/dealer")
@Getter @Setter
public class DealershipController {
    @Autowired
    private DealershipService dealershipService;
    @RequestMapping(value = "/dealerships",method = RequestMethod.GET)
    public ModelAndView viewAllDealerships(ModelAndView modelAndView) {
        List<Dealership> dealerships = dealershipService.findAll();
        modelAndView.addObject("dealerships",dealerships);
        modelAndView.setViewName("allDealer");
        return modelAndView;
    }

    @RequestMapping(value = "/dealership/{id}",method = RequestMethod.GET)
    @PostAuthorize(value = "hasPermission(#model['dealership'],'read')")
    public String viewDealership(Model model, @PathVariable Long id) {
        Dealership dealership = dealershipService.findOne(id);
        model.addAttribute("dealership",dealership);
        return "detail";
    }

    @RequestMapping(value = "/dealership/{id}",method = RequestMethod.POST)
    public String deleteDealership(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Dealership dealership = dealershipService.findOne(id);
        dealershipService.delete(id);
//        redirectAttributes.addAttribute("dealership",dealership);
        redirectAttributes.addFlashAttribute("dealership", dealership);
        return "redirect:/mvc/robbie/home";
    }

}
