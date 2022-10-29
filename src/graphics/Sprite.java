package graphics;

import java.awt.Image;

public class Sprite {

	private Animation anim;
	// position (pixels)
	private float x;
	private float y;
	// velocity (pixels per millisecond)
	private float dx;
	private float dy;
	// acceleration
	private float ax;
	private float ay;

	/**
        Creates a new Sprite object with the specified Animation.
	 */
	public Sprite(Animation anim) {
		this.anim = anim;
	}

	/**
        Updates this Sprite's Animation and its position based
        on the velocity.
	 */
	public void update(long elapsedTime) {
		x += dx * elapsedTime;
		y += dy * elapsedTime;
		anim.update(elapsedTime);
	}

	/**
        Gets this Sprite's current x position.
	 */
	public float getX() {
		return x;
	}

	/**
        Gets this Sprite's current y position.
	 */
	public float getY() {
		return y;
	}

	/**
        Sets this Sprite's current x position.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
        Sets this Sprite's current y position.
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
        Gets this Sprite's width, based on the size of the
        current image.
	 */
	public int getWidth() {
		return anim.getImage().getWidth(null);
	}

	/**
        Gets this Sprite's height, based on the size of the
        current image.
	 */
	public int getHeight() {
		return anim.getImage().getHeight(null);
	}

	/**
        Gets the horizontal velocity of this Sprite in pixels
        per millisecond.
	 */
	public float getVelocityX() {
		return dx;
	}

	/**
        Gets the vertical velocity of this Sprite in pixels
        per millisecond.
	 */
	public float getVelocityY() {
		return dy;
	}

	/**
        Sets the horizontal velocity of this Sprite in pixels
        per millisecond.
	 */
	public void setVelocityX(float dx) {
		this.dx = dx;
	}

	/**
        Sets the vertical velocity of this Sprite in pixels
        per millisecond.
	 */
	public void setVelocityY(float dy) {
		this.dy = dy;
	}

	/**
    Gets the horizontal acceleration of this Sprite in pixels
    per millisecond.
	 */
	public float getAccelX() {
		return ax;
	}

	/**
    Gets the vertical acceleration of this Sprite in pixels
    per millisecond.
	 */
	public float getAccelY() {
		return ay;
	}

	/**
    Sets the horizontal acceleration of this Sprite in pixels
    per millisecond.
	 */
	public void setAccelX(float dx) {
		this.ax = dx;
	}

	/**
    Sets the vertical acceleration of this Sprite in pixels
    per millisecond.
	 */
	public void setAccelY(float dy) {
		this.ay = dy;
	}

	/**
        Gets this Sprite's current image.
	 */
	public Image getImage() {
		return anim.getImage();
	}

}