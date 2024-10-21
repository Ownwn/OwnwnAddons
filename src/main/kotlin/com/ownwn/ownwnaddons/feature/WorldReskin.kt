package com.ownwn.ownwnaddons.feature

import com.ownwn.ownwnaddons.Config
import com.ownwn.ownwnaddons.util.Game
import com.ownwn.ownwnaddons.util.Utils
import net.minecraft.block.Block
import net.minecraft.block.BlockColored
import net.minecraft.block.BlockDirt
import net.minecraft.block.BlockDirt.DirtType
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.WorldRenderer
import net.minecraft.init.Blocks
import net.minecraft.item.EnumDyeColor
import net.minecraft.util.BlockPos
import net.minecraft.world.IBlockAccess


object WorldReskin {
    fun reskinHub(state: IBlockState, pos: BlockPos, blockAccess: IBlockAccess, worldRendererIn: WorldRenderer): Boolean {
        if (!Config.worldReskinHub) {
            return false
        }

        if (!Utils.isInIsland("Hub")) {
            return false
        }

        val block: Block = state.block
        var newState: IBlockState? = null

        when (block) {
            Blocks.stone, Blocks.clay, Blocks.grass -> newState = Blocks.stonebrick.defaultState // normal stone bricks

            Blocks.stained_hardened_clay -> {
                if (state.getValue(BlockColored.COLOR) != EnumDyeColor.CYAN) {
                    return false
                }
                newState = Blocks.stonebrick.getStateFromMeta(2) // cracked stone bricks
            }

            Blocks.dirt -> {
                val dirtType = state.getValue(BlockDirt.VARIANT)
                newState = if (dirtType == DirtType.COARSE_DIRT) {
                    Blocks.stonebrick.getStateFromMeta(2) // Cracked stone bricks
                } else {
                    Blocks.stonebrick.defaultState // Stone bricks
                }
            }
        }

        return renderReskinnedBlock(newState, pos, blockAccess, worldRendererIn)
    }

    private fun renderReskinnedBlock(newState: IBlockState?, pos: BlockPos, blockAccess: IBlockAccess, worldRendererIn: WorldRenderer): Boolean {
        if (newState == null) {
            return false
        }

        val dispatcher = Game.mc!!.blockRendererDispatcher
        dispatcher.renderBlock(newState, pos, blockAccess, worldRendererIn)
        return true
    }
}