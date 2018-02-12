package net.thegaminghuskymc.huskylib.utils.math;

import net.thegaminghuskymc.huskylib.utils.Copyable;

public class Point2i implements Copyable<Point2i> {

    private int x;
    private int y;

    public Point2i(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point2i add(Point2i point){
        this.x += point.x;
        this.y += point.y;
        return this;
    }

    public Point2i add(int x, int y){
        this.x += x;
        this.y += y;
        return this;
    }

    public Point2i multiply(Point2i point){
        this.x *= point.x;
        this.y *= point.y;
        return this;
    }

    public Point2i multiply(int x, int y){
        this.x *= x;
        this.y *= y;
        return this;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public Point2i copy() {
        return new Point2i(this.x, this.y);
    }

}
