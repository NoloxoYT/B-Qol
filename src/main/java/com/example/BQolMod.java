package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BQolMod implements ModInitializer {
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient && player.isCreative() && world.getPlayers().size() == 1) {
                BlockPos pos = hitResult.getBlockPos();
                UndoManager.pushAction(pos, world.getBlockState(pos), (ServerWorld) world);
            }
            return ActionResult.PASS;
        });

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (!world.isClient && player.isCreative() && world.getPlayers().size() == 1) {
                UndoManager.pushAction(pos, state, (ServerWorld) world);
            }
            return true;
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(com.mojang.brigadier.builder.LiteralArgumentBuilder.literal("undo")
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    ServerPlayerEntity player = source.getPlayer();
                    if (player != null && player.isCreative() && player.getServerWorld().getPlayers().size() == 1) {
                        if (UndoManager.canUndo()) {
                            UndoManager.undoLastAction();
                            player.sendMessage(Text.literal("Dernière action annulée !"), false);
                        } else {
                            player.sendMessage(Text.literal("Rien à annuler."), false);
                        }
                    } else {
                        player.sendMessage(Text.literal("Cette commande est uniquement disponible en mode créatif et en solo."), false);
                    }
                    return 1;
                }));
        });
    }
}