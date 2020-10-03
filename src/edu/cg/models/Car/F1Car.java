package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.*;

import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;

/**
 * A F1 Racing Car.
 *
 */
public class F1Car implements IRenderable, IIntersectable {
	// TODO : Add new design features to the car.
	// Remember to include a ReadMe file specifying what you implemented.
	Center carCenter = new Center ( );
	Back carBack = new Back ( );
	Front carFront = new Front ( );

	@Override
	public void render(GL2 gl) {
		carCenter.render (gl);
		gl.glPushMatrix ( );
		gl.glTranslated (-Specification.B_LENGTH / 2.0 - Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
		carBack.render (gl);
		gl.glPopMatrix ( );
		gl.glPushMatrix ( );
		gl.glTranslated (Specification.F_LENGTH / 2.0 + Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
		carFront.render (gl);
		gl.glPopMatrix ( );

	}

	@Override
	public String toString() {
		return "F1Car";
	}

	@Override
	public void init(GL2 gl) {

	}

	@Override
	public List < BoundingSphere > getBoundingSpheres() {
		// TODO: Return a list of bounding spheres the list structure is as follow:
		// s1 -> s2 -> s3 -> s4
		// where:
		// s1 - sphere bounding the whole car
		// s2 - sphere bounding the car front
		// s3 - sphere bounding the car center
		// s4 - sphere bounding the car back
		//
		// * NOTE:
		// All spheres should be adapted so that they are place relative to
		// the car model coordinate system.
		LinkedList < BoundingSphere > res = new LinkedList < BoundingSphere > ( );

		//////////////////// s1 - sphere bounding the whole car ////////////////////

		Point cent;
		BoundingSphere boundingSphere;
		cent = new Point (0, Specification.B_HEIGHT / 2.0, 0);
		boundingSphere = new BoundingSphere (Specification.F_LENGTH + Specification.F_BUMPER_LENGTH, cent);
		boundingSphere.setSphereColore3d (0.0, 0.0, 0.0);
		res.add (boundingSphere);

		//////////////////// s2 - sphere bounding the car front ////////////////////

		double front_trans;
		BoundingSphere f_sphere;
		f_sphere = carFront.getBoundingSpheres().get(0);
		front_trans = (Specification.F_LENGTH / 2.0) + (Specification.C_BASE_LENGTH / 2.0);
		f_sphere.translateCenter(front_trans, 0.0, 0.0);
		res.add (f_sphere);

		//////////////////// s3 - sphere bounding the car center ////////////////////

		BoundingSphere c_sphere;
		c_sphere = carCenter.getBoundingSpheres().get(0);
		c_sphere.setSphereColore3d (0,1,0);
		res.add (c_sphere);

		//////////////////// s4 - sphere bounding the car back ////////////////////

		double back_trans;
		BoundingSphere b_sphere;
		b_sphere = carBack.getBoundingSpheres().get(0);
		back_trans = (-Specification.B_LENGTH / 2.0) - (Specification.C_BASE_LENGTH / 2.0);
		b_sphere.translateCenter (back_trans, 0.0, 0.0);
		b_sphere.setSphereColore3d (0,0,1);
		res.add (b_sphere);

		///////////////////////////////////////////////////////////////////////////

		return res;
	}
}
