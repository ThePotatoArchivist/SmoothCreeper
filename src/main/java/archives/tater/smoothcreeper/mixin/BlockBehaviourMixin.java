package archives.tater.smoothcreeper.mixin;

import archives.tater.smoothcreeper.SmoothCreeper;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @ModifyArg(
            method = "onExplosionHit",
            slice = @Slice(
                    from = @At(value = "FIELD", target = "Lnet/minecraft/world/level/storage/loot/parameters/LootContextParams;TOOL:Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/world/level/storage/loot/parameters/LootContextParams;BLOCK_ENTITY:Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootParams$Builder;withParameter(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;Ljava/lang/Object;)Lnet/minecraft/world/level/storage/loot/LootParams$Builder;"),
            index = 1
    )
    private Object changeTool(Object object, @Local(argsOnly = true) Level level, @Local(argsOnly = true) Explosion explosion) {
        return explosion.getDirectSourceEntity() instanceof Creeper ? SmoothCreeper.TOOL.apply(level) : object;
    }
}
