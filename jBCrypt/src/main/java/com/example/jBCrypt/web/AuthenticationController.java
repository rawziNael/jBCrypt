package com.example.jBCrypt.web;

import com.example.jBCrypt.domain.Customer;
import com.example.jBCrypt.infrastructure.CustomerRepository;
import com.example.jBCrypt.infrastructure.RecipeRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final RecipeRepository recipeRepository;

    public AuthenticationController(CustomerRepository customerRepository, RecipeRepository recipeRepository) {
        this.customerRepository = customerRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    RedirectView getIndex() {
        return new RedirectView("/login");
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUpDone(Model model, String username, String password) {
        if (customerRepository.findAllByUsername(username) == null) {
            String createPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
            Customer customer = new Customer(username, createPass);
            customerRepository.save(customer);
            return new RedirectView("/login");
        } else {
            return new RedirectView("/signup");
        }
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, String username, String password) {
        Customer customer = customerRepository.findAllByUsername(username);
        if (customer == null || !(BCrypt.checkpw(password, customer.getPassword()))) {
            return new RedirectView("/login");
        }
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return new RedirectView("/recipes");
    }

    @GetMapping("/recipes")
    public String getAllRecipe() {
        return "recipes";
    }

    @GetMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/login");
    }
}
