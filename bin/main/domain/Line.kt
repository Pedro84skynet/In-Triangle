package domain

import kotlin.math.abs

class Line (var A: Point, var B: Point) {

    private var dx = B.x - A.x
    private var dy = B.y - A.y

    // line slope. Max float if it is infinity
    fun slope(): Float {
        return if (abs(dx) < 1.0E-8 && (dx > 0)) Float.POSITIVE_INFINITY
        else if (abs(dx) < 1.0E-8 && (dx < 0)) Float.NEGATIVE_INFINITY
        else (dy/dx)
    }

    // verify if a given point is on the line segment
    fun isInLineSegment(C: Point): Boolean {
        if(dx == 0.0f) return ((C.x == A.x) && (abs((C.y - A.y)) < abs(dy)))
        if(dy == 0.0f) return ((C.y == A.y) && (abs((C.x - A.x)) < abs(dx)))
        return ((abs(((C.x - A.x)/dx - (C.y - A.y)/dy)) < 1.0E-5) &&
                (abs(((C.x - A.x)/dx)) < 1.0f))
    }

    // verify if a given point is clockwise relative of the construction
    // line. By arbitrary definition, in line is clockwise
    fun isClockWise(C: Point): Boolean {
        return ((C.x - A.x)*dy > dx*(C.y - A.y))
    }

    fun middlePoint():Point {
        return Point(A.x + (dx/2), A.y +(dy/2))
    }

    fun printLinePoints(): Unit {
        A.printPointCoords()
        B.printPointCoords()
    }
}