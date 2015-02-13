package com;

/**
 * 例子来自于Effective Java
 * 
 * 八大行星
 */
public enum Planet {

	MERCURY(3.302e+23, 2.439e6),
	VENUS(4.869e24, 6.052e6),
	EARTH(5.975e24, 6.378e6); // 下面的省略了
	
	@SuppressWarnings("unused")
	private final double mass; // 质量 kg
	@SuppressWarnings("unused")
	private final double radius; // 半径 米
	private final double surfaceGravity; // m / s^2
	
	// 宇宙引力常数，单位m^3 / kg s^2
	private static final double G = 6.67300E-11;
	
	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		this.surfaceGravity = G * mass / (radius * radius);
	}
	
	// 星球表面重力 F = ma
	public double surfaceWeight(double mass) {
		return mass * surfaceGravity; // F = ma
	}
	
	// unit test
	public static void main(String[] args) {
		double mass = 1;
		for(Planet p : Planet.values()) {
			System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
		}
	}
}
