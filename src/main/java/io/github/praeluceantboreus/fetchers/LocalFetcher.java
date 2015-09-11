package io.github.praeluceantboreus.fetchers;

import io.github.praeluceantboreus.translators.ClazzTranslator;
import io.github.praeluceantboreus.units.Translateable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LocalFetcher
{
	public static Translateable fetch(File file)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			ArrayList<String> lines = new ArrayList<>();
			lines.add("");
			String line;
			while ((line = br.readLine()) != null)
				lines.add(line);
			return new ClazzTranslator().generateNew(lines);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
