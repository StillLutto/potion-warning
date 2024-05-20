package me.lutto;

public interface SchedulerAccessor {
    void runTaskLater(long ticksUntilSomething, Runnable runnable);
}
