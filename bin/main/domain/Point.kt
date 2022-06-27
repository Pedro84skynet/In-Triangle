package domain

class Point(var x: Float, var y: Float) {
    fun printPointCoords() {
        println("x:${this.x} y:${this.y}")
    }
}