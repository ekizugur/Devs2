package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.technologies.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technologies.DeleteTechnologyRequest;
import Kodlama.io.Devs.business.requests.technologies.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technologies.GetAllTechnologiesResponse;

public interface TechnologyService {
	List<GetAllTechnologiesResponse> getAll();
	void add(CreateTechnologyRequest createTechnologyRequest) throws Exception;
	void update(UpdateTechnologyRequest updateTechnologyRequest)throws Exception;
	void delete(DeleteTechnologyRequest deleteTechnologyRequest) throws Exception;

}
