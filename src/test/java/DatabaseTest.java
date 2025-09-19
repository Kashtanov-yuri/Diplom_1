import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun", "red bun"})
    @DisplayName("Проверка наличия всех булочек в базе")
    void testAllBunsExistInDatabase(String bunName) {
        Database database = new Database();
        boolean bunExists = database.availableBuns().stream()
                .anyMatch(bun -> bunName.equals(bun.getName()));
        assertTrue(bunExists, "Bun " + bunName + " should exist in database");
    }

    @ParameterizedTest
    @CsvSource({
            "hot sauce, SAUCE",
            "sour cream, SAUCE",
            "chili sauce, SAUCE",
            "cutlet, FILLING",
            "dinosaur, FILLING",
            "sausage, FILLING"
    })
    @DisplayName("Проверка типов всех ингредиентов в базе")
    void testAllIngredientsHaveCorrectTypes(String ingredientName, IngredientType expectedType) {
        Database database = new Database();
        Ingredient ingredient = database.availableIngredients().stream()
                .filter(i -> ingredientName.equals(i.getName()))
                .findFirst()
                .orElseThrow();
        assertEquals(expectedType, ingredient.getType());
    }
}
