package me.lutto.accessor;

public interface SchedulerAccessor {
    void runTaskLater(long ticksUntilSomething, Runnable runnable);
}
