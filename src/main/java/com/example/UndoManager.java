package com.example;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import java.util.Stack;

public class UndoManager {
    private static final Stack<UndoAction> undoStack = new Stack<>();

    public static void pushAction(BlockPos pos, BlockState oldState, ServerWorld world) {
        undoStack.push(new UndoAction(pos, oldState, world));
    }

    public static void undoLastAction() {
        if (!undoStack.isEmpty()) {
            UndoAction action = undoStack.pop();
            action.undo();
        }
    }

    public static boolean canUndo() {
        return !undoStack.isEmpty();
    }

    private static class UndoAction {
        private final BlockPos pos;
        private final BlockState oldState;
        private final ServerWorld world;

        public UndoAction(BlockPos pos, BlockState oldState, ServerWorld world) {
            this.pos = pos;
            this.oldState = oldState;
            this.world = world;
        }

        public void undo() {
            world.setBlockState(pos, oldState, 3);
        }
    }
}