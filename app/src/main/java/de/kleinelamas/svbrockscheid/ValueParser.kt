package de.kleinelamas.svbrockscheid

import java.util.HashMap
import java.util.regex.Pattern

/**
 * This class helps to parse values from the SV Brockscheid configuration files
 */
object ValueParser {

    private val resultPattern = Pattern.compile("\\$([a-zA-Z0-9]+)='([-a-zA-Z0-9.: ()]+)'")

    fun parse(resultFile: String): Map<String, String> {
        val results = HashMap<String, String>()
        val resultMatcher = resultPattern.matcher(resultFile)
        while (resultMatcher.find()) {
            results.put(resultMatcher.group(1), resultMatcher.group(2))
        }
        return results
    }
}