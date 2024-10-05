package starter.petstore;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenFetchingAlreadyAvailablePet {
  private Long newPetId = null;
  private PetApiActions petApi;

  @Test
  public void fetchAlreadyAvailablePet() {
    String petName = "Kitty";
    newPetId = petApi.givenAPetIsAvailableInThePetStore(petName);
    petApi.whenIAskForAPetWithId(newPetId);
    petApi.thenISeeThePetAsResult(petName);
  }
}
