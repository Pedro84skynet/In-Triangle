package domain

class Triangle(var A: Point, var B: Point, var C: Point) {

    private var AB = Line(A,B)
    private var BC = Line(B,C)
    private var CA = Line(C,A)

    fun isInside(C: Point): Boolean {

        if (AB.isInLineSegment(C)) return true
        if (BC.isInLineSegment(C)) return true
        if (CA.isInLineSegment(C)) return true

        return ((AB.isClockWise(C) &&
                 BC.isClockWise(C) &&
                 CA.isClockWise(C)) ||
                (!AB.isClockWise(C) &&
                 !BC.isClockWise(C) &&
                 !CA.isClockWise(C)))
    }

    fun printTrianglePoints(): Unit {
        A.printPointCoords()
        B.printPointCoords()
        C.printPointCoords()
    }

//    fun centroid(): Point{
//
//    }
}