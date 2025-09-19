import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @ParameterizedTest
    @CsvSource({
            "black bun, 100.0",
            "white bun, 200.0",
            "red bun, 300.0",
            "special bun, 999.99"
    })
    @DisplayName("Создание булочки с разными параметрами")
    void testBunCreationWithDifferentParameters(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }

    @ParameterizedTest
    @ValueSource(floats = {0.0f, -50.0f, Float.MAX_VALUE, Float.MIN_VALUE})
    @DisplayName("Создание булочки с разными ценами")
    void testBunWithDifferentPrices(float price) {
        Bun bun = new Bun("test bun", price);
        assertEquals(price, bun.getPrice());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    @DisplayName("Создание булочки с разными именами")
    void testBunWithDifferentNames(String name) {
        Bun bun = new Bun(name, 100.0f);
        assertEquals(name, bun.getName());
    }
}
