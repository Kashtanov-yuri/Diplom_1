import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PraktikumTest {

    @Test
    @DisplayName("Main метод должен запускаться без исключений")
    void testMainMethodRunsWithoutExceptions() {
        assertDoesNotThrow(() -> {
            Praktikum.main(new String[]{});
        });
    }

    @Test
    @DisplayName("Main метод с пустыми аргументами")
    void testMainMethodWithEmptyArgs() {
        assertDoesNotThrow(() -> {
            Praktikum.main(new String[]{});
        });
    }

    @Test
    @DisplayName("Main метод должен завершаться")
    void testMainMethodCompletes() {
        long startTime = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            Praktikum.main(new String[]{});
        });

        thread.start();

        try {
            thread.join(5000);
            long endTime = System.currentTimeMillis();
            if (thread.isAlive()) {
                thread.interrupt();
                fail("Main method did not complete within 5 seconds");
            }

            assertTrue((endTime - startTime) < 5000, "Main method should complete quickly");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Test was interrupted");
        }
    }

    @Test
    @DisplayName("Проверка существования класса")
    void testClassExists() {
        assertNotNull(Praktikum.class);
        assertEquals("Praktikum", Praktikum.class.getSimpleName());
    }

    @Test
    @DisplayName("Проверка наличия main метода")
    void testMainMethodExists() {
        assertDoesNotThrow(() -> {
            Praktikum.class.getMethod("main", String[].class);
        });
    }

    @Test
    @DisplayName("Main метод должен быть публичным и статическим")
    void testMainMethodSignature() {
        try {
            var mainMethod = Praktikum.class.getMethod("main", String[].class);
            assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
            assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Main method not found");
        }
    }
}