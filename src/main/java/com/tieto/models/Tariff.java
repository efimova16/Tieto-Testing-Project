package com.tieto.models;

public class Tariff {

	private int time;
	private String speed;
	private int cost;

	public Tariff() {
	}

	public Tariff(int time, String speed, int cost) {
		this.time = time;
		this.speed = speed;
		this.cost = cost;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setTime(String time) {
		this.time = Integer.parseInt(time);
	}

	public void setCost(String cost) {
		this.cost = Integer.parseInt(cost);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Tariff))
			return false;
		Tariff tariff = (Tariff) o;
		if (Double.compare(tariff.cost, cost) != 0)
			return false;
		if (Integer.compare(tariff.time, time) != 0)
			return false;
		if (!speed.equals(tariff.speed))			
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Tariff {time=" + time + ", speed=" + speed + ", cost=" + cost
				+ "}";
	}

}
