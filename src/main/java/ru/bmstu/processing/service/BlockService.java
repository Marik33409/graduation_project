package ru.bmstu.processing.service;


import lombok.val;
import ru.bmstu.processing.entity.Block;
import ru.bmstu.processing.entity.MyMap;
import ru.bmstu.processing.repository.implementation.BlockRepositoryImplementation;

import java.util.*;

public class BlockService {
    private static BlockRepositoryImplementation BlockRepository;

    public BlockService() {
        BlockRepository = new BlockRepositoryImplementation();
    }

    public void create(ru.bmstu.processing.models.Block inputBlock) {
        Block block = new Block();
        block.setDescription(inputBlock.getDescription());
        block.setExperiment_id(inputBlock.getExperimentId());
        Map<String, List<Double>> tmp = inputBlock.getParamsMap();

        Set<MyMap> mySetMap = new HashSet<>();
        for (val entry : tmp.entrySet()) {
            MyMap myMap = new MyMap();
            myMap.setKey(entry.getKey());

            List<Double> list = entry.getValue();
            myMap.setValue(list.toArray(new Double[0]));

            mySetMap.add(myMap);
        }

        block.setParamsMyMap(mySetMap);

        BlockRepository.openCurrentSessionwithTransaction();
        BlockRepository.saveBlock(block);
        BlockRepository.closeCurrentSessionwithTransaction();
    }


//    public void update(ru.bmstu.processing.models.Block Block) {
//        BlockRepository.openCurrentSessionwithTransaction();
//        BlockRepository.updateBlock(Block);
//        BlockRepository.closeCurrentSessionwithTransaction();
//    }

    public Block findById(Integer id) {
        BlockRepository.openCurrentSession();
        Block Block = BlockRepository.getBlockById(id);
        BlockRepository.closeCurrentSession();
        return Block;
    }

    public void delete(Integer id) {
        BlockRepository.openCurrentSessionwithTransaction();
        Block Block = BlockRepository.getBlockById(id);
        BlockRepository.deleteBlock(Block);
        BlockRepository.closeCurrentSessionwithTransaction();
    }

    public static void main(String[] args) {
        ru.bmstu.processing.models.Block block = new ru.bmstu.processing.models.Block();
        block.setDescription("testBlock");
        block.setExperimentId(100000);
        Map<String, List<Double>> map = new HashMap<>();
        map.put("test1", Arrays.asList(12.0, 13.0, 14.0));
        map.put("test2", Arrays.asList(15.0, 16.0, 17.0));
        block.setParamsMap(map);

        BlockService blockService = new BlockService();
        blockService.create(block);
    }

//    public List<Block> findAll() {
//        BlockRepository.openCurrentSession();
//        List<Block> Blocks = BlockRepository.findAll();
//        BlockRepository.closeCurrentSession();
//        return Blocks;
//    }
//
//    public void deleteAll() {
//        BlockRepository.openCurrentSessionwithTransaction();
//        BlockRepository.deleteAll();
//        BlockRepository.closeCurrentSessionwithTransaction();
//    }
}
