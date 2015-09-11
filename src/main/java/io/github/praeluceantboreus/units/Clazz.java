package io.github.praeluceantboreus.units;

import java.util.ArrayList;

public class Clazz implements Translateable
{
	ArrayList<Unit> units;
	private String kv, name;

	public Clazz(String kv, String name)
	{
		units = new ArrayList<Unit>();
		this.kv = kv;
		this.name = name;
	}

	public void addUnit(Unit unit)
	{
		units.add(unit);
	}

	public String getKv()
	{
		return kv;
	}

	@Override
	public String toString()
	{
		return "Clazz [units=" + units + ", kv=" + kv + ", name=" + name + "]";
	}
}
