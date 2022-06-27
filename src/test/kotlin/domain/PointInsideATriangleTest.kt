package domain

import kotlin.math.abs
import kotlin.random.Random
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

internal class PointInsideATriangleTest {

    private fun rand(from : Int, to : Int): Int {
        return Random.nextInt(to - from) + from
    }

    @Test //Contructor tests
    fun isPoint(): Unit {
        var p1 = Point(3.0f, 4.0f)
        assertIs<Point>(p1,"Failure: not a Point");
        assertEquals(3.0f,p1.x)
        assertEquals(4.0f,p1.y)
    }

    @Test
    fun isLine(): Unit {
        var p1 = Point(3.0f, 4.0f)
        var p2 = Point(5.0f, 6.0f)
        var l1 = Line(p1, p2)
        assertIs<Line>(l1,"Failure: not a Line");
    }

    @Test
    fun isTriangle(): Unit {
        var p1 = Point(3.0f, 4.0f)
        var p2 = Point(5.0f, 6.0f)
        var p3 = Point(4.0f, 8.0f)
        var t1 = Triangle(p1,p2,p3)
        assertIs<Triangle>(t1,"Failure: not a Triangle");
    }

    @Test //Tests a triangle constructed clockwise
    fun basicTriangleClockWise(): Unit {
        var auxP1 = Point(0.0f,0.0f)
        var auxP2 = Point(5.0f,10.0f)
        var auxP3 = Point(10.0f,0.0f)
        var underTestTriangle = Triangle(auxP1, auxP2, auxP3)
        var underTestPoint = Point(5.0f, 5.0f)
        assertEquals(true, underTestTriangle.isInside(underTestPoint),
            "Failure: with a basic Triangle build in ClockWise");
    }

    @Test //Tests a triangle constructed Anticlockwise
    fun basicTriangleAntiClockWise (): Unit {
        var auxP1 = Point(0.0f,0.0f)
        var auxP2 = Point(10.0f,0.0f)
        var auxP3 = Point(5.0f,10.0f)
        var underTestTriangle = Triangle(auxP1, auxP2, auxP3)
        var underTestPoint = Point(5.0f, 5.0f)
        assertEquals(true, underTestTriangle.isInside(underTestPoint),
            "Failure: with a basic Triangle build in AntiClockWise");
    }

    @Test //Tests a series of triangles with a point inside for sure
    fun CenterPointTest(): Unit {
        var underTestPoint = Point(5.0f, 5.0f)
        var auxP3 = Point(10.0f, 5.0f)
        for (i in 0..5) {
            for (j in 6..10) {
                for (l in 0..5) {
                    for (m in 0..5) {
                        var auxP1 = Point(i * 1.0f, j * 1.0f)
                        var auxP2 = Point(l * 1.0f, m * 1.0f)
                        var underTestTriangle = Triangle(auxP1, auxP2, auxP3)
                        var str = "Failure: with Point(%f, %f) and Point(%f, %f)";
                        assertEquals(true, underTestTriangle.isInside(underTestPoint),
                            String.format(str, auxP1.x, auxP1.y, auxP2.x, auxP2.y));
                    }
                }
            }
        }
    }

    @Test //Tests a series of triangles without a point inside for sure
    fun OuterPointTest(): Unit {
        var underTestPoint = Point(10.0f, 10.0f)
        var auxP3 = Point(10.0f, 5.0f)
        for (i in 0..5) {
            for (j in 6..10) {
                for (l in 0..5) {
                    for (m in 0..5) {
                        var auxP1 = Point(i * 1.0f, j * 1.0f)
                        var auxP2 = Point(l * 1.0f, m * 1.0f)
                        var underTestTriangle = Triangle(auxP1, auxP2, auxP3)
                        var str = "Failure: with Point(%f, %f) and Point(%f, %f)";
                        assertEquals(false, underTestTriangle.isInside(underTestPoint),
                            String.format(str, auxP1.x, auxP1.y, auxP2.x, auxP2.y));
                    }
                }
            }
        }
    }

    @Test //Centroid
    fun middlePointBasic(): Unit {
        var underTestLine = Line(Point(0.0f,0.0f), Point(1.0f,2.0f))
        var underTestPoint = Point(0.5f,1.0f)
        assertEquals(underTestPoint.x, underTestLine.middlePoint().x,
            "Failure: point x value is not the same of the middle point")
        assertEquals(underTestPoint.y, underTestLine.middlePoint().y,
            "Failure: point y value is not the same of the middle point")
    }
    @Test
    fun middlePointBasicReverse(): Unit {
        var underTestLine = Line(Point(1.0f,2.0f),Point(0.0f,0.0f))
        var underTestPoint = Point(0.5f,1.0f)
        assertEquals(underTestPoint.x, underTestLine.middlePoint().x,
            "Failure: point x value is not the same of the middle point")
        assertEquals(underTestPoint.y, underTestLine.middlePoint().y,
            "Failure: point y value is not the same of the middle point")
    }

}

