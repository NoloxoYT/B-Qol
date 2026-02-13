package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.class_217;
import net.minecraft.class_3222;
import net.minecraft.class_2561;
import net.minecraft.class_1268;
import net.minecraft.class_1265;
import net.minecraft.class_1472;
import net.minecraft.class_2338;
import net.minecraft.class_1937;

public class BQolMod implements ModInitializer {
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world instanceof class_1937 && ((class_3222) player).isCreative() && ((class_1937) world).getPlayers().size() == 1) {
                class_2338 pos = ((class_1472) hitResult).getBlockPos();
                UndoManager.pushAction(pos, world.getBlockState(pos), (class_1937) world);
            }
            return class_1268.PASS;
        });

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof class_1937 && ((class_3222) player).isCreative() && ((class_1937) world).getPlayers().size() == 1) {
                UndoManager.pushAction(pos, state, (class_1937) world);
            }
            return true;
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                LiteralArgumentBuilder.<class_217>literal("undo")
                    .executes(context -> {
                        class_217 source = context.getSource();
                        class_3222 player = source.getPlayer();
                        if (player != null && player.isCreative() && player.getServerWorld().getPlayers().size() == 1) {
                            if (UndoManager.canUndo()) {
                                UndoManager.undoLastAction();
                                player.sendMessage(class_2561.of("Dernière action annulée !"));
                            } else {
                                player.sendMessage(class_2561.of("Rien à annuler."));
                            }
                        } else {
                            player.sendMessage(class_2561.of("Cette commande est uniquement disponible en mode créatif et en solo."));
                        }
                        return 1;
                    })
            );
        });
    }
}