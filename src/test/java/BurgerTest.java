import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    @DisplayName("Добавление разного количества ингредиентов")
    void testAddMultipleIngredients(int count) {
        Burger burger = new Burger();
        Bun bun = new Bun("test", 100.0f);
        burger.setBuns(bun);

        for (int i = 0; i < count; i++) {
            Ingredient ingredient = new Ingredient(
                    i % 2 == 0 ? IngredientType.SAUCE : IngredientType.FILLING,
                    "ingredient" + i,
                    50.0f + i
            );
            burger.addIngredient(ingredient);
        }

        assertEquals(count, burger.ingredients.size());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 0",
            "2, 3",
            "0, 2"
    })
    @DisplayName("Перемещение ингредиентов с разных позиций")
    void testMoveIngredientDifferentPositions(int fromIndex, int toIndex) {
        Burger burger = new Burger();
        Bun bun = new Bun("test", 100.0f);
        burger.setBuns(bun);
        for (int i = 0; i < 4; i++) {
            burger.addIngredient(new Ingredient(IngredientType.FILLING, "ing" + i, 50.0f));
        }
        String originalName = burger.ingredients.get(fromIndex).getName();
        burger.moveIngredient(fromIndex, toIndex);
        assertEquals(originalName, burger.ingredients.get(toIndex).getName());
    }
}