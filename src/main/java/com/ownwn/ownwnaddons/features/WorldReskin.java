package com.ownwn.ownwnaddons.features;

import com.ownwn.ownwnaddons.OwnwnAddons;
import com.ownwn.ownwnaddons.utils.NewConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class WorldReskin {

    public static boolean reskinHub(IBlockState state, BlockPos pos, IBlockAccess blockAccess, WorldRenderer worldRendererIn) {
        if (!NewConfig.WORLD_RESKIN_HUB) {
            return false;
        }

        if (!OwnwnAddons.utils.checkLocMap("Hub")) {
            return false;
        }

        Block block = state.getBlock();
        IBlockState newState = null;

        if (block == Blocks.stone || block == Blocks.clay || block == Blocks.grass) {
            newState = Blocks.stonebrick.getDefaultState(); // normal stone bricks

        } else if (block == Blocks.stained_hardened_clay) {
            if (state.getValue(BlockColored.COLOR) != EnumDyeColor.CYAN) {
                return false;
            }
            newState = Blocks.stonebrick.getStateFromMeta(2); // cracked stone bricks

        } else if (block == Blocks.dirt) {
            BlockDirt.DirtType dirtType = state.getValue(BlockDirt.VARIANT);
            if (dirtType == BlockDirt.DirtType.COARSE_DIRT) {
                newState = Blocks.stonebrick.getStateFromMeta(2); // Cracked stone bricks
            } else {
                newState = Blocks.stonebrick.getDefaultState(); // Stone bricks
            }
        }

        return renderReskinnedBlock(newState, pos, blockAccess, worldRendererIn);

    }



//    public static boolean reskinEnd(IBlockState state, BlockPos pos, IBlockAccess blockAccess, WorldRenderer worldRendererIn) {
//        if (!NewConfig.WORLD_RESKIN_END) {
//            return false;
//        }
//
//        if (!Utils.checkLocMap("The End")) {
//            return false;
//        }
//
//        Block block = state.getBlock();
//        IBlockState newState = null;
//
//        if (block == Blocks.end_stone) {
//            newState = Blocks.stonebrick.getDefaultState(); // normal stone bricks
//        } else if (block == Blocks.sandstone) {
//            newState = Blocks.stonebrick.getStateFromMeta(2); // cracked stone bricks
//        }
//
//        return renderReskinnedBlock(newState, pos, blockAccess, worldRendererIn);
//
//    }


    private static boolean renderReskinnedBlock(IBlockState newState, BlockPos pos, IBlockAccess blockAccess, WorldRenderer worldRendererIn) {
        if (newState == null) {
            return false;
        }
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        dispatcher.renderBlock(newState, pos, blockAccess, worldRendererIn);
        return true;
    }

}
