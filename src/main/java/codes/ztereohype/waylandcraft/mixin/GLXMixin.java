package codes.ztereohype.waylandcraft.mixin;

import com.mojang.blaze3d.platform.GLX;
import org.lwjgl.glfw.GLFW;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.LongSupplier;

import static org.lwjgl.glfw.GLFW.GLFW_PLATFORM;
import static org.lwjgl.glfw.GLFW.GLFW_PLATFORM_WAYLAND;

@Mixin(GLX.class)
public class GLXMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetErrorCallback(Lorg/lwjgl/glfw/GLFWErrorCallbackI;)Lorg/lwjgl/glfw/GLFWErrorCallback;", shift = At.Shift.AFTER), method = "_initGlfw()Ljava/util/function/LongSupplier;", remap = false)
	private static void setWayland(CallbackInfoReturnable<LongSupplier> cir) {
		LoggerFactory.getLogger("waylandcraft").info("Applying wayland...");

		GLFW.glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_WAYLAND);
	}
}
