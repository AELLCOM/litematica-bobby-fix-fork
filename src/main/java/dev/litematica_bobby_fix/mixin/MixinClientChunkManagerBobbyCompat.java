package dev.litematica_bobby_fix.mixin;

import fi.dy.masa.litematica.config.Configs;
import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.util.SchematicWorldRefresher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientChunkCache.class, priority = 1500)
public abstract class MixinClientChunkManagerBobbyCompat {
    // Методы bobby_* добавляет в ClientChunkCache миксин Bobby — у них нет имён Mojang, перемаппинг не нужен.
    @Inject(method = "bobby_onFakeChunkAdded", at = @At("HEAD"), require = 0, remap = false)
    private void litematicaBobbyFix_onFakeChunkAdded(int x, int z, CallbackInfo ci) {
        // Когда сервер выгружает чанк, Bobby подставляет фейковый прямо внутри ClientChunkCache.drop(),
        // то есть ещё до того, как Litematica на выходе из handleForgetLevelChunk выгрузит свой чанк
        // схемы. Пересборку чанка Litematica 0.28+ (26.x) отдаёт в отдельный поток, и он может успеть загрузить
        // чанк схемы раньше этой выгрузки — тогда схема в фейковом чанке пропадает до следующего
        // настоящего чанка (случайно, примерно через раз). Поэтому загрузка откладывается в очередь
        // клиента и выполняется после текущего обработчика пакета.
        Minecraft.getInstance().schedule(() -> litematicaBobbyFix_loadSchematicChunk(x, z));
    }

    @Inject(method = "bobby_onFakeChunkRemoved", at = @At("HEAD"), require = 0, remap = false)
    private void litematicaBobbyFix_onFakeChunkRemoved(int x, int z, boolean willBeReplaced, CallbackInfo ci) {
        if (willBeReplaced) {
            return;
        }

        try {
            if (!Configs.Generic.LOAD_ENTIRE_SCHEMATICS.getBooleanValue()) {
                DataManager.getSchematicPlacementManager().onClientChunkUnload(x, z);
            }
        } catch (Exception ignored) {
        }
    }

    private static void litematicaBobbyFix_loadSchematicChunk(int x, int z) {
        try {
            Minecraft mc = Minecraft.getInstance();

            // Фейковый чанк могли успеть убрать (игрок ушёл дальше) — тогда грузить нечего.
            if (mc.level == null || mc.level.getChunkSource().getChunk(x, z, ChunkStatus.FULL, false) == null) {
                return;
            }

            DataManager.getSchematicPlacementManager().onClientChunkLoad(x, z);

            if (Configs.Visuals.ENABLE_RENDERING.getBooleanValue()
                    && Configs.Visuals.ENABLE_SCHEMATIC_RENDERING.getBooleanValue()) {
                SchematicWorldRefresher.INSTANCE.markSchematicChunksForRenderUpdate(x, z);
            }
        } catch (Exception ignored) {
        }
    }
}
