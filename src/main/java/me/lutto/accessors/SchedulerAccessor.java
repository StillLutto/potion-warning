package me.lutto.accessors;

public interface SchedulerAccessor {
    void runTaskLater(long ticksUntilSomething, Runnable runnable);
}
