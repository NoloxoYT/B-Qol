package com.example;

import net.minecraft.class_2248;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import java.util.Stack;

public class UndoManager {
    private static final Stack<UndoAction> undoStack = new Stack<>();

    public static void pushAction(class_2338 pos, class_2248 oldState, class_1937 world) {
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
        private final class_2338 pos;
        private final class_2248 oldState;
        private final class_1937 world;

        public UndoAction(class_2338 pos, class_2248 oldState, class_1937 world) {
            this.pos = pos;
            this.oldState = oldState;
            this.world = world;
        }

        public void undo() {
            world.setBlockState(pos, oldState, 3);
        }
    }
}