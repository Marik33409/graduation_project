package ru.bmstu.processing.repository;

public interface BlockRepository<T, Id> {
    void saveBlock(T client);
    void deleteBlock(T client);
    void updateBlock(T client);
    T getBlockById(Id id);
}
