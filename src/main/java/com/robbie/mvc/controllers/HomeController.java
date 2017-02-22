package com.robbie.mvc.controllers;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.services.UserService;
import com.robbie.mvc.utils.ResultFilter;
import com.robbie.mvc.validators.UserValidator;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/robbie")
@SessionAttributes("user")
public class HomeController {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    @Autowired
    private UserService userService;
    @Autowired
    private Validator userValidator;
    /*@InitBinder
    public void InitBinder(HttpServletRequest request,
                           ServletRequestDataBinder binder) {
        // 不要删除下行注释!!! 将来"yyyy-MM-dd"将配置到properties文件中
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(getText("date.format", request.getLocale()));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
                dateFormat, true));
    }*/
    @ModelAttribute(value = "user")
    public User getUser() {
        System.out.println("Add a new user to model!");
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(DEFAULT_ROLE);
        User user = new User();
        return user;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public String services() {
        return "services";
    }

    @RequestMapping(value = "/appointment",method = RequestMethod.GET)
    public String appointment() {
        return "appointment";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String goHome(SessionStatus status) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        status.setComplete();
        return "home";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register() {
        return "signUp";
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST,params = "firstName=robbie")
//    public String review(Model model,@Valid @ModelAttribute(value = "user") User user, @RequestParam String lastName, BindingResult errors) {
    public String review(@Valid @ModelAttribute(value = "user") User user, BindingResult errors) {
//        System.out.println("Last Name is " + lastName);
        if ("runtime".equalsIgnoreCase(user.getLastName())) {
            throw new RuntimeException();
        } else if ("null".equalsIgnoreCase(user.getLastName())) {
            throw new NullPointerException();
        }
        this.userValidator.validate(user,errors);
        if (!errors.hasErrors()) {
            System.out.println("This user validated.");
            return "detail";
        } else {
            System.out.println("This user did not validate.");
            return "signUp";
        }
//        return "userDetail";
    }

    @RequestMapping(value = "/save")
//    @PreAuthorize(value = "hasAnyRole('ROLE_USER') or hasAnyRole('ROLE_ADMIN')")
//    @RolesAllowed("ROLE_USER,ROLE_ADMIN")
    @PostAuthorize(value = "#user.role == 'ROLE_USER'")
    public String save(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        System.out.println("register invoked!");
        redirectAttributes.addFlashAttribute("user", user);
//        user.getAuthorities().add(new SimpleGrantedAuthority(user.getRole()));
        userService.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/mvc/robbie/home";
    }

/*    @ExceptionHandler(NullPointerException.class)
    public String handleError(HttpServletRequest request) {
        return "exceptions/exception";
    }*/

    @RequestMapping(value = "/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

/*    @RequestMapping(value = "/getSingleUser/{firstName}")
    @ResponseBody
    public User getSingleUser(@PathVariable("firstName") String firstName) {
        User user = userService.findUserByFirstName(firstName);
        System.out.println(ReflectionToStringBuilder.toString(user));
        return user;
    }*/

    @RequestMapping(value = "/getSingleUser/{firstName}")
    @ResponseBody
    public User getSingleUser(@PathVariable("firstName") User user) {
        return user;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String result;
        if (!file.isEmpty()) {
            result = "File size is " + file.getSize();
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            result = "There is a problem!";
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/findByLastName/{lastName}")
    @ResponseBody
    public List<User> findUsers(@PathVariable("lastName") String lastName) {
        return userService.findUserByLastName(lastName);
    }

    @RequestMapping(value = "/findOne/{id}")
    @ResponseBody
    public User findUserById(@PathVariable("id") User user) {
        return user;
    }

    @RequestMapping(value = "/deleteSingleUser/{id}")
    @ResponseBody
    public ResponseEntity deleteSingleUser(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity("Not exist!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Deleted:)",HttpStatus.OK);
    }

    @RequestMapping(value = "/findByDealershipName/{name}")
    @ResponseBody
    public List<User> findByDealershipName(@PathVariable("name") String name) {
        return userService.findByDealershipName(name);
    }

    @RequestMapping(value = "/userPage/{pageNo}/{pageSize}")
    @ResponseBody
    public List<User> findAllByPage(@PathVariable int pageNo,@PathVariable int pageSize) {
        return userService.findAllByPage(pageNo,pageSize);
    }

    @RequestMapping(value = "/users/{id1}/{id2}/{id3}/{id4}")
    @ResponseBody
    public ResponseEntity findByIDs(@PathVariable long id1,@PathVariable long id2,@PathVariable long id3,@PathVariable long id4) {
        Long[] ids = {id1,id2,id3,id4};
        List<User> users = userService.findByIDs(ids);
/*        List<User> users = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            users.addAll(userService.findByIDs(ids[i]));
        }*/
        ResponseEntity responseEntity = new ResponseEntity(users,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users/{pageNo}/{pageSize}")
    @ResponseBody
    public ResponseEntity customQuery(@PathVariable int pageNo,@PathVariable int pageSize) {
        Page<User> page = userService.findAllByPageAndSort(pageNo,pageSize);
        ResultFilter<User> resultFilter = wrapPageResult(page,pageNo);
        ResponseEntity responseEntity = new ResponseEntity(resultFilter,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public ResponseEntity pageParam(Pageable pageable, Sort sort) {
        String sortField = sort != null ? sort.iterator().next().getProperty() : "age";
        Page<User> page = userService.findAll(pageable);
        ResultFilter<User> resultFilter = wrapPageResult(page,pageable.getPageNumber());
        resultFilter.setSort(sortField);
        ResponseEntity responseEntity = new ResponseEntity(resultFilter,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users/{pageNo}/{pageSize}/{age}")
    @ResponseBody
    public ResponseEntity findByAge(@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable int age) {
        Page<User> page = userService.findByAgeGreatThanOrderByAgeDesc(pageNo, pageSize, age);
        ResultFilter<User> resultFilter = wrapPageResult(page,pageNo);
        ResponseEntity responseEntity = new ResponseEntity(resultFilter,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users/lastName/{lastName}/age/{age}")
    @ResponseBody
    public ResponseEntity updateAge(@PathVariable String lastName,@PathVariable int age) {
        int updateCount = userService.updateAge(lastName, age);
        ResponseEntity responseEntity = new ResponseEntity(updateCount,HttpStatus.OK);
        return responseEntity;
    }

    private ResultFilter wrapPageResult(Page page,int pageNo) {
        ResultFilter<User> resultFilter = new ResultFilter<>();
        resultFilter.setItems(page.getContent());
        resultFilter.setHasNextPage(page.hasNext());
        resultFilter.setHasPrePage(page.hasPrevious());
        resultFilter.setCurrentPage(pageNo);
        resultFilter.setTotalPages(page.getTotalPages());
        resultFilter.setPageSize(page.getSize());
        return resultFilter;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Validator getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(Validator userValidator) {
        this.userValidator = userValidator;
    }
/*    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator());
    }*/
}
