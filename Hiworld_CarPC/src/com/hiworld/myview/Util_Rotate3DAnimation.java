package com.hiworld.myview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Util_Rotate3DAnimation extends Animation {

	private final float mFromDegrees;
	private final float mToDegrees;
	private final float mCenterX;
	private final float mCenterY;
	private final float mDepthZ;
	private final boolean mReverse;
	private Camera mCamera;
	 
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		
		super.applyTransformation(interpolatedTime, t);
		final float fromDegrees = mFromDegrees;
		float degrees = fromDegrees
		  + ((mToDegrees - fromDegrees) * interpolatedTime);
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;
		final Matrix matrix = t.getMatrix();
		camera.save();
		if (mReverse) {
		camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
		} else {
	    camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
	    }
		camera.rotateY(degrees);//以y轴旋转
		camera.getMatrix(matrix);
		camera.restore();
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		
		super.initialize(width, height, parentWidth, parentHeight);
		 mCamera = new Camera();
	}

	public Util_Rotate3DAnimation(float fromDegrees, float toDegrees,
	   float centerX, float centerY, float depthZ, boolean reverse) {
	  this.mFromDegrees = fromDegrees;
	  this.mToDegrees = toDegrees;
	  this.mCenterX = centerX;
	  this.mCenterY = centerY;
	  this.mDepthZ = depthZ;
	  this.mReverse = reverse;
	 }
}
