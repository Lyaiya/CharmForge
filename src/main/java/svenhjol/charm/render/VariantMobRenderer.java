package svenhjol.charm.render;

import net.minecraft.client.render.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.Identifier;
import svenhjol.charm.module.VariantMobTextures;

public class VariantMobRenderer {
    public static class Chicken extends ChickenEntityRenderer {
        public Chicken(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(ChickenEntity entity) {
            return VariantMobTextures.getChickenTexture(entity);
        }
    }

    public static class Cow extends CowEntityRenderer {
        public Cow(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(CowEntity entity) {
            return VariantMobTextures.getCowTexture(entity);
        }
    }

    public static class Pig extends PigEntityRenderer {
        public Pig(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(PigEntity entity) {
            return VariantMobTextures.getPigTexture(entity);
        }
    }

    public static class Sheep extends SheepEntityRenderer {
        public Sheep(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(SheepEntity entity) {
            return VariantMobTextures.getSheepTexture(entity);
        }
    }

    public static class SnowGolem extends SnowGolemEntityRenderer {
        public SnowGolem(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(SnowGolemEntity entity) {
            return VariantMobTextures.getSnowGolemTexture(entity);
        }
    }

    public static class Squid extends SquidEntityRenderer {
        public Squid(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(SquidEntity entity) {
            return VariantMobTextures.getSquidTexture(entity);
        }
    }

    public static class Wolf extends WolfEntityRenderer {
        public Wolf(EntityRenderDispatcher dispatcher) {
            super(dispatcher);
        }

        @Override
        public Identifier getTexture(WolfEntity entity) {
            return VariantMobTextures.getWolfTexture(entity);
        }
    }
}
