package starter.petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.core.steps.UIInteractions;
import org.hamcrest.Matchers;

import static net.serenitybdd.rest.SerenityRest.*;

public class PetApiActions extends UIInteractions {
  @Given("{0} is available in the pet store")
  public Long givenAPetIsAvailableInThePetStore(String name) {
    Pet pet = new Pet(name, "available");

    return given()
            .baseUri("https://petstore.swagger.io/v2")
            .basePath("/pet")
            .body(pet, ObjectMapperType.GSON)
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON).post().getBody().as(Pet.class, ObjectMapperType.GSON).getId();
  }

  @When("I ask for a pet using its ID: {0}")
  public void whenIAskForAPetWithId(Long id) {
    when().get("/" + id);
  }

  @Then("I get {0} as result")
  public void thenISeeThePetAsResult(String name) {
    then().body("name", Matchers.equalTo(name), "id", Matchers.notNullValue(), "status", Matchers.equalTo("available"));
  }
}
