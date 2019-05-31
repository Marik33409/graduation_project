package ru.bmstu.processing.repository.implementation;


import ru.bmstu.processing.entity.Block;
import ru.bmstu.processing.repository.BlockRepository;

public class BlockRepositoryImplementation extends SessionWorker implements BlockRepository<Block, Integer> {
    @Override
    public void saveBlock(Block Block) {
        getCurrentSession().save(Block);
    }

    @Override
    public void deleteBlock(Block Block) {
        getCurrentSession().delete(Block);
    }

    @Override
    public void updateBlock(Block Block) {
        getCurrentSession().update(Block);
    }

    @Override
    public Block getBlockById(Integer id) {
        return (Block) getCurrentSession().get(Block.class, id);
    }
}