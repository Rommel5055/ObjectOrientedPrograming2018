package exercise14;

import java.awt.Point;
import java.awt.Rectangle;

public class Aliasing {
	public static void printPoint(Point p) {
		System.out.println("(" + p.x + ", " + p.y + ")");
	}
	
	public static Point findCenter(Rectangle box) {
		int x = box.x + box.width / 2;
		int y = box.y + box.height / 2;
		return new Point(x, y);
	}

	public static void main(String[] args) {
		Rectangle box1 = new Rectangle(2, 4, 7, 9);
		Point p1 = findCenter(box1);
		printPoint(p1);
		box1.grow(1, 1);
		Point p2 = findCenter(box1);
		printPoint(p2);
	}
}


/*
2) Output:
(5, 8)
(5, 8)

3) No, they p1 and p2 are not aliaseded because they were assigned the value and don't aim exactly to the rectangle,
but they have the same value because the center is still the same after the grow method is called.
*/
 