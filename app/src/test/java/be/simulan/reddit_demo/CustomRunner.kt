package be.simulan.reddit_demo

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.reflect.Method


class CustomRunner(testClass: Class<*>) : RobolectricTestRunner(testClass) {
    val manifest = "./app/src/main/AndroidManifest.xml"
    val resourceDirectory = "./app/src/main/res"
    val assetsDirectory = "./app/src/main/assets"

    override fun getConfig(method: Method?): Config {
        return Config.Builder()
                .setConstants(CustomBuildConfig::class.java)
                .setManifest(manifest)
                .setResourceDir(resourceDirectory)
                .setAssetDir(assetsDirectory)
                .setSdk(25)
                .build()

    }

}