package io.github.praeluceantboreus.translators;

import java.util.ArrayList;

import io.github.praeluceantboreus.units.Clazz;

public class ClazzTranslator implements Translator<Clazz>
{

	public Clazz generate(ArrayList<String> lines)
	{
		String name = lines.get(16);
		String kv = lines.get(20);
		return new Clazz(kv, name);
	}

}
