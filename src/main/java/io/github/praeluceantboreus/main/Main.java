package io.github.praeluceantboreus.main;

import io.github.praeluceantboreus.fetchers.LocalFetcher;
import io.github.praeluceantboreus.units.Clazz;
import io.github.praeluceantboreus.units.Unit;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main
{

	public static void main(String[] args)
	{
		Clazz klasse = (Clazz) LocalFetcher.fetch(new File("/home/richi/c00050.htm"));
		for (Unit u : klasse.getUnits())
		{
			System.out.println(u.getName());
			System.out.println(u.getTeacher());
			System.out.println(u.getRoom());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(u.getStart());
			System.out.println(gc.getDisplayName(GregorianCalendar.DAY_OF_WEEK, 1, Locale.GERMAN));
			System.out.println();
			System.out.println();
		}
	}

}
