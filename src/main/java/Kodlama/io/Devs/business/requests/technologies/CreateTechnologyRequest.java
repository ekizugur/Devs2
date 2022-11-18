package Kodlama.io.Devs.business.requests.technologies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnologyRequest {	
	private int languageId;
	private String name;

}
