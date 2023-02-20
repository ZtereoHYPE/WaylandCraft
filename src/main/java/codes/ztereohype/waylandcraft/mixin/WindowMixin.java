package codes.ztereohype.waylandcraft.mixin;

import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.lwjgl.glfw.GLFW.*;

@Mixin(Window.class)
public class WindowMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE), method = "<init>", remap = false)
    private void doNotSetInputFocus(CallbackInfo ci) {
        glfwWindowHint(GLFW_FOCUSED, GLFW_FALSE);
        // todo: find a way to create custom decorations? maybe in minecraft style?
        glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
    }

    @Inject(at = @At("HEAD"), method = "setIcon", cancellable = true)
    private void doNotSetIcon(CallbackInfo ci) {
        // todo: find a way to set the icon anyways
        ci.cancel();
    }
}
