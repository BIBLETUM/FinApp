includeBuild("build-logic")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FinApp"
include(":app")

listOf(
    "ui",
    "utils",
    "data",
    "domain",
).forEach {
    include(":core:$it")
}

listOf(
    "splash",
    "settings",
    "account_info",
    "my_expense_categories",
    "today_expenses",
    "today_incomes",
    "history_expenses",
    "history_incomes",
).forEach {
    include(":feature:$it")
}
include(":feature:account_settings")
