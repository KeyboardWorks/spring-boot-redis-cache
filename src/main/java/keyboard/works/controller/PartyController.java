package keyboard.works.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import keyboard.works.model.request.PartyRequest;
import keyboard.works.model.response.PartyResponse;
import keyboard.works.service.PartyService;

@RestController
@RequestMapping(
	path = "/parties",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class PartyController {

	@Autowired
	private PartyService partyService;
	
	@GetMapping
	public List<PartyResponse> getParties() {
		return partyService.getParties();
	}
	
	@GetMapping(path = "{id}")
	public PartyResponse getParty(@PathVariable("id") String id) {
		return partyService.getParty(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public PartyResponse createParty(@RequestBody PartyRequest partyRequest) {
		return partyService.createParty(partyRequest);
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PartyResponse updateParty(@PathVariable("id") String id, @RequestBody PartyRequest partyRequest) {
		return partyService.updateParty(id, partyRequest);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteParty(@PathVariable("id") String id) {
		partyService.deleteParty(id);
	}
	
}
