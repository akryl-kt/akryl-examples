package io.akryl.todomvc.store

import io.akryl.ComponentScope
import io.akryl.todomvc.entities.Task
import io.akryl.todomvc.repositories.TaskRepository
import io.akryl.useContext
import react.React

data class TaskFilter(
  val name: String,
  val url: String,
  val predicate: (Task) -> Boolean
)

val TodoContext = React.createContext<TodoStore?>(null)

data class TodoModel(
    val items: List<Task>,
    val filters: List<TaskFilter>,
    val currentFilter: Int
)

fun initModel() = TodoModel(
    items = TaskRepository.getAll(),
    filters = listOf(
        TaskFilter(
            name = "All",
            url = "/",
            predicate = { true }
        ),
        TaskFilter(
            name = "Active",
            url = "/active",
            predicate = { !it.completed }
        ),
        TaskFilter(
            name = "Completed",
            url = "/completed",
            predicate = { it.completed }
        )
    ),
    currentFilter = 0
)

fun ComponentScope.useStore() = useContext(TodoContext) ?: throw IllegalStateException("Store is null")

class TodoStore(
    private val model: TodoModel,
    private val setModel: (TodoModel) -> Unit
) {
    val items get() = model.items

    val filters get() = model.filters

    val filter get() = model.filters[model.currentFilter]

    val filtered get() = items.filter(filter.predicate)

    val allCompleted get() = items.all { it.completed }

    fun add(title: String) = action {
        model.copy(
            items = items + Task(id = uuid4(), title = title, completed = false)
        )
    }

    fun toggle(id: String) = action {
        model.copy(
            items = items.map {
                if (it.id == id) it.copy(completed = !it.completed) else it
            }
        )
    }

    fun clearCompleted() = action {
        model.copy(
            items = items.filterNot { it.completed }
        )
    }

    fun toggleAll() = action {
        model.copy(
            items = items.map {
                it.copy(completed = !it.completed)
            }
        )
    }

    fun rename(id: String, title: String) = action {
        model.copy(
            items = items.map {
                if (it.id == id) it.copy(title = title) else it
            }
        )
    }

    fun find(id: String) = model.items.firstOrNull { it.id == id }

    fun remove(id: String) = action {
        model.copy(
            items = items.filterNot { it.id == id }
        )
    }

    fun setFilter(index: Int) = action {
        model.copy(
            currentFilter = index
        )
    }

    private fun action(block: () -> TodoModel) {
        val newModel = block()
        if (newModel.items !== model.items) {
            TaskRepository.saveAll(newModel.items)
        }
        setModel(newModel)
    }
}

@JsModule("uuid/v4")
@JsNonModule
external fun uuid4(): String
