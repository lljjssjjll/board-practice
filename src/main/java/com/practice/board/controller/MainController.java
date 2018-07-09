package com.practice.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.board.dao.AppUserDao;
import com.practice.board.dao.CountryDao;
import com.practice.board.entity.AppUser;
import com.practice.board.entity.Country;
import com.practice.board.formbean.AppUserForm;
import com.practice.board.utils.WebUtils;
import com.practice.board.validator.AppUserValidator;

@Controller
public class MainController {

	@Autowired
	private AppUserDao appUserDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private AppUserValidator appUserValidator;
	
	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome!");
		model.addAttribute("message", "This is welcome page!");
		return "welcomPage";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		
		return "adminPage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}
	
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		String userName = principal.getName();
		
		System.out.println("User name : " + userName);
		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		
		return "userInfoPage";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		if(principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			
			String userInfo = WebUtils.toString(loginedUser);
			
			model.addAttribute("userInfo", userInfo);
			
			String message = ""
					+ "Hi " + principal.getName()
					+ "<br /> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		
		return "403Page";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		//Form 타겟
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target = " + target);
		
		if (target.getClass() == AppUserForm.class) {
			dataBinder.setValidator(appUserValidator);
		}
		
	}
	
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public String viewMembers(Model model) {
		List<AppUser> list = appUserDao.findUserAccounts();
		
		model.addAttribute("members", list);
		
		return "membersPage";
	}
	
	@RequestMapping(value = "/registerSuccessful", method = RequestMethod.GET)
	public String viewRegisterSuccessful(Model model) {
		return "registerSuccessfulPage";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		AppUserForm form = new AppUserForm();
		List<Country> countries = countryDao.findCountrys();
		model.addAttribute("appUserForm", form);
		model.addAttribute("countries", countries);
		return "registerPage";
	}
	
	//회원 등록
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model,
			@ModelAttribute("appUserForm") @Validated AppUserForm appUserForm,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		//유효성 체크
		if (result.hasErrors()) {
			List<Country> countries = countryDao.findCountrys();
			model.addAttribute("countries", countries);
			return "registerPage";
		}
		
		AppUser newAccount = null;
		try {
			newAccount = appUserDao.createAccount(appUserForm);
		} catch (Exception e) {
			List<Country> countries = countryDao.findCountrys();
			model.addAttribute("countries", countries);
			model.addAttribute("errorMessage", "Error : " + e.getMessage());
			return "registerPage";
		}
		
		redirectAttributes.addFlashAttribute("flashUser", newAccount);
		
		return "redirect:/registerSuccessful";
	}
	
}
