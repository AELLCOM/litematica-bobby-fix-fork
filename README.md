# Litematica Bobby Fix Fork

Lets Litematica render schematics and count materials in chunks loaded by Bobby beyond the server view distance.

[Modrinth](https://modrinth.com/mod/litematica-bobby-fix-fork) · [CurseForge](https://www.curseforge.com/minecraft/mc-mods/litematica-bobby-fix-fork) · [Discord](https://discord.gg/jTU2yawThv)

This branch (`mc/26.2`) is the source of **1.1.7+26.2** for **Minecraft 26.2** (Fabric).
Branches: `main` (26.3), `mc/26.2` (26.2), `mc/1.21.11` (1.21.11). Release notes: [CHANGELOG.md](CHANGELOG.md).

## Building

You need JDK 25+ (Gradle comes with the wrapper).

```sh
./libs/fetch-libs.sh   # malilib, litematica from Modrinth, SHA-1 checked
./gradlew build        # the jar lands in build/libs/
```

The jars in `libs/` are compile-only: the mod talks to them at runtime, none of their code is bundled.


## License

MIT — see [LICENSE](LICENSE).

Not an official Minecraft product. Not approved by or associated with Mojang or Microsoft.

