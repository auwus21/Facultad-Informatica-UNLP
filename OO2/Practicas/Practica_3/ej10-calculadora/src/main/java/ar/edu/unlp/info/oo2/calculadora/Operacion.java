package ar.edu.unlp.info.oo2.calculadora;

public enum Operacion {
    SUMA {
        public double aplicar(double a, double b) { return a + b; }
    },
    RESTA {
        public double aplicar(double a, double b) { return a - b; }
    },
    MULTIPLICACION {
        public double aplicar(double a, double b) { return a * b; }
    },
    DIVISION {
        public double aplicar(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Division por cero");
            }
            return a / b;
        }
    };

    public abstract double aplicar(double a, double b);
}