package keyboard.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import keyboard.works.model.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, String> {

}
