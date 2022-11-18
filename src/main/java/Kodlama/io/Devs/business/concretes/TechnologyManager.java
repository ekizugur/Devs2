package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.requests.technologies.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technologies.DeleteTechnologyRequest;
import Kodlama.io.Devs.business.requests.technologies.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technologies.GetAllTechnologiesResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Devs.entities.concretes.Technology;
@Service
public class TechnologyManager implements TechnologyService {
	private TechnologyRepository technologyRepository;
	private LanguageRepository languageRepository;
	
	public TechnologyManager(TechnologyRepository technologyRepository, LanguageRepository languageRepository) {
		super();
		this.technologyRepository = technologyRepository;
		this.languageRepository = languageRepository;
	}


	public List<GetAllTechnologiesResponse> getAll() {
		List<Technology> technologies=technologyRepository.findAll();
		List<GetAllTechnologiesResponse> technologyResponse=new ArrayList<GetAllTechnologiesResponse>();
		for (Technology technology : technologies) {
			GetAllTechnologiesResponse responseItem=new GetAllTechnologiesResponse();
			responseItem.setId(technology.getId());
			responseItem.setName(technology.getName());
			technologyResponse.add(responseItem);
		}
		return technologyResponse;
	}
	

	@Override
	public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
		Technology technology=new Technology();
		Language language=this.languageRepository.findById(createTechnologyRequest.getLanguageId()).get();
		
		if(createTechnologyRequest.getName().isEmpty()) 
			throw new Exception("Teknoloji adı boş geçilemez...");
		else if(isNameExist(createTechnologyRequest.getName())) 
			throw new Exception("Teknoloji ismi tekrar edemez...");
		else {
			
			technology.setName(createTechnologyRequest.getName());
			technology.setLanguage(language);
			technologyRepository.save(technology);
			
		}
	}
	private boolean isNameExist(String name) {
		for (GetAllTechnologiesResponse getTechnologiesResponse :getAll()) {
			if(name.equals(getTechnologiesResponse.getName())) {
				return true;
			}
			
		}
		return false;
		
		
	}
	
	private boolean isIdExist(int id) {
		for (GetAllTechnologiesResponse technology : getAll()) {
			if(technology.getId()==id) return true;
		}
		return false;
	}
	private boolean isNameEmpty(String name) {
		if(name.isEmpty()) return true;
		
		return false;
	}
	

	@Override
	public void update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
		Technology technology=this.technologyRepository.findById(updateTechnologyRequest.getId()).get();
		Language language=this.languageRepository.findById(updateTechnologyRequest.getLanguageId()).get();
		
		
			if(isNameExist(updateTechnologyRequest.getName())) throw new Exception("Bu dil zaten kayıtlı");
			if(isNameEmpty(updateTechnologyRequest.getName())) throw new Exception("Dil ismi boş geçilemez...");
			technology.setName(updateTechnologyRequest.getName());
			technology.setLanguage(language);
			this.technologyRepository.save(technology);
		
		
	}

	@Override
	public void delete(DeleteTechnologyRequest deleteTechnologyRequest) throws Exception {
		if(!isIdExist(deleteTechnologyRequest.getId())) throw new Exception("Teknoloji Id bulunamadı");
		this.technologyRepository.deleteById(deleteTechnologyRequest.getId());
	}
	

}
