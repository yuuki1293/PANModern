import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import com.hypherionmc.modpublisher.properties.ReleaseType

plugins {
    `java-library`
    `maven-publish`
    idea
    id("net.neoforged.moddev") version "2.0.141"
    id("com.hypherionmc.modutils.modpublisher") version "2.1.+"
}

val minecraftVersion: String = property("minecraft_version").toString()
val minecraftVersionRange: String = property("minecraft_version_range").toString()
val neoVersion: String = property("neo_version").toString()
val neoVersionRange: String = property("neo_version_range").toString()
val loaderVersionRange: String = property("loader_version_range").toString()
val parchmentMinecraftVersion: String = property("parchment_minecraft_version").toString()
val parchmentMappingsVersion: String = property("parchment_mappings_version").toString()
val modId: String = property("mod_id").toString()
val modName: String = property("mod_name").toString()
val modLicense: String = property("mod_license").toString()
val modVersion: String = property("mod_version").toString()
val modGroupId: String = property("mod_group_id").toString()
val modAuthors: String = property("mod_authors").toString()
val modDescription: String = property("mod_description").toString()
val curseforgeProjectId: String = property("curseforge_project_id").toString()
val modrinthProjectId: String = property("modrinth_project_id").toString()
val githubRepository: String = property("github_repository").toString()
val licenseFileName = "LICENSE_$modId"

version = modVersion
group = modGroupId

repositories {
    mavenLocal()
}

base {
    archivesName = modId
}

java {
    withSourcesJar()
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

neoForge {
    version = neoVersion

    parchment {
        mappingsVersion = parchmentMappingsVersion
        minecraftVersion = parchmentMinecraftVersion
    }

    runs {
        register("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        register("data") {
            data()
            programArguments.addAll(
                "--mod",
                modId,
                "--all",
                "--output",
                file("src/generated/resources/").absolutePath,
                "--existing",
                file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create(modId) {
            sourceSet(sourceSets["main"])
        }
    }
}

dependencies {
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = 21
}

tasks.jar {
    manifest {
        attributes(
            "Specification-Title" to modName,
            "Specification-Vendor" to modAuthors,
            "Specification-Version" to modVersion,
            "Implementation-Title" to project.name,
            "Implementation-Version" to modVersion,
            "Implementation-Vendor" to modAuthors,
            "Built-On-Minecraft" to minecraftVersion,
            "MixinConfigs" to "$modId.mixins.json"
        )
    }
}

tasks.named<Jar>("sourcesJar") {
    from(rootProject.file("LICENSE")) {
        rename("LICENSE", licenseFileName)
    }
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
        "minecraft_version" to minecraftVersion,
        "minecraft_version_range" to minecraftVersionRange,
        "neo_version" to neoVersion,
        "neo_version_range" to neoVersionRange,
        "loader_version_range" to loaderVersionRange,
        "mod_id" to modId,
        "mod_name" to modName,
        "mod_license" to modLicense,
        "mod_version" to modVersion,
        "mod_authors" to modAuthors,
        "mod_description" to modDescription
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

tasks.processResources {
    from(rootProject.file("LICENSE")) {
        rename("LICENSE", licenseFileName)
    }
    dependsOn(generateModMetadata)
}

val preparePublish by tasks.registering(Sync::class) {
    group = "publishing"
    description = "Collects release artifacts for publishing."

    dependsOn(tasks.jar, tasks.named("sourcesJar"))

    into(layout.buildDirectory.dir("publish"))
    from(tasks.jar)
    from(tasks.named("sourcesJar"))
    from("README.md")
    from("CHANGELOG.md")
    from("LICENSE")
}

sourceSets {
    main {
        resources {
            srcDir("src/generated/resources")
            srcDir(generateModMetadata)
        }
    }
}

neoForge.ideSyncTask(generateModMetadata)

publisher {
    apiKeys {
        curseforge(System.getenv("CURSEFORGE_TOKEN"))
        modrinth(System.getenv("MODRINTH_TOKEN"))
        github(System.getenv("GITHUB_TOKEN"))
    }

    setReleaseType(ReleaseType.BETA)
    setLoaders(ModLoader.NEOFORGE)
    setCurseEnvironment(CurseEnvironment.BOTH)

    curseID.set(curseforgeProjectId)
    modrinthID.set(modrinthProjectId)
    changelog.set(providers.fileContents(layout.projectDirectory.file("CHANGELOG.md")).asText)
    projectVersion.set(modVersion)
    displayName.set("[$minecraftVersion] v$modVersion")
    setGameVersions(minecraftVersion)
    setJavaVersions(21)
    artifact.set(tasks.named("jar"))
    addAdditionalFile(tasks.named("sourcesJar"))

    github {
        repo(githubRepository)
        tag("$minecraftVersion/v$modVersion")
        displayName("[$minecraftVersion] v$modVersion")
        createTag(true)
        createRelease(true)
        updateRelease(true)
        target("1.21.1")
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${project.projectDir}/repo")
        }
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
