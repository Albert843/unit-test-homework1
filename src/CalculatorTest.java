import seminars.first.model.Calculator;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
    public static void main(String[] args) {
        // Проверка базового функционала с целыми числами:
        if (8 != Calculator.calculation(2, 6, '+')) {
            throw new AssertionError("Ошибка в методе");
        }

        if (0 != Calculator.calculation(2, 2, '-')) {
            throw new AssertionError("Ошибка в методе");
        }

        if (14 != Calculator.calculation(2, 7, '*')) {
            throw new AssertionError("Ошибка в методе");
        }

        if (2 != Calculator.calculation(100, 50, '/')) {
            throw new AssertionError("Ошибка в методе");
        }

        // Случаи с неправильными аргументами
        // аргумент operator типа char, должен вызывать исключение, если он получает не базовые символы (+-*/)
        try {
            Calculator.calculation(8, 4, '_');
        } catch (IllegalStateException e) {
            if (!e.getMessage().equals("Unexpected value operator: _")) {
                throw new AssertionError("Ошибка в методе");
            }
        }

        // Проверка базового функционала с целыми числами, с использованием утверждений:
        assert 8 == Calculator.calculation(2, 6, '+');
        assert 0 == Calculator.calculation(2, 2, '-');
        assert 14 == Calculator.calculation(2, 7, '*');
        assert 2 == Calculator.calculation(100, 50, '/');

        // Проверка базового функционала с целыми числами, с использованием утверждений AssertJ:
        assertThat(Calculator.calculation(2, 6, '+')).isEqualTo(8);
        assertThat(Calculator.calculation(2, 2, '-')).isEqualTo(0);
        assertThat(Calculator.calculation(2, 7, '*')).isEqualTo(14);
        assertThat(Calculator.calculation(100, 50, '/')).isEqualTo(2);

        // Проверка ожидаемого исключения, с использованием утверждений AssertJ:
        assertThatThrownBy(() ->
                Calculator.calculation(8, 4, '_')
        ).isInstanceOf(IllegalStateException.class);

        System.out.println(Calculator.calculation(2_147_483_647, 1, '+')); // integer overflow
        System.out.println(Calculator.squareRootExtraction(169));


        //===================================== homework ==========================================

        //        проверяем утверждения, связанные с правильностью вычисления цены со скидкой
        assertThat(Calculator.calculatingDiscount(9000, 70)).isEqualTo(2700);
        assertThat(Calculator.calculatingDiscount(2200, 7)).isEqualTo(2046);
        assertThat(Calculator.calculatingDiscount(1000, 10)).isEqualTo(900);
        assertThat(Calculator.calculatingDiscount(3300, 0)).isEqualTo(3300);
//        проверяем утверждения, связанные с выбрасыванием ошибки
        // проверяем верхний предел скидки
        assertThatThrownBy(() -> Calculator.calculatingDiscount(1000, 55))
                .isInstanceOf(ArithmeticException.class).hasMessage("Скидка в диапазоне от 0 до 50%");
        // проверяем что скидка не может быть отрицательной
        assertThatThrownBy(() -> Calculator.calculatingDiscount(1000, -10))
                .isInstanceOf(ArithmeticException.class).hasMessage("Скидка не может быть отрицательной");
        // проверяем что сумма покупки не может быть отрицательной
        assertThatThrownBy(() -> Calculator.calculatingDiscount(700, 20))
                .isInstanceOf(ArithmeticException.class).hasMessage("Сумма покупки меньше ьинимальной суммы (1000 руб)");
    }
}