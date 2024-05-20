package me.lutto;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimerScheduler {

    private static class TimerTask {
        int ticksRemaining;
        Runnable action;

        TimerTask(int ticksRemaining, Runnable action) {
            this.ticksRemaining = ticksRemaining;
            this.action = action;
        }
    }

    private final List<TimerTask> tasks = new ArrayList<>();

    public TimerScheduler() {
        ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);
    }

    public void schedule(int delayInTicks, Runnable action) {
        tasks.add(new TimerTask(delayInTicks, action));
    }

    private void onClientTick(MinecraftClient minecraftClient) {
        Iterator<TimerTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            TimerTask task = iterator.next();
            task.ticksRemaining--;
            if (task.ticksRemaining <= 0) {
                task.action.run();
                iterator.remove();
            }
        }
    }

}