package de.feli490.feliutils.chat;

import net.kyori.adventure.text.format.TextColor;

public class PluginColorMessenger {

    private final TextColor primaryColor;
    private final TextColor secondaryColor;
    private final TextColor errorColor;
    private final String pluginTag;
    private final TextColor tagColor;

    public PluginColorMessenger(TextColor primaryColor, TextColor secondaryColor, TextColor errorColor, String pluginTag,
            TextColor tagColor) {

        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.errorColor = errorColor;
        this.pluginTag = pluginTag;
        this.tagColor = tagColor;
    }

    public PluginColorMessenger(TextColor primaryColor, TextColor secondaryColor, TextColor errorColor, String pluginTag) {
        this(primaryColor, secondaryColor, errorColor, pluginTag, null);
    }

    public PluginColorMessenger(TextColor primaryColor, TextColor secondaryColor, TextColor errorColor) {
        this(primaryColor, secondaryColor, errorColor, null);
    }

    public ColorChatComponentBuilder createBuilder() {
        return new ColorChatComponentBuilder(this.primaryColor, this.secondaryColor, this.errorColor, this.pluginTag, this.tagColor);
    }

    public TextColor getErrorColor() {
        return this.errorColor;
    }

    public TextColor getPrimaryColor() {
        return this.primaryColor;
    }

    public TextColor getSecondaryColor() {
        return this.secondaryColor;
    }

    public TextColor getTagColor() {
        return this.tagColor;
    }

    public String getPluginTag() {
        return this.pluginTag;
    }
}
