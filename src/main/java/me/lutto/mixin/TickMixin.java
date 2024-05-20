package me.lutto.mixin;

import me.lutto.accessors.SchedulerAccessor;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class TickMixin implements SchedulerAccessor {

    @Unique
    private long ticksUntilSomething;

    @Unique
    private Runnable runnable;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo callbackInfo) {
        if (--this.ticksUntilSomething == 0L) {
            runnable.run();
        }
    }

    @Override
    public void runTaskLater(long ticksUntilSomething, Runnable runnable) {
        this.ticksUntilSomething = ticksUntilSomething;
        this.runnable = runnable;
    }
}