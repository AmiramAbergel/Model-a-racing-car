package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;
import edu.cg.algebra.Point;
import edu.cg.algebra.Vec;

public class Front implements IRenderable, IIntersectable {
	// TODO: Add necessary fields (e.g. the bumper).
	private FrontHood hood = new FrontHood();
	private PairOfWheels wheels = new PairOfWheels();
	private FrontBumber f_bumper = new FrontBumber(); // FRONT - BUMPER

	@Override
	public void render(GL2 gl) {
		// TODO: Render the BUMPER. Look at how we place the front and the wheels of
		// the car.
		gl.glPushMatrix();

		// Render hood - Use Red Material.
		gl.glTranslated(-Specification.F_LENGTH / 2.0 + Specification.F_HOOD_LENGTH / 2.0, 0.0, 0.0);
		hood.render(gl);
		// Render the wheels.
		gl.glTranslated(Specification.F_HOOD_LENGTH / 2.0 - 1.25 * Specification.TIRE_RADIUS,
				0.5 * Specification.TIRE_RADIUS, 0.0);
		wheels.render(gl);
		// Render Front-bumper
		gl.glTranslated((1.25 * Specification.TIRE_RADIUS) + (Specification.F_BUMPER_LENGTH / 2.0),
				-Specification.TIRE_RADIUS / 2.0,
				0.0);
		f_bumper.render(gl);

		gl.glPopMatrix();
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public List<BoundingSphere> getBoundingSpheres() {
		// TODO: Return a list of bounding spheres the list structure is as follow:
		// s1
		// where:
		// s1 - sphere bounding the car front
		LinkedList<BoundingSphere> res = new LinkedList<BoundingSphere>();
		Point center_P;
		double radius_V;
		BoundingSphere b_sphere_f;

		center_P = new Point(0, Specification.F_HEIGHT * 0.5, 0);
		radius_V = new Vec(Specification.F_LENGTH * 0.5,
				Specification.F_HEIGHT * 0.5,
				Specification.F_DEPTH * 0.5).norm();
		b_sphere_f = new BoundingSphere(radius_V, center_P);
		b_sphere_f.setSphereColore3d(1.0, 0.1, 0.2);
		res.add(b_sphere_f);

		return res;

	}

	@Override
	public String toString() {
		return "CarFront";
	}
}
