package de.feli490.feliutils.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ColorChatComponentBuilder {

    private final TextColor primaryColor;
    private final TextColor secondaryColor;
    private final TextColor errorColor;

    private final TextComponent textComponent;

    public final TextComponent tagComponent;

    public boolean showTag;

    public ColorChatComponentBuilder(TextColor primaryColor, TextColor secondaryColor, TextColor errorColor, String pluginTag,
            TextColor tagColor) {

        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.errorColor = errorColor;

        this.textComponent = Component.text("");
        this.showTag = pluginTag != null;
        this.tagComponent = Component.text(this.showTag ? "[" + pluginTag + "] " : "");
        if (tagColor != null) {
            this.tagComponent.color(tagColor);
        }
    }

    public ColorChatComponentBuilder component(Component component, TextDecoration... decorations) {
        return this.color(null, component, decorations);
    }

    public ColorChatComponentBuilder component(Component component, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(null, component, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder component(Component component, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(null, component, clickEvent, decorations);
    }

    public ColorChatComponentBuilder component(Component component, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(null, component, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder primary(Component component, TextDecoration... decorations) {
        return this.color(this.primaryColor, component, decorations);
    }

    public ColorChatComponentBuilder primary(Component component, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.primaryColor, component, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder primary(Component component, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.primaryColor, component, clickEvent, decorations);
    }

    public ColorChatComponentBuilder primary(Component component, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(this.primaryColor, component, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(Component component, TextDecoration... decorations) {
        return this.color(this.secondaryColor, component, decorations);
    }

    public ColorChatComponentBuilder secondary(Component component, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.secondaryColor, component, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(Component component, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.secondaryColor, component, clickEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(Component component, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(this.secondaryColor, component, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder error(Component component, TextDecoration... decorations) {
        return this.color(this.errorColor, component, decorations);
    }

    public ColorChatComponentBuilder error(Component component, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.errorColor, component, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder error(Component component, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.errorColor, component, clickEvent, decorations);
    }

    public ColorChatComponentBuilder error(Component component, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(this.errorColor, component, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, Component component, TextDecoration... decorations) {
        return this.color(textColor, component, null, null, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, Component component, HoverEvent<?> hoverEvent,
            TextDecoration... decorations) {
        return this.color(textColor, component, hoverEvent, null, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, Component component, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(textColor, component, null, clickEvent, decorations);
    }

    public ColorChatComponentBuilder primary(String text, TextDecoration... decorations) {
        return this.color(this.primaryColor, text, decorations);
    }

    public ColorChatComponentBuilder primary(String text, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.primaryColor, text, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder primary(String text, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.primaryColor, text, clickEvent, decorations);
    }

    public ColorChatComponentBuilder primary(String text, HoverEvent<?> hoverEvent, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.primaryColor, text, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(String text, TextDecoration... decorations) {
        return this.color(this.secondaryColor, text, decorations);
    }

    public ColorChatComponentBuilder secondary(String text, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.secondaryColor, text, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(String text, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.secondaryColor, text, clickEvent, decorations);
    }

    public ColorChatComponentBuilder secondary(String text, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(this.secondaryColor, text, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder error(String text, TextDecoration... decorations) {
        return this.color(this.errorColor, text, decorations);
    }

    public ColorChatComponentBuilder error(String text, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(this.errorColor, text, hoverEvent, decorations);
    }

    public ColorChatComponentBuilder error(String text, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.errorColor, text, clickEvent, decorations);
    }

    public ColorChatComponentBuilder error(String text, HoverEvent<?> hoverEvent, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(this.errorColor, text, hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, String text, TextDecoration... decorations) {
        return this.color(textColor, text, null, null, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, String text, HoverEvent<?> hoverEvent, TextDecoration... decorations) {
        return this.color(textColor, text, hoverEvent, null, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, String text, ClickEvent clickEvent, TextDecoration... decorations) {
        return this.color(textColor, text, null, clickEvent, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, String text, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        return this.color(textColor, Component.text(text), hoverEvent, clickEvent, decorations);
    }

    public ColorChatComponentBuilder color(TextColor textColor, Component component, HoverEvent<?> hoverEvent, ClickEvent clickEvent,
            TextDecoration... decorations) {
        if (textColor != null) {
            component.color(textColor);
        }
        if (hoverEvent != null) {
            component.hoverEvent(hoverEvent);
        }
        if (clickEvent != null) {
            component.clickEvent(clickEvent);
        }
        for (TextDecoration decoration : decorations) {
            component.decorate(decoration);
        }

        this.textComponent.append(component);
        return this;
    }

    public ColorChatComponentBuilder tag() {
        return this.tag(!this.showTag);
    }

    private ColorChatComponentBuilder tag(boolean showTag) {
        this.showTag = showTag;
        return this;
    }

    public ColorChatComponentBuilder send(CommandSender commandSender) {
        commandSender.sendMessage(this.buildComponent());
        return this;
    }

    public ColorChatComponentBuilder sendAllOnlinePlayers() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(this.buildComponent()));
        return this;
    }

    public ColorChatComponentBuilder broadcast() {
        Bukkit.getServer().broadcast(this.buildComponent());
        return this;
    }

    public Component buildComponent() {

        Component component = Component.text("");
        if (this.showTag) {
            component.append(this.tagComponent);
        }

        return component.append(this.textComponent);
    }
}
