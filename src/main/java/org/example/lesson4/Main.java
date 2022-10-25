package org.example.lesson4;

public class Main {
    public static void main(String[] args)
            throws SideEqualZeroException, LengthIsNegativeException, CanNotBeTriangleException {

        Triangle triangle = new Triangle(2, 2, 5);
        System.out.println("Площадь треугольника равна " + triangle.countSquare());
    }
}
