package io.github.praeluceantboreus.units;

import java.util.GregorianCalendar;

public class Unit implements Translateable
{
	private long start, end;
	private String name, teacher, room;

	public Unit(long start, long end, String name, String teacher, String room)
	{
		super();
		this.start = start;
		this.end = end;
		this.name = name;
		this.teacher = teacher;
		this.room = room;
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
		return teacher;
	}

	public String getTeacher()
	{
		return room;
	}

	@Override
	public String toString()
	{
		return "Unit [start=" + start + ", end=" + end + ", name=" + name + ", teacher=" + teacher + ", room=" + room + "]";
	}
}
