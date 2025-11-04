package archives.tater.smoothcreeper.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.world.level.GameRules;

@Mixin(GameRules.class)
public class GameRulesMixin {
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=mobExplosionDropDecay"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/world/level/GameRules;RULE_MOB_EXPLOSION_DROP_DECAY:Lnet/minecraft/world/level/GameRules$Key;")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules$BooleanValue;create(Z)Lnet/minecraft/world/level/GameRules$Type;")
    )
    private static boolean defaultDropDecay(boolean bl) {
        return false;
    }
}