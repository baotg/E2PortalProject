package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.UserAccount;
import se.iuh.e2portal.model.UserAccountDetails;
import se.iuh.e2portal.service.UserAccountService;

@Controller
@RequestMapping("/account-management")
public class AccountController {

	@Autowired
	private UserAccountService accountService;

	@GetMapping("")
	public String getAllAccount(Model model,@Param(value = "ajax") String ajax) {

		model.addAttribute("accounts", accountService.findAll());
		if(ajax!=null)
			return "account-management::account-management";
		return "account-management";
	}
	@GetMapping("/reset")
	public String resetPassword(Model model, @Param(value = "id") String id,@Param(value = "ajax") String ajax) {
		UserAccountDetails account = accountService.findById(id);
		if(account.getUserAccount()!=null)
			account.getUserAccount().setPassword(UserAccount.DEFAULT_PASSWORD);
		accountService.save(account.getUserAccount());
		Message msg = Message.RESET_PASSWORD_SUCCESSFUL;
		model.addAttribute("msg", msg.getMessage() + account.getId());
		model.addAttribute("accounts", accountService.findAll());
		if(ajax!=null)
			return "account-management::account-management";
		return "account-management";
	}

}
