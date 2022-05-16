package net.exzotic.epoli.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import net.exzotic.epoli.Epoli;
import net.exzotic.epoli.power.ActionOnTriggerPower;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TriggerCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TriggerCommand.class)
public class TriggerCommandMixin {
    @Inject(method = "executeAdd", at = @At("HEAD"))
    private static void executeAdd(ServerCommandSource source, ScoreboardPlayerScore score, int value, CallbackInfoReturnable<Integer> cir){
        for (ActionOnTriggerPower power : PowerHolderComponent.getPowers(source.getEntity(), ActionOnTriggerPower.class)) {
            if(power.isOperation("add")) {
                if(power.isScore(score)){
                    if(power.compare(value)){
                        power.executeAction();
                    }
                }
            }
        }
    }

    @Inject(method = "executeSet", at = @At("HEAD"))
    private static void executeSet(ServerCommandSource source, ScoreboardPlayerScore score, int value, CallbackInfoReturnable<Integer> cir){
        for (ActionOnTriggerPower power : PowerHolderComponent.getPowers(source.getEntity(), ActionOnTriggerPower.class)) {
            if(power.isOperation("set")) {
                if(power.isScore(score)){
                    if(power.compare(value)){
                        power.executeAction();
                    }
                }
            }
        }
    }

    @Inject(method = "executeSimple", at = @At("HEAD"))
    private static void executeSet(ServerCommandSource source, ScoreboardPlayerScore score, CallbackInfoReturnable<Integer> cir){
        for (ActionOnTriggerPower power : PowerHolderComponent.getPowers(source.getEntity(), ActionOnTriggerPower.class)) {
            if(power.isOperation("simple")) {
                if(power.isScore(score)){
                    power.executeAction();
                }
            }
        }
    }
}
