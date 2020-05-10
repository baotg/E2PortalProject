package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.UserAccount;
import se.iuh.e2portal.model.UserAccountDetails;
import se.iuh.e2portal.service.UserAccountService;
import org.springframework.data.domain.Page;
@Controller
@RequestMapping("/account-management")
public class AccountController {

	@Autowired
	private UserAccountService accountService;

	@GetMapping("")
	public String getAllAccount(@PageableDefault(size = 10) Pageable pageable, Model model,@Param("ajax")String ajax) {
		Page<UserAccount> page = accountService.findAll(pageable);
		  model.addAttribute("page", page);
		if(ajax!=null)
			return "account-management::account-management";
		return "account-management";
	}
	@GetMapping("/search")
	public String search(@PageableDefault(size = 10) Pageable pageable, Model model,@Param("ajax")String ajax,@RequestParam("id")String id) {
		Page<UserAccount> page = accountService.findAll(pageable,id);
		  model.addAttribute("page", page);
		if(ajax!=null)
			return "account-management::account-table";
		return "account-management";
	}
	@GetMapping("/reset")
	public String resetPassword(Model model, @Param(value = "id") String id) {
		UserAccountDetails account = accountService.findById(id);
		if(account.getUserAccount()!=null)
			account.getUserAccount().setPassword(UserAccount.DEFAULT_PASSWORD);
		accountService.save(account.getUserAccount());
		Message msg = Message.RESET_PASSWORD_SUCCESSFUL;
		model.addAttribute("msg", msg.getMessage() + account.getId());
		return "account-management::reset-message";
	}

}
