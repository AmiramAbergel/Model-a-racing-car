package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IRenderable;
import edu.cg.models.SkewedBox;

public class FrontBumber implements IRenderable {
	// TODO: Add fields as you like (and methods if you think they are necessary).
	private SkewedBox wing_bumper = new SkewedBox(Specification.F_BUMPER_LENGTH,
			Specification.F_BUMPER_WINGS_HEIGHT_1,
			Specification.F_BUMPER_WINGS_HEIGHT_2,
			Specification.F_BUMPER_WINGS_DEPTH,
			Specification.F_BUMPER_WINGS_DEPTH);

	private SkewedBox base_bumper = new SkewedBox(Specification.F_BUMPER_LENGTH,
			Specification.F_BUMPER_HEIGHT_1,
			Specification.F_BUMPER_HEIGHT_2,
			Specification.F_BUMPER_DEPTH,
			Specification.F_BUMPER_DEPTH);

	private BoundingSphere b_Sphere = new BoundingSphere(Specification.F_BUMPER_LENGTH/4,
			new Point(0.0));

	@Override
	public void render(GL2 gl) {
		// TODO: Render the front bumper relative to it's local coordinate system.
		// Remember the dimensions of the bumper, this is important when you
		// combine the bumper with the hood.
		gl.glPushMatrix();

		Materials.SetBlackMetalMaterial(gl);
		base_bumper.render(gl);
		gl.glTranslated(0.0,
				0.0,
				Specification.F_BUMPER_DEPTH * 0.5 + Specification.F_BUMPER_WINGS_DEPTH * 0.5);
		wing_bumper.render(gl);
		b_Sphere.setSphereColore3d(0.7,0,0.1);
		b_Sphere.setCenter(new Point (0));
		b_Sphere.translateCenter(0.0, 0.04, 0.0);
		b_Sphere.render(gl);

		gl.glPopMatrix();
		gl.glPushMatrix();

		Materials.SetBlackMetalMaterial(gl);
		gl.glTranslated(0.0,
				0.0,
				-(Specification.F_BUMPER_DEPTH * 0.5 + Specification.F_BUMPER_WINGS_DEPTH * 0.5));
		wing_bumper.render(gl);
		b_Sphere.render(gl);

		gl.glPopMatrix();
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public String toString() {
		return "FrontBumper";
	}



}

