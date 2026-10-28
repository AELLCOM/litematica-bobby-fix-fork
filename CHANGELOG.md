# Litematica Bobby Fix Fork — changelog

English release notes as published on [Modrinth](https://modrinth.com/mod/litematica-bobby-fix-fork/changelog), newest first.
The original Russian notes are in [CHANGELOG.ru.md](CHANGELOG.ru.md).

## 1.1.7 — 2026-10-05

Minecraft 26.3, 26.2, 1.21.11

- fix — chunks flickered while flying or walking with Sodium, and terrain behind unexplored gaps vanished whenever you stopped. Sodium's visibility search cannot pass through chunk columns that neither the server nor the cache has, so everything behind such a gap was reachable only by detours that changed at every chunk-section border — and with the camera right above a gap the search did not start at all, so the whole view disappeared the moment you stood still. Such gaps are now reported to Sodium as empty air and the search passes straight through them.
- fix — the set of rendered chunks differed between a still and a moving camera (Sodium switches to a stricter, ray-tested visibility tree a few frames after you stop). Rendering now always uses the regular tree, so stopping and starting no longer toggles distant chunks.
- Both behaviours are options (on by default) and can be switched in-game for comparison: `/bobby holes on|off`, `/bobby culling stable|stock`.

#### 📦 You need

Minecraft 26.3 · Fabric Loader · Litematica · Java 25

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

