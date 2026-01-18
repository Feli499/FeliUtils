package de.feli490.utils.spigot.inventory.textinput;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.feli490.utils.spigot.inventory.ActionInventory;
import de.feli490.utils.spigot.inventory.textinput.validator.TextValidator;
import de.feli490.utils.spigot.items.DefaultSkulls;

public class TextInputInventory {

    private final JavaPlugin javaPlugin;
    private final AnvilInventory anvilInventory;
    private final TextInputResult textInputResult;
    private final ActionInventory actionInventory;
    private final String confirmText;
    private final String notValidText;

    private final Set<TextValidator> textValidatorSet = new HashSet<>();

    private TextInputInventory(JavaPlugin javaPlugin, AnvilInventory anvilInventory, TextInputResult textInputResult, String confirmText,
            String notValidText, ActionInventory actionInventory) {

        this.javaPlugin = javaPlugin;

        this.anvilInventory = anvilInventory;
        this.textInputResult = textInputResult;
        this.actionInventory = actionInventory;

        this.confirmText = confirmText;
        this.notValidText = notValidText;
    }

    public static TextInputInventory createTextInputInventory(JavaPlugin javaPlugin, HumanEntity humanEntity,
            TextInputResult textInputResult, String whatToDoTitle, Collection<String> whatTodoDescription, String confirmText,
            String notValidText) {
        return createTextInputInventory(javaPlugin, humanEntity, textInputResult, whatToDoTitle, whatTodoDescription, confirmText,
                notValidText, null);
    }

    public static TextInputInventory createTextInputInventory(JavaPlugin javaPlugin, HumanEntity humanEntity,
            TextInputResult textInputResult, String whatToDoTitle, Collection<String> whatTodoDescription, String confirmText,
            String notValidText, ActionInventory actionInventory) {

        ItemStack itemStack = DefaultSkulls.QUESTION_MARK.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(whatToDoTitle);

        List<String> lore = new ArrayList<>(whatTodoDescription);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        InventoryView inventoryView = humanEntity.openAnvil(humanEntity.getLocation(), true);

        AnvilInventory anvilInventory = (AnvilInventory) inventoryView.getTopInventory();

        anvilInventory.setFirstItem(itemStack);
        anvilInventory.setMaximumRepairCost(0);

        TextInputInventory textInputInventory = new TextInputInventory(javaPlugin, anvilInventory, textInputResult, confirmText,
                notValidText, actionInventory);

        TextInputInventoryContainer.getInstance().addTextInputInventory(textInputInventory);

        return textInputInventory;
    }

    public ActionInventory getActionInventory() {
        return actionInventory;
    }

    public TextInputResult getTextInputResult() {
        return textInputResult;
    }

    public AnvilInventory getAnvilInventory() {
        return anvilInventory;
    }

    public ItemStack getResultItemStack(String newText) {

        Set<TextValidator> failedValidators = validate(newText);

        ItemStack itemStack;
        if (failedValidators.isEmpty())
            itemStack = confirmItemStack();
        else
            itemStack = notValidItemStack(failedValidators);

        return itemStack;
    }

    public void confirmClick() {

        if (!validate().isEmpty())
            return;

        String renameText = getRenameText();
        closeInventory();

        javaPlugin.getServer().getScheduler().scheduleSyncDelayedTask(javaPlugin, () -> textInputResult.textResult(renameText), 1L);
    }

    private void closeInventory() {
        clearInventory();
        anvilInventory.close();
    }

    public void clearInventory() {
        anvilInventory.clear();
    }

    public void addTextValidator(TextValidator textValidator) {

        textValidatorSet.add(textValidator);
    }

    private ItemStack confirmItemStack() {

        ItemStack itemStack = DefaultSkulls.GREEN_CHECKMARK.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(MessageFormat.format("{0}{1}", ChatColor.GREEN, confirmText));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack notValidItemStack(Set<TextValidator> failedValidators) {

        ItemStack itemStack = DefaultSkulls.RED_CROSS.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(MessageFormat.format("{0}{1}", ChatColor.RED, notValidText));

        List<String> lore = new ArrayList<>();
        failedValidators
                .forEach(textValidator -> lore.add(MessageFormat.format("{0} - {1}", ChatColor.WHITE, textValidator.getErrorText())));

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private Set<TextValidator> validate() {
        return validate(getRenameText());
    }

    private Set<TextValidator> validate(String renameText) {

        Set<TextValidator> failedValidators = new HashSet<>();

        for (TextValidator textValidator : textValidatorSet) {
            if (!textValidator.validate(renameText))
                failedValidators.add(textValidator);
        }

        return failedValidators;
    }

    private String getRenameText() {

        String renameText = anvilInventory.getRenameText();
        if (renameText == null)
            renameText = "";

        return renameText;
    }

    public interface TextInputResult {

        void textResult(String result);

    }
}
