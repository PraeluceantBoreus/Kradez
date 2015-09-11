package io.github.praeluceantboreus.translators;

import io.github.praeluceantboreus.units.Clazz;
import io.github.praeluceantboreus.units.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ClazzTranslator implements Translator<Clazz>
{

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
			Document doc = Jsoup.connect("https://intranet.spengergasse.at/infostundenplan/38/c/c00075.htm").get();
			// System.out.println(doc);
			print(doc.body(), ret);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public void print(Element element, Clazz ret)
	{
		ArrayList<String> buffer = new ArrayList<>();
		for (Element e : element.getAllElements())
		{
			if (e.children().size() == 0 && e.hasText())
			{
				// System.out.println(e.text());
				buffer.add(e.text());
			}
			if (buffer.size() >= 3)
			{
				if (buffer.get(2).contains("."))
					ret.addUnit(new Unit(0, 0, buffer.get(0), buffer.get(2), buffer.get(1)));
				buffer.removeAll(buffer);
			}
		}
	}
}
