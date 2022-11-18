package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.languages.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.DeleteLanguageRequest;
import Kodlama.io.Devs.business.requests.languages.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.languages.GetAllLanguageResponse;

@RestController //annotation
@RequestMapping("/api/languages")
public class LanguagesController {
	private LanguageService languageService;
	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	@GetMapping("/getall")
	public List<GetAllLanguageResponse> getAll(){
		return languageService.getAll();		
	}
	
	@PostMapping("/add")
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception{
		this.languageService.add(createLanguageRequest);
	}
	@DeleteMapping("/delete")
	public void delete(DeleteLanguageRequest deleteLanguageRequest) throws Exception {
		this.languageService.delete(deleteLanguageRequest);
	}
	@PutMapping("/update")
	public void update(UpdateLanguageRequest updateLanguageRequest) throws Exception {
		this.languageService.update(updateLanguageRequest);
	}
	

	
}
