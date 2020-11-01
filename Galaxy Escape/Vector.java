/**
 * Write a description of class Vector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector  
{
    public double x, y;
    public Vector(double a, double b) { x = a; y = b; }
    public Vector(double theta) { x = Math.cos(theta); y = Math.sin(theta); } // radians
    public double dot(Vector other) { return this.x * other.x + this.y * other.y; }
    public double abs() { return Math.sqrt(x*x + y*y); }
    public Vector scale(double c) { return new Vector(x*c, y*c); }
    public Vector proj(Vector other) { // note this projects onto the other vector
        return other.scale(this.dot(other) / Math.pow(other.abs(), 2));
    }
    public double angle(Vector other) { return Math.acos(this.dot(other) / (this.abs() * other.abs())); }
    public Vector unit() { return new Vector(x/abs(), y/abs()); }
    public String toString() { return String.format("<%.2f, %.2f>", x, y); }
}
