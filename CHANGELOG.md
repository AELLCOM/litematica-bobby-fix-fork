# Litematica Bobby Fix Fork — changelog

English release notes as published on [Modrinth](https://modrinth.com/mod/litematica-bobby-fix-fork/changelog), newest first.
The original Russian notes are in [CHANGELOG.ru.md](CHANGELOG.ru.md).

## 1.1.7 — 2026-10-05

Minecraft 26.3, 26.2, 1.21.11

#### Fixed
- fix — **the schematic vanished in Bobby chunks about every other time.** When the server unloads a chunk, Bobby swaps in its cached chunk before Litematica unloads its own schematic chunk, and Litematica's background rebuild could load the schematic chunk too early — it then stayed missing until the next real chunk arrived (nothing rendered there, Material HUD lost banner patterns in those chunks). The load now waits until the server packet is fully handled and checks that the cached chunk is still there.

Same number as 1.1.7 for 26.3: versions 1.1.4–1.1.7 only changed the Bobby copy bundled into the 26.3 build. On 1.21.11 Bobby is not bundled — install the official Bobby.

**RU.** fix — схема в чанках Bobby пропадала примерно через раз. Загрузка чанка схемы теперь ждёт конца обработки пакета сервера. Bobby в сборку 1.21.11 не вшит — нужен официальный Bobby.

#### 📦 You need

Minecraft 1.21.11 · Fabric Loader · Bobby · Litematica · Java 21

## 1.1.6 — 2026-10-01

Minecraft 26.3

FIXES

## 1.1.5 — 2026-09-30

Minecraft 26.3

When there were areas the game had no chunks for at all, Sodium's culling treated them as solid: everything behind such an area was hidden, and while the camera moved it kept popping in and out (Sodium briefly falls back to a coarser visibility list). Those areas are now treated as empty space, so the terrain behind them stays visible and stable.

#### Minecraft 26.3

Ported to Minecraft 26.3 (Fabric Loader 0.19.5, Fabric API 0.161.0+26.3), built against Litematica 0.29.1 / malilib 0.30.2.

#### Fixed: the schematic vanished in cached chunks about every other time

Litematica 0.28+ rebuilds chunks on a worker thread, and that rebuild could load the schematic chunk before Litematica's own unload ran on the packet handler's return — the schematic was then gone from that chunk until the next real one arrived. The load is now queued on the client thread and runs after the packet handler.

**RU.** Исправлено мерцание с Sodium: чанки за непрогруженными областями то пропадали, то появлялись при движении камеры — Sodium считал пустые области непрозрачными. Порт на Minecraft 26.3 (Litematica 0.29.1). Исправлено пропадание схемы в кэшированных чанках примерно через раз.

#### 📦 You need

Minecraft 26.3 · Fabric Loader · Litematica · Java 25

## 1.1.2 — 2026-08-16

Minecraft 26.2

New icon

## 1.1.1 — 2026-08-14

Minecraft 26.2

Just new Icon :)

## 1.1.0 — 2026-08-05

Minecraft 26.2

_No notes._

