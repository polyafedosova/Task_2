package ru.vsu.fedosova;

import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        Line l1 = readLine("l1");
        Line l2 = readLine("l2");

        LineTypes type = getTypes(l1, l2);
        PointIntersect point = calsPoint(l1, l2);

        printType(type);
        printPointIntersect(point);
    }

    static double readCoefficients(String points) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %s: ", points);
        return scanner.nextDouble();
    }

    static Line readLine(String line) {
        double a = readCoefficients(String.format("%s.a", line));
        double b = readCoefficients(String.format("%s.b", line));
        double c = readCoefficients(String.format("%s.c", line));

        return new Line(a, b, c);
    }

    static boolean isLinesParallel(Line l1, Line l2) {
        return l1.a / l2.a == l1.b / l2.b;
    }

    static boolean isLineMatch(Line l1, Line l2) {
        return isLinesParallel(l1, l2) && l1.c / l2.c == l1.b / l2.b;
    }

    static PointIntersect calsPoint(Line l1, Line l2)
    {
        double x = (l1.b*l2.c-l2.b*l1.c) / (l1.a*l2.b-l1.b*l2.a);
        double y = (l1.c*l2.a-l2.c*l1.a) / (l1.a*l2.b-l1.b*l2.a);
        PointIntersect point = new PointIntersect(x, y);
        return point;
    }

    static LineTypes getTypes(Line l1, Line l2) {
        if (isLinesParallel(l1, l2)) return LineTypes.Lines_Parallel;
        if (isLineMatch(l1, l2)) return LineTypes.Lines_Match;
        return LineTypes.Lines_Intersect;
    }

    static void printType(LineTypes type)
    {
        System.out.println(type);
    }

    static void printPointIntersect(PointIntersect point)
    {
        System.out.printf("Point Intersect = A(%1$.0f; %2$.0f)", point.x, point.y);
    }
}