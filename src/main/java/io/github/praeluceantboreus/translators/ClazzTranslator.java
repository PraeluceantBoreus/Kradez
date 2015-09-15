package io.github.praeluceantboreus.translators;

import io.github.praeluceantboreus.units.Clazz;
import io.github.praeluceantboreus.units.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ClazzTranslator implements Translator<Clazz>
{

	private GregorianCalendar day;

	public ClazzTranslator()
	{
		day = new GregorianCalendar();
		day.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		day.add(GregorianCalendar.DAY_OF_WEEK, -1);
	}

	public Clazz generate(ArrayList<String> lines)
	{
		String name = lines.get(16);
		String kv = lines.get(20);
		Clazz ret = new Clazz(kv, name);

		ArrayList<String> copy = new ArrayList<>(lines);
		Iterator<String> iterator = copy.iterator();
		boolean remHeader = true;
		while (iterator.hasNext() && remHeader)
			if (!iterator.next().contains("table"))
				iterator.remove();
			else
				remHeader = false;
		StringBuilder sb = new StringBuilder();
		for (String string : copy)
			sb.append(string);
		ArrayList<String> splitted = new ArrayList<>(Arrays.asList(sb.toString().split("<TD")));
		for (int i = 0; i < 17; i++)
			splitted.remove(0);
		iterator = splitted.iterator();
		while (iterator.hasNext())
			if (iterator.next().contains(":"))
				iterator.remove();
		splitted.remove(0);
		ArrayList<String> faceSplitter = new ArrayList<>();
		for (String string : splitted)
		{
			String[] arr = string.split("face=\"Arial\">");
			faceSplitter.add(arr[arr.length - 1]);
		}
		ArrayList<String> triSplit = new ArrayList<>();
		for (String string : faceSplitter)
		{
			String[] arr = string.split("<");
			triSplit.add(arr[0]);
		}
		ArrayList<String> buffer = new ArrayList<>();
		for (String string : triSplit)
		{
			if (!string.contains("="))
				buffer.add(string);
			if (buffer.size() >= 3)
			{
				ret.addUnit(new Unit(0, 0, buffer.get(0), buffer.get(2), buffer.get(1)));
				buffer.removeAll(buffer);
			}
		}
		System.out.println(triSplit);
		return ret;
	}

	public Clazz generateNew(ArrayList<String> lines)
	{
		String name = lines.get(16);
		String kv = lines.get(20);
		Clazz ret = new Clazz(kv, name);

		try
		{
			Document doc = Jsoup.connect("https://intranet.spengergasse.at/infostundenplan/38/c/c00050.htm").get();
			// System.out.println(doc);
			print(doc.body(), ret);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// while (true)
		// System.out.println(nextDay().getDisplayName(GregorianCalendar.DAY_OF_WEEK,
		// 0, Locale.GERMAN));
		return ret;
	}

	public void print(Element element, Clazz ret)
	{
		ArrayList<Element> buffer = new ArrayList<>();
		for (Element e : element.getAllElements())
		{
			if (e.children().size() == 0)
			{
				// System.out.println(e.text());
				buffer.add(e);
			}
			/*
			 * if (buffer.size() >= 3)
			 * {
			 * if (buffer.get(2).text().contains(".") || isEmpty(buffer))
			 * ret.addUnit(new Unit(nextDay().getTimeInMillis(), 0,
			 * buffer.get(0).text(), buffer.get(1).text(),
			 * buffer.get(2).text()));
			 * buffer.removeAll(buffer);
			 * }
			 */
		}
		for (int i = 0; i < buffer.size(); i++)
		{
			// System.out.println(buffer.get(i).text());
			if (ret.getUnits().size() > 0 && i < buffer.size() - 2 && (!buffer.get(i).hasText() && buffer.get(i - 1).text().contains(".")) || i >= 19 && (buffer.get(i - 19).parent().parent().parent().parent().attr("rowspan").equalsIgnoreCase("4")))
			{
				increaseDay();
				System.out.println("inced");
			}
			if (buffer.get(i).text().contains("."))
				ret.addUnit(new Unit(nextDay().getTimeInMillis(), 0, buffer.get(i - 2).text(), buffer.get(i - 1).text(), buffer.get(i).text()));
		}
	}

	public static boolean isEmpty(ArrayList<Element> elements)
	{
		for (Element el : elements)
			if (el.hasText())
				return false;
		return true;
	}

	public GregorianCalendar nextDay()
	{
		increaseDay();
		if (day.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY)
			return nextDay();
		return (GregorianCalendar) day.clone();
	}

	public void increaseDay()
	{
		day.add(GregorianCalendar.DAY_OF_WEEK, 1);
	}
}
