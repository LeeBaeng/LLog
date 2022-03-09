package com.leebaeng.util.log

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.leebaeng.util.array.LArrUtil
import java.util.*

/**
 * LeeBaeng Log Module (not Supported <On Screen Log> yet)
 *
 * @author LeeBaeng
 * @version 1.0.0
 */
object LLog {
    enum class LogLevel(val level: Int, val prefix: String, val priority: Int) {
        VERBOSE(0, "V", Log.VERBOSE),
        DEBUG(1, "D", Log.DEBUG),
        INFO(2, "I", Log.INFO),
        WARN(3, "W", Log.WARN),
        ERROR(4, "E", Log.ERROR),
        EXCEPT(5, "X", Log.ASSERT),
        NONE(6, "N", Log.ASSERT),
        SYSTEM(7, "S", Log.ASSERT)
    }

    private const val WEB_LOG_TAG = "WebLog"
    private const val DEFAULT_HEADER = "LLog"

    var logLevel: LogLevel = LogLevel.VERBOSE // logLevel for Logcat console
    var logHeader: String = DEFAULT_HEADER
        private set

    fun init(context: Context?) {
        logHeader = context?.applicationInfo?.loadLabel(context.packageManager).toString()
    }

    fun setLogHeader(obj: Any?) {
        logHeader = getTagString(obj) ?: DEFAULT_HEADER
    }

    fun setLogHeader(header: String?) {
        logHeader = header ?: DEFAULT_HEADER
    }

    /** Verbose 로그를 출력한다.(Log level : 0) */
    fun verbose(log: String, tag: Any? = null) = log(LogLevel.VERBOSE, log, tag)

    /** Debug 로그를 출력한다.(Log level : 1) */
    fun debug(log: String, tag: Any? = null) = log(LogLevel.DEBUG, log, tag)

    /** Info 로그를 출력한다.(Log level : 2) */
    fun info(log: String, tag: Any? = null) = log(LogLevel.INFO, log, tag)

    /** Warning 로그를 출력한다.(Log level : 3) */
    fun warn(log: String, tag: Any? = null) = log(LogLevel.WARN, log, tag)

    /** Error 로그를 출력한다.(Log level : 4) */
    fun err(log: String, tag: Any? = null) = log(LogLevel.ERROR, log, tag)

    /** Exception 로그를 출력한다.(Log level : 5) */
    fun except(e: Throwable, log: String? = null, tag: Any? = null) {
        if (logLevel <= LogLevel.EXCEPT) {
            val header = getHeader(tag)
            val priority = LogLevel.EXCEPT.priority

            Log.println(priority, header, "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            Log.println(priority, header, "${log ?: "thrown Exception at ${getCallerClassName()}"} → $e")
            for (st in e.stackTrace) Log.println(priority, header, "     $st")
            if (e.cause != null) {
                val cuz = e.cause
                Log.println(priority, header, "   cause: " + cuz.toString())
                for (st in cuz!!.stackTrace) Log.println(priority, header, "     $st")
            }
            Log.println(priority, header, "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        }
    }

    /** System 로그를 출력한다.(Log level : 7) */
    fun sys(log: String, tag: Any? = null, printSpline: Boolean = true) {
        val header = getHeader(tag)
        val priority = LogLevel.SYSTEM.priority

        if (printSpline) Log.println(priority, header, "────────────────────────────────────────────────")
        Log.println(priority, header, log)
        if (printSpline) Log.println(priority, header, "────────────────────────────────────────────────")
    }

    private fun log(level: LogLevel, msg: String, tag: Any? = null) {
        if (logLevel <= level) Log.println(level.priority, getHeader(tag), msg)
    }

    /**
     * 로그헤더를 반환한다.
     * tag가 String type이 아닌 경우 해당 Object의 Class Name을 Tag로 사용한다.
     *
     * @param tag 헤더에 포함할 로그를 출력하는 개체
     * @return 반환형식 : [logHeader|tag]
     */
    fun getHeader(tag: Any?): String {
        return if (tag != null) {
            if (tag != WEB_LOG_TAG) "[$logHeader|${getTagString(tag) ?: getCallerClassName()}]"
            else "[$logHeader|$WEB_LOG_TAG]"
        } else "[$logHeader|${getCallerClassName()}]"
    }

    /** 태그 String을 반환 한다 */
    fun getTagString(tag: Any?): String? {
        if (tag != null) {
            return if (tag is String || (!tag.toString().contains("@") && tag.toString().length < 15)) tag.toString()
            else tag.javaClass.simpleName
        }
        return null
    }

    /** 로그를 출력한 Class의 이름을 반환한다 */
    fun getCallerClassName(): String? {
        val stElements = Thread.currentThread().stackTrace
        val filterPackages = setOf(this::class.qualifiedName!!, Thread::class.qualifiedName!!)
        for (i in 1 until stElements.size) {
            val ste = stElements[i]
            if (ste.className !in filterPackages) {
                return Class.forName(ste.className).simpleName
            }
        }
        return null
    }

    /** Intent 정보를 출력한다 */
    fun printIntentInfo(intent: Intent?, includeExtra: Boolean = true, tag: Any? = null, prefix: String? = null) {
        if (intent != null) {
            info("${prefix ?: ""} Intent :: $intent, action : ${intent.action}", tag)
            if (includeExtra) printIntentExtras(intent, tag)
        } else {
            info("${prefix ?: ""} Intent is null!!", tag)
        }
    }

    /** Intent의 Extra 정보를 출력한다 */
    fun printIntentExtras(intent: Intent, tag: Any?) {
        intent.extras?.let {
            printBundle(it, tag)
        }
    }

    /** Bundle Item 정보를 출력한다 */
    fun printBundle(bundle: Bundle, tag: Any?) {
        for (key in bundle.keySet()) {
            val value = bundle[key]
            if (value!! !is Array<*>) info(" └[" + key + "]=" + value.toString() + " (" + value.javaClass.name + ")", tag)
            else info(
                " └[" + key + "]=" + Arrays.deepToString(LArrUtil.convertObjectToArray(value)) + " (Array type : " + value.javaClass.componentType + "[])",
                tag
            )
        }
    }
}


/** Verbose 로그를 출력한다.(Log level : 0) */
fun String.logV(tag: Any? = null) = LLog.verbose(this, tag)

/** Debug 로그를 출력한다.(Log level : 1) */
fun String.logD(tag: Any? = null) = LLog.verbose(this, tag)

/** Info 로그를 출력한다.(Log level : 2) */
fun String.logI(tag: Any? = null) = LLog.verbose(this, tag)

/** Warning 로그를 출력한다.(Log level : 3) */
fun String.logW(tag: Any? = null) = LLog.verbose(this, tag)

/** Error 로그를 출력한다.(Log level : 4) */
fun String.logE(tag: Any? = null) = LLog.verbose(this, tag)

/** System 로그를 출력한다.(Log level : 7) */
fun String.logS(tag: Any? = null, printSpline: Boolean = true) = LLog.sys(this, tag, printSpline)

/** Exception 로그를 출력한다.(Log level : 5) */
fun Exception.logEX(log: String? = null, tag: Any? = null) = LLog.except(this, log, tag)

/** Intent 정보를 출력한다 */
fun Intent.log(includeExtra: Boolean = true, tag: Any? = null, prefix: String? = null) = LLog.printIntentInfo(this, includeExtra, tag, prefix)

/** Bundle Item 정보를 출력한다 */
fun Bundle.log(tag: Any? = null) = LLog.printBundle(this, tag)