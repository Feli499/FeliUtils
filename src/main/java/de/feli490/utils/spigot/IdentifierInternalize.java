package de.feli490.utils.spigot;

public class IdentifierInternalize {

    public static String internalize(String str) {
        return str.trim().replace("/", "").replace("\\", "").replace(" ", "_").toLowerCase();
    }
}
