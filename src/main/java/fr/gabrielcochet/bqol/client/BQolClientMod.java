package fr.gabrielcochet.bqol.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class BQolClientMod implements ClientModInitializer {
    private static KeyBinding notepadKey;
    private static KeyBinding undoKey;

    @Override
    public void onInitializeClient() {
        notepadKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.bqol.notepad",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.bqol.general"
        ));
        undoKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.bqol.undo",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "category.bqol.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (notepadKey.wasPressed()) {
                // TODO: Open notepad GUI
                System.out.println("Notepad opened!");
            }
            while (undoKey.wasPressed()) {
                // TODO: Undo last action
                System.out.println("Undo action!");
            }
        });
    }
}