import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;

public abstract class CollisionFrame extends BoundableEntityFrame {
	
	public CollisionEngine engine;

	public CollisionFrame(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public CollisionFrame(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public CollisionFrame(Rectangle2D.Double bounds) {
		super(bounds);
	}

	public CollisionFrame(Rectangle2D.Double bounds, Color color) {
		super(bounds, color);
	}

	public void update() {
		super.update();
		engine.update();
	}

	public void draw(Graphics g) {
		super.draw(g);
		engine.draw(g);
	}
}