package io.github.praeluceantboreus.units;

import java.util.GregorianCalendar;

public class Unit implements Translateable
{
	private long start, end;
	private String name, room, teacher;

	public Unit(long start, long end, String name, String room, String teacher)
	{
		super();
		this.start = start;
		this.end = end;
		this.name = name;
		this.room = room;
		this.teacher = teacher;
	}

	public long getStart()
	{
		return start;
	}

	public GregorianCalendar getStartAsObject()
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(getStart());
		return gc;
	}

	public long getEnd()
	{
		return end;
	}

	public GregorianCalendar getEndAsObject()
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(getEnd());
		return gc;
	}

	public String getName()
	{
		return name;
	}

	public String getRoom()
	{
		return room;
	}

	public String getTeacher()
	{
		return teacher;
	}

	@Override
	public String toString()
	{
		return "Unit [start=" + start + ", end=" + end + ", name=" + name + ", room=" + room + ", teacher=" + teacher + "]";
	}
}
