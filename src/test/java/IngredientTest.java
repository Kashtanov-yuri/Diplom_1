import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Создание ингредиента с разными типами")
    void testIngredientWithDifferentTypes(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 100.0f);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100.0",
            "SAUCE, sour cream, 200.0",
            "FILLING, cutlet, 150.0",
            "FILLING, dinosaur, 250.0"
    })
    @DisplayName("Создание ингредиента с комбинациями параметров")
    void testIngredientWithCombinations(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }
}
