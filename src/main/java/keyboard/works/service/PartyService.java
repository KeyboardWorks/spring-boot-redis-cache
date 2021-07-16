package keyboard.works.service;

import java.util.List;

import keyboard.works.model.request.PartyRequest;
import keyboard.works.model.response.PartyResponse;

public interface PartyService {

	List<PartyResponse> getParties();
	
	PartyResponse getParty(String id);
	
	PartyResponse createParty(PartyRequest partyRequest);
	
	PartyResponse updateParty(String id, PartyRequest partyRequest);
	
	void deleteParty(String id);
	
}
