package archives.tater.smoothcreeper.mixin;

import archives.tater.smoothcreeper.SmoothCreeper;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Definition(id = "withParameter", method = "Lnet/minecraft/world/level/storage/loot/LootParams$Builder;withParameter(Lnet/minecraft/util/context/ContextKey;Ljava/lang/Object;)Lnet/minecraft/world/level/storage/loot/LootParams$Builder;")
    @Definition(id = "TOOL", field = "Lnet/minecraft/world/level/storage/loot/parameters/LootContextParams;TOOL:Lnet/minecraft/util/context/ContextKey;")
    @Expression("?.withParameter(TOOL, ?)")
    @ModifyArg(
            method = "onExplosionHit",
            at = @At("MIXINEXTRAS:EXPRESSION"),
            index = 1
    )
    private Object changeTool(Object object, @Local(argsOnly = true) ServerLevel level, @Local(argsOnly = true) Explosion explosion) {
        return explosion.getDirectSourceEntity() instanceof Creeper creeper ? SmoothCreeper.getTool(level, creeper) : object;
    }
}
