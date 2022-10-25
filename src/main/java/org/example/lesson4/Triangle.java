package org.example.lesson4;

public class Triangle {
    private int sideA;
    private int sideB;
    private int sideC;


    Triangle(int a, int b, int c) {
        setSideA(a);
        setSideB(b);
        setSideC(c);
    }


    public void setSideA(int sideA) {
        this.sideA = sideA;
    }


    public void setSideB(int sideB) {
        this.sideB = sideB;
    }


    public void setSideC(int sideC) {
        this.sideC = sideC;
    }


    public double countSquare() throws SideEqualZeroException, LengthIsNegativeException, CanNotBeTriangleException {

        if (sideA == 0) throw new SideEqualZeroException("sideA = 0." +
                " Длина стороны треугольника не может быть равна 0.");
        if (sideB == 0) throw new SideEqualZeroException("sideB = 0. " +
                "Длина стороны треугольника не может быть равна 0.");
        if (sideC == 0) throw new SideEqualZeroException("sideC = 0. " +
                "Длина стороны треугольника не может быть равна 0.");

        if (sideA < 0)
            throw new LengthIsNegativeException("sideA < 0. " +
                    "Длина стороны треугольника не может иметь отрицательное значение.");
        double p = (double) (sideA + sideB + sideC) / 2;
        if (sideB < 0)
            throw new LengthIsNegativeException("sideB < 0. " +
                    "Длина стороны треугольника не может иметь отрицательное значение.");
        if (sideC < 0)
            throw new LengthIsNegativeException("sideC < 0. " +
                    "Длина стороны треугольника не может иметь отрицательное значение.");

        if (sideA >= (sideB + sideC))
            throw new CanNotBeTriangleException("sideA = " + sideA + ". " +
                    "Значение слишком велико, длина стороны A должна быть меньше " + (sideB + sideC) +
                    ", чтобы получился треугольник.");
        if (sideB >= (sideA + sideC))
            throw new CanNotBeTriangleException("sideB = " + sideB + ". " +
                    "Значение слишком велико, длина стороны B должна быть меньше " + (sideA + sideC) +
                    ", чтобы получился треугольник.");
        if (sideC >= (sideA + sideB))
            throw new CanNotBeTriangleException("sideC = " + sideC + ". " +
                    "Значение слишком велико, длина стороны C должна быть меньше " + (sideA + sideB) +
                    ", чтобы получился треугольник.");

        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
}
