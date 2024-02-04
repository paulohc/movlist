package com.paulohc.movlist.navigation

import androidx.navigation.NamedNavArgument

abstract class BaseScreen(
    val routePrefix: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {
    val route: String = if (arguments.isEmpty()) {
        routePrefix
    } else {
        val argumentsString = arguments
            .map { it.name }
            .joinToString(separator = "$") { name ->
                "$name={$name}"
            }
        "$routePrefix?$argumentsString"
    }

    protected fun buildRouteWithParams(
        parameters: Map<String, Any?> = emptyMap(),
    ): String {
        if (parameters.isEmpty()) return routePrefix

        val validParams =
            parameters.filter { param ->
                arguments.any { argument -> argument.name == param.key }
            }

        val parameterList = validParams
            .entries
            .joinToString(separator = "$") { entry ->
                "${entry.key}=${entry.value}"
            }

        return "$routePrefix?$parameterList"
    }
}
