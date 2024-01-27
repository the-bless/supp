package com.example.mysupp.screen


sealed class Screen(val route: String) {
    object LoginScreen : Screen(route = "login_screen")
    object VerifyScreen : Screen(route = "verify_screen")
    object RegisterScreen : Screen(route = "register_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
