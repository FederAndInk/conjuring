package com.glisco.conjuring.client;

import com.glisco.conjuring.blocks.GemTinkererBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class GemTinkererBlockEntityRenderer extends BlockEntityRenderer<GemTinkererBlockEntity> {

    public static final SpriteIdentifier MODEL_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("conjuring", "block/gem_tinkerer"));

    ModelPart columnModel = new ModelPart(32, 32, 0, 0);
    ModelPart mainModel = new ModelPart(32, 32, 8, 0);

    private static final double twoPi = Math.PI * 2;
    private double scalar = 800;

    public GemTinkererBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);

        columnModel.addCuboid(0, 0, 0, 2, 6, 2);
        mainModel.addCuboid(0, 0, 0, 4, 12, 4);
    }

    public void render(GemTinkererBlockEntity blockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {

        scalar = blockEntity.getScalar();

        int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(blockEntity.getWorld()), blockEntity.getPos().up());
        VertexConsumer vertexConsumer = MODEL_TEXTURE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);

        DefaultedList<ItemStack> items = blockEntity.getInventory();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        float itemRotation = (float) (System.currentTimeMillis() / 30d % 360);
        final float scaledRotation = (float) (itemRotation + Math.pow(scalar * 2, 1.2));

        // ---

        matrixStack.push();

        matrixStack.translate(0.375, 0.2 + getHeight(0) * 0.25, 0.375);
        mainModel.render(matrixStack, vertexConsumer, lightAbove, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

        matrixStack.translate(0.125, 0.85, 0.125);
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(itemRotation));
        itemRenderer.renderItem(items.get(0), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider);

        matrixStack.pop();

        // ---

        matrixStack.push();

        matrixStack.translate(0.1, 0.5 + getHeight(0.5 * twoPi), 0.45);
        columnModel.render(matrixStack, vertexConsumer, lightAbove, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

        matrixStack.translate(0.0625, 0.425, 0.0625);
        matrixStack.scale(0.25f, 0.25f, 0.25f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(scaledRotation));
        itemRenderer.renderItem(items.get(1), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider);

        matrixStack.pop();

        // ---

        matrixStack.push();

        matrixStack.translate(0.45, 0.5 + getHeight(1.5 * twoPi), 0.1);
        columnModel.render(matrixStack, vertexConsumer, lightAbove, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

        matrixStack.translate(0.0625, 0.425, 0.0625);
        matrixStack.scale(0.25f, 0.25f, 0.25f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(scaledRotation));
        itemRenderer.renderItem(items.get(2), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider);

        matrixStack.pop();

        // ---

        matrixStack.push();

        matrixStack.translate(0.45, 0.5 + getHeight(twoPi), 0.8);
        columnModel.render(matrixStack, vertexConsumer, lightAbove, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

        matrixStack.translate(0.0625, 0.425, 0.0625);
        matrixStack.scale(0.25f, 0.25f, 0.25f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(scaledRotation));
        itemRenderer.renderItem(items.get(3), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider);

        matrixStack.pop();

        // ---

        matrixStack.push();

        matrixStack.translate(0.8, 0.5 + getHeight(0), 0.45);
        columnModel.render(matrixStack, vertexConsumer, lightAbove, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);

        matrixStack.translate(0.0625, 0.425, 0.0625);
        matrixStack.scale(0.25f, 0.25f, 0.25f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(scaledRotation));
        itemRenderer.renderItem(items.get(4), ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider);

        matrixStack.pop();

    }

    private double getHeight(double offset) {
        return Math.sin((System.currentTimeMillis() / 800d + offset * twoPi) % (twoPi)) * 0.01 * scalar;
    }
}