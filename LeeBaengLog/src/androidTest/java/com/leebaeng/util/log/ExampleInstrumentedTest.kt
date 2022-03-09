package com.leebaeng.util.log

import android.content.Intent
import android.os.Bundle
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.RuntimeException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.leebaeng.util.log.test", appContext.packageName)
    }

    @Test
    fun printTest() {
        val TAG = "Tag"
        LLog.verbose("Verbose")
        LLog.verbose("Verbose", TAG)
        LLog.debug("Debug")
        LLog.debug("Debug", TAG)
        LLog.info("Info")
        LLog.info("Info", TAG)
        LLog.warn("Warning")
        LLog.warn("Warning", TAG)
        LLog.err("Error")
        LLog.err("Error", TAG)
        LLog.except(RuntimeException("RuntimeException"), "what is happening?")
        try {
            var a = 1 / 0
        } catch (e: Exception) {
            LLog.except(e, "Error", TAG)
        }
        LLog.sys("Start LLog Application")
        LLog.sys("Start LLog Application", TAG)
        LLog.sys("Start LLog Application", printSpline = false)
        LLog.sys("Start LLog Application", TAG, printSpline = false)

        "===================================================================================".logW()
        "Verbose".logV()
        "Debug".logD()
        "Info".logI()
        "Warn".logW()
        "Error".logE()
        RuntimeException().logEX("occur error while runtime")
        Intent().log()
        Bundle().log()
    }
}