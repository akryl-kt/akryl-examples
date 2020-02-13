package io.akryl.todomvc.repositories

import io.akryl.todomvc.entities.Task
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlin.browser.localStorage

object TaskRepository {
    private val json = Json(JsonConfiguration.Stable)

    fun getAll(): List<Task> {
        val str = localStorage.getItem("todos") ?: return arrayListOf()
        return ArrayList(json.parse(Task.serializer().list, str))
    }

    fun saveAll(tasks: List<Task>) {
        val str = json.stringify(Task.serializer().list, tasks)
        localStorage.setItem("todos", str)
    }
}
