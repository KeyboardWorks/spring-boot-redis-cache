package keyboard.works.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import keyboard.works.model.Party;
import keyboard.works.model.request.PartyRequest;
import keyboard.works.model.response.PartyResponse;
import keyboard.works.repository.PartyRepository;
import keyboard.works.service.PartyService;
import keyboard.works.utils.ResponseHelper;

@Service
@CacheConfig(cacheNames = "partyCache")
public class PartyServiceImpl implements PartyService {

	@Autowired
	private PartyRepository partyRepository;
	
	@Cacheable(cacheNames = "parties")
	@Override
	public List<PartyResponse> getParties() {
		waitSomeTime();
		return ResponseHelper.createResponses(PartyResponse.class, partyRepository.findAll());
	}

	@Cacheable(cacheNames = "parties", key = "#id", unless = "#result == null")
	@Override
	public PartyResponse getParty(String id) {
		waitSomeTime();
		Party party = partyRepository.findById(id).orElseThrow(RuntimeException::new);
		
		return ResponseHelper.createResponse(PartyResponse.class, party);
	}
	
	@CacheEvict(cacheNames = "parties", allEntries = true)
	@Override
	public PartyResponse createParty(PartyRequest partyRequest) {
		
		Party party = new Party();
		BeanUtils.copyProperties(partyRequest, party);
		
		party = partyRepository.save(party);
		
		return ResponseHelper.createResponse(PartyResponse.class, party);
	}

	@CacheEvict(cacheNames = "parties", allEntries = true)
	@Override
	public PartyResponse updateParty(String id, PartyRequest partyRequest) {
		
		Party party = partyRepository.findById(id).orElseThrow(RuntimeException::new);
		BeanUtils.copyProperties(partyRequest, party);
		
		party = partyRepository.save(party);
		
		return ResponseHelper.createResponse(PartyResponse.class, party);
	}
	
	@Caching(evict = { 
			@CacheEvict(cacheNames = "parties", key = "#id"),
			@CacheEvict(cacheNames = "parties", allEntries = true) 
	})
	@Override
	public void deleteParty(String id) {
		
		Party party = partyRepository.findById(id).orElseThrow(RuntimeException::new);
		
		partyRepository.delete(party);
		
	}
	
	private void waitSomeTime() {
		System.out.println("Long Wait Begin");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Long Wait End");
	}

}
