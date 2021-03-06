package svenhjol.charm.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.block.ICharmBlock;
import svenhjol.charm.base.enums.IVariantMaterial;
import svenhjol.charm.module.VariantChests;
import svenhjol.charm.tileentity.VariantTrappedChestTileEntity;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class VariantTrappedChestBlock extends ChestBlock implements ICharmBlock, IVariantChestBlock {
    private final CharmModule module;
    private final IVariantMaterial type;

    public VariantTrappedChestBlock(CharmModule module, IVariantMaterial type) {
        super(Properties.from(Blocks.TRAPPED_CHEST), () -> VariantChests.TRAPPED_BLOCK_ENTITY);

        this.module = module;
        this.type = type;

        this.register(module, type.getString() + "_trapped_chest");
    }

    @Override
    public ItemGroup getItemGroup() {
        return ItemGroup.REDSTONE;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (enabled())
            super.fillItemGroup(group, items);
    }

    @Override
    public boolean enabled() {
        return module.enabled;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        VariantTrappedChestTileEntity chest = new VariantTrappedChestTileEntity();
        chest.setCustomName(new TranslationTextComponent("block." + module.mod + "." + type.getString() + "_trapped_chest"));

        return chest;
    }

    @Override
    public int getWeakPower(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return MathHelper.clamp(ChestTileEntity.getPlayersUsing(world, pos), 0, 15);
    }

    @Override
    public int getStrongPower(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? state.getWeakPower(world, pos, direction) : 0;
    }

    @Override
    public IVariantMaterial getMaterialType() {
        return type;
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    /**
     * Copypasta from {@link net.minecraft.block.TrappedChestBlock}
     */
    protected Stat<ResourceLocation> getOpenStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public Supplier<Callable<ItemStackTileEntityRenderer>> getISTER() {
        return () -> () -> new ItemStackTileEntityRenderer() {
            private final VariantTrappedChestTileEntity tile = new VariantTrappedChestTileEntity();

            @Override
            public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
                tile.setMaterialType(getMaterialType());
                TileEntityRendererDispatcher.instance.renderItem(tile, matrix, buffer, combinedLight, combinedOverlay);
            }
        };
    }
}
