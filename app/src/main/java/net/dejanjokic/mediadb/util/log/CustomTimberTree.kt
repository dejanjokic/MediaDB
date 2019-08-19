package net.dejanjokic.mediadb.util.log

import timber.log.Timber

class CustomTimberTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? =
        "(${element.fileName}:${element.lineNumber})#${element.methodName}"
}